package main.activity;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import main.converters.ModelConverter;
import main.dependency.DynamoDbClientProvider;
import main.dynamodb.BudgetDao;
import main.dynamodb.models.Budget;
import main.exceptions.InvalidAttributeValueException;
import main.models.BudgetModel;
import main.models.requests.CreateBudgetRequest;
import main.models.results.CreateBudgetResult;

import main.utils.BudgetServiceUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;


/**
 * Implementation of the CreateBudgetActivity for the FinancialFitness CreateBudget API
 *
 * Allows the customer to create a new budget with no categories.
 */

public class CreateBudgetActivity implements RequestHandler<CreateBudgetRequest, CreateBudgetResult> {
    private final Logger log = LogManager.getLogger();
    private final BudgetDao budgetDao;

    @Inject
    public CreateBudgetActivity(BudgetDao budgetDao) {
        this.budgetDao = budgetDao;
    }

    public CreateBudgetActivity() {
        this.budgetDao = new BudgetDao(new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_WEST_2)));
    }

    @Override
    public CreateBudgetResult handleRequest(final CreateBudgetRequest createBudgetRequest, Context context) {
        log.info("Received CreateBudgetRequest {}", createBudgetRequest);
        if (!(BudgetServiceUtils.isValidString(createBudgetRequest.getName()))) {
            throw new InvalidAttributeValueException("Budget name is invalid");
        }
        String createdId = BudgetServiceUtils.generateBudgetId();
        Budget budget = new Budget();
        budget.setId(createdId);
        budget.setName(createBudgetRequest.getName());
        budget.setBalance(createBudgetRequest.getBalance());

        budgetDao.saveBudget(budget);
        BudgetModel budgetModel = new ModelConverter().toBudgetModel(budget);

        return CreateBudgetResult.builder()
                .withBudget(budgetModel)
                .build();
    }
}
