package main.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "spending_categories")
public class SpendingCategory {
    public String id;
    public String budgetId;
    public String name;
    public Integer spendingLimit;
    public Integer amountSpent;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "budget_id")
    public String getBudgetId() { return budgetId; }

    public void setBudgetId(String budgetId) { this.budgetId = budgetId; }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "spending_limit")
    public Integer getSpendingLimit() {
        return spendingLimit;
    }

    public void setSpendingLimit(Integer spendingLimit) {
        this.spendingLimit = spendingLimit;
    }

    @DynamoDBAttribute(attributeName = "amount_spent")
    public Integer getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(Integer amountSpent) {
        this.amountSpent = amountSpent;
    }
}
