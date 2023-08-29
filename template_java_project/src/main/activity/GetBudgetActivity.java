package main.activity;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.converters.ModelConverter;
import main.dependency.DynamoDbClientProvider;
import main.dynamodb.BudgetDao;
import main.dynamodb.models.Budget;
import main.models.BudgetModel;
import main.models.requests.GetBudgetRequest;
import main.models.results.GetBudgetResult;

import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetBudgetActivity implements RequestHandler<GetBudgetRequest, GetBudgetResult> {
    private final Logger log = LogManager.getLogger();
    private final BudgetDao budgetDao;

    @Inject
    public GetBudgetActivity(BudgetDao budgetDao) {
        this.budgetDao = budgetDao;
    }

    public GetBudgetActivity() {
        this.budgetDao = new BudgetDao(new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_WEST_2)));
    }

    @Override
    public GetBudgetResult handleRequest(final GetBudgetRequest getBudgetRequest, Context context) {
        log.info("Received GetBudgetRequest {}");
        String requestedId = getBudgetRequest.getId();
        Budget budget = budgetDao.getBudget(requestedId);
        BudgetModel budgetModel = new ModelConverter().toBudgetModel(budget);

        return GetBudgetResult.builder()
                .withBudget(budgetModel)
                .build();
    }
}
