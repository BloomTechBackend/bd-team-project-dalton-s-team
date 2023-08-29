package main.activity;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.converters.ModelConverter;
import main.dependency.DynamoDbClientProvider;
import main.dynamodb.BudgetDao;
import main.dynamodb.SpendingCategoryDao;
import main.dynamodb.models.Budget;
import main.dynamodb.models.SpendingCategory;
import main.exceptions.BudgetNotFoundException;
import main.exceptions.InvalidAttributeValueException;
import main.models.SpendingCategoryModel;
import main.models.requests.UpdateSpendingCategoryRequest;
import main.models.results.UpdateSpendingCategoryResult;
import main.utils.BudgetServiceUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class UpdateSpendingCategoryActivity implements RequestHandler<UpdateSpendingCategoryRequest, UpdateSpendingCategoryResult> {
    private final Logger log = LogManager.getLogger();
    private final BudgetDao budgetDao;
    private final SpendingCategoryDao spendingCategoryDao;

    @Inject
    public UpdateSpendingCategoryActivity(BudgetDao budgetDao, SpendingCategoryDao spendingCategoryDao) {
        this.budgetDao = budgetDao;
        this.spendingCategoryDao = spendingCategoryDao;
    }

    public UpdateSpendingCategoryActivity() {
        this.budgetDao = new BudgetDao(new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_WEST_2)));
        this.spendingCategoryDao = new SpendingCategoryDao(new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_WEST_2)));
    }

    @Override
    public UpdateSpendingCategoryResult handleRequest(final UpdateSpendingCategoryRequest updateSpendingCategoryRequest, Context context) {
        log.info("Received UpdateSpendingCategoryRequest {} ", updateSpendingCategoryRequest);
        if (!(BudgetServiceUtils.isValidString(updateSpendingCategoryRequest.getName()))) {
            throw new InvalidAttributeValueException("Spending Category name is invalid");
        }
        Budget budget = budgetDao.getBudget(updateSpendingCategoryRequest.getBudgetId());
        if (budget == null) {
            throw new BudgetNotFoundException(String.format("A Budget with ID %s does not exist", budget.getId()));
        }

        SpendingCategory spendingCategory = new SpendingCategory();
        spendingCategory.setId(updateSpendingCategoryRequest.getId());
        spendingCategory.setBudgetId(updateSpendingCategoryRequest.getBudgetId());
        spendingCategory.setName(updateSpendingCategoryRequest.getName());
        spendingCategory.setSpendingLimit(updateSpendingCategoryRequest.getSpendingLimit());
        spendingCategory.setAmountSpent(updateSpendingCategoryRequest.getAmountSpent());

        spendingCategoryDao.saveSpendingCategory(spendingCategory);

        SpendingCategoryModel spendingCategoryModel = new ModelConverter().toSpendingCategoryModel(spendingCategory);
        return UpdateSpendingCategoryResult.builder()
                .withSpendingCategory(spendingCategoryModel)
                .build();
    }
}
