package main.activity;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.converters.ModelConverter;
import main.dependency.DynamoDbClientProvider;
import main.dynamodb.SpendingCategoryDao;
import main.dynamodb.models.SpendingCategory;
import main.exceptions.BudgetNotFoundException;
import main.models.SpendingCategoryModel;
import main.models.requests.GetBudgetSpendingCategoriesRequest;
import main.models.results.GetBudgetSpendingCategoriesResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GetBudgetSpendingCategoriesActivity implements RequestHandler<GetBudgetSpendingCategoriesRequest, GetBudgetSpendingCategoriesResult> {
    private final Logger log = LogManager.getLogger();
    private final SpendingCategoryDao spendingCategoryDao;

    @Inject
    public GetBudgetSpendingCategoriesActivity(SpendingCategoryDao spendingCategoryDao) {
        this.spendingCategoryDao = spendingCategoryDao;
    }

    public GetBudgetSpendingCategoriesActivity() {
        this.spendingCategoryDao = new SpendingCategoryDao(new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_WEST_2)));
    }

    @Override
    public GetBudgetSpendingCategoriesResult handleRequest(final GetBudgetSpendingCategoriesRequest getBudgetSpendingCategoriesRequest, Context context) {
        log.info("Received GetBudgetSpendingCategoriesResult {}", getBudgetSpendingCategoriesRequest);
        String requestedId = getBudgetSpendingCategoriesRequest.getId();
        List<SpendingCategory> spendingCategories = spendingCategoryDao.getSpendingCategoryByBudgetId(requestedId);
        if (spendingCategories == null) {
            throw new BudgetNotFoundException(String.format("A Budget with id %s does not exist, or the Budget has no spending categories", requestedId));
        }

        List<SpendingCategoryModel> spendingCategoryModels = new ArrayList<>();
        for (SpendingCategory spendingCategory : spendingCategories) {
            spendingCategoryModels.add(new ModelConverter().toSpendingCategoryModel(spendingCategory));
        }
        return GetBudgetSpendingCategoriesResult.builder()
                .withSpendingCategoryList(spendingCategoryModels)
                .build();
    }
}
