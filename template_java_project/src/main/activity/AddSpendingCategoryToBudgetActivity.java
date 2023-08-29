package main.activity;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.converters.ModelConverter;
import main.dependency.DynamoDbClientProvider;
import main.dynamodb.BudgetDao;
import main.dynamodb.SpendingCategoryDao;
import main.dynamodb.models.SpendingCategory;
import main.models.SpendingCategoryModel;
import main.models.requests.AddSpendingCategoryToBudgetRequest;
import main.models.results.AddSpendingCategoryToBudgetResult;
import main.utils.BudgetServiceUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import main.dynamodb.models.Budget;
import main.exceptions.BudgetNotFoundException;
import main.exceptions.InvalidAttributeValueException;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

    public class AddSpendingCategoryToBudgetActivity implements RequestHandler<AddSpendingCategoryToBudgetRequest, AddSpendingCategoryToBudgetResult> {
        private final Logger log = LogManager.getLogger();
        private final BudgetDao budgetDao;
        private final SpendingCategoryDao spendingCategoryDao;

        @Inject
        public AddSpendingCategoryToBudgetActivity(BudgetDao budgetDao, SpendingCategoryDao spendingCategoryDao) {
            this.budgetDao = budgetDao;
            this.spendingCategoryDao = spendingCategoryDao;
        }

        public AddSpendingCategoryToBudgetActivity() {
            this.budgetDao = new BudgetDao(new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_WEST_2)));
            this.spendingCategoryDao = new SpendingCategoryDao(new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_WEST_2)));
        }


        @Override
        public AddSpendingCategoryToBudgetResult handleRequest(final AddSpendingCategoryToBudgetRequest addSpendingCategoryToBudgetRequest, Context context) {
            log.info("Received AddSpendingCategoryToBudgetRequest {} ", addSpendingCategoryToBudgetRequest);
            if (!(BudgetServiceUtils.isValidString(addSpendingCategoryToBudgetRequest.getName()))) {
                throw new InvalidAttributeValueException("Spending Category name is invalid");
            }
            Budget budget = budgetDao.getBudget(addSpendingCategoryToBudgetRequest.getBudgetId());

            if (budget == null) {
                throw new BudgetNotFoundException("Budget ID does not exist");
            }
            // check to see if budgetId passed, exists, else throw BudgetNotFoundExpection
            String createdId = BudgetServiceUtils.generateSpendingCategoryId();
            SpendingCategory spendingCategory = new SpendingCategory();
            spendingCategory.setId(createdId);
            spendingCategory.setBudgetId(addSpendingCategoryToBudgetRequest.getBudgetId());
            spendingCategory.setName(addSpendingCategoryToBudgetRequest.getName());
            spendingCategory.setSpendingLimit(addSpendingCategoryToBudgetRequest.getSpendingLimit());
            spendingCategory.setAmountSpent(addSpendingCategoryToBudgetRequest.getAmountSpent());

            spendingCategoryDao.saveSpendingCategory(spendingCategory);

            SpendingCategoryModel spendingCategoryModel = new ModelConverter().toSpendingCategoryModel(spendingCategory);
            return AddSpendingCategoryToBudgetResult.builder()
                    .withSpendingCategory(spendingCategoryModel)
                    .build();
        }
    }

