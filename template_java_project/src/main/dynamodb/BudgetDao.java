package main.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import main.dynamodb.models.Budget;
import main.exceptions.BudgetNotFoundException;

import javax.inject.Inject;

public class BudgetDao {
    private final DynamoDBMapper dynamoDbMapper;

    @Inject
    public BudgetDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    public Budget getBudget(String id) {
        Budget budget = this.dynamoDbMapper.load(Budget.class, id);

        if (budget == null) {
            throw new BudgetNotFoundException("Could not find Budget with id " + id);
        }

        return budget;
    }

    public Budget saveBudget(Budget budget) {
        if (budget == null) {
            throw new RuntimeException("Budget cannot be null.");
        }

        this.dynamoDbMapper.save(budget);
        return budget;
    }
}
