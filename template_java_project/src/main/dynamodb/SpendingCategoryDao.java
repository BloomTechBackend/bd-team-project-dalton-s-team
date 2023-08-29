package main.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import main.dependency.DynamoDbClientProvider;
import main.dynamodb.models.SpendingCategory;
import main.exceptions.SpendingCategoryNotFoundException;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpendingCategoryDao {
    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public SpendingCategoryDao(DynamoDBMapper dynamoDBMapper) { this.dynamoDBMapper = dynamoDBMapper; }

    public SpendingCategory getSpendingCategory(String id) {
        SpendingCategory spendingCategory = this.dynamoDBMapper.load(SpendingCategory.class, id);

        if (spendingCategory == null) {
            throw new SpendingCategoryNotFoundException("Could not find Spending Category with id " + id);

        }
        return spendingCategory;
    }

    public SpendingCategory saveSpendingCategory(SpendingCategory spendingCategory) {
        if (spendingCategory == null) {
            throw new RuntimeException("Spending Category cannot be null.");
        }

        this.dynamoDBMapper.save(spendingCategory);
        return spendingCategory;
    }

    public List<SpendingCategory> getSpendingCategoryByBudgetId(String budgetId) {
//        SpendingCategory spendingCategory = new SpendingCategory();
//        spendingCategory.setBudgetId(budgetId);

//        DynamoDBQueryExpression<SpendingCategory> queryExpression = new DynamoDBQueryExpression()
//                .withHashKeyValues(spendingCategory)
//                .withScanIndexForward(false)
//                .withLimit(1);
        AmazonDynamoDB client = DynamoDbClientProvider.getDynamoDBClient();
        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":budget_id", new AttributeValue().withS(budgetId));
        ScanRequest scanRequest = new ScanRequest()
                .withTableName("spending_categories")
                .withFilterExpression("budget_id = :budget_id")
                .withExpressionAttributeValues(expressionAttributeValues);

        ScanResult result = client.scan(scanRequest);
        List<SpendingCategory> results = new ArrayList<>();
        for (Map<String, AttributeValue> s : result.getItems()) {
            SpendingCategory spendingCategory = new SpendingCategory();
            spendingCategory.setId(s.get("id").getS());
            spendingCategory.setBudgetId(s.get("budget_id").getS());
            spendingCategory.setName(s.get("name").getS());
            spendingCategory.setSpendingLimit(Integer.valueOf(s.get("spending_limit").getN()));
            spendingCategory.setAmountSpent(Integer.valueOf(s.get("amount_spent").getN()));
            results.add(spendingCategory);
        }
//        List<SpendingCategory> results = dynamoDBMapper.query(SpendingCategory.class, queryExpression);
        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

}
