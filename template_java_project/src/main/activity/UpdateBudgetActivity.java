package main.activity;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.converters.ModelConverter;
import main.dependency.DynamoDbClientProvider;
import main.dynamodb.BudgetDao;
import main.dynamodb.models.Budget;
import main.exceptions.BudgetNotFoundException;
import main.exceptions.InvalidAttributeValueException;
import main.models.BudgetModel;
import main.models.requests.UpdateBudgetRequest;
import main.models.results.UpdateBudgetResult;
import main.utils.BudgetServiceUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class UpdateBudgetActivity implements RequestHandler<UpdateBudgetRequest, UpdateBudgetResult> {
    private final Logger log = LogManager.getLogger();
    private final BudgetDao budgetDao;

    @Inject
    public UpdateBudgetActivity(BudgetDao budgetDao) {
        this.budgetDao = budgetDao;
    }

    public UpdateBudgetActivity() {
        this.budgetDao = new BudgetDao(new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_WEST_2)));
    }

    @Override
    public UpdateBudgetResult handleRequest(final UpdateBudgetRequest updateBudgetRequest, Context context) {
        log.info("Received UpdateBudgetRequest {}", updateBudgetRequest);
        if(!BudgetServiceUtils.isValidString(updateBudgetRequest.getName())) {
            throw new InvalidAttributeValueException("Budget name is invalid");
        }
        String requestedId = updateBudgetRequest.getId();
        Budget budget = budgetDao.getBudget(requestedId);
        if (budget == null) {
            throw new BudgetNotFoundException("A Budget with id " + updateBudgetRequest.getId() + " does not exist.");
        }
        budget.setName(updateBudgetRequest.getName());
        budget.setId(updateBudgetRequest.getId());
        budget.setBalance(updateBudgetRequest.getBalance());
        budgetDao.saveBudget(budget);
        BudgetModel budgetModel = new ModelConverter().toBudgetModel(budget);
        return UpdateBudgetResult.builder()
                .withBudget(budgetModel)
                .build();
    }
}
