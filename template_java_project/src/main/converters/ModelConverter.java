package main.converters;


import main.dynamodb.models.Budget;
import main.dynamodb.models.SpendingCategory;
import main.models.BudgetModel;
import main.models.SpendingCategoryModel;

import java.util.ArrayList;

public class ModelConverter {

    public BudgetModel toBudgetModel(Budget budget) {

        return BudgetModel.builder()
                .withId(budget.getId())
                .withName(budget.getName())
                .withBalance(budget.getBalance())
                .build();

    }

    public SpendingCategoryModel toSpendingCategoryModel(SpendingCategory spendingCategory) {
        return SpendingCategoryModel.builder()
                .withId(spendingCategory.getId())
                .withBudgetId(spendingCategory.getBudgetId())
                .withName(spendingCategory.getName())
                .withSpendingLimit(spendingCategory.getSpendingLimit())
                .withAmountSpent(spendingCategory.getAmountSpent())
                .build();
    }
}
