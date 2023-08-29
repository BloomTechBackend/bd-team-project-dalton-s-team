package main.models.requests;

import main.models.results.AddSpendingCategoryToBudgetResult;

import java.util.Objects;

public class AddSpendingCategoryToBudgetRequest {
    private String budgetId;
    private String name;
    private Integer spendingLimit;
    private Integer amountSpent;

    public AddSpendingCategoryToBudgetRequest() {
    }

    public AddSpendingCategoryToBudgetRequest(String id, String budgetId, String name, Integer spendingLimit, Integer amountSpent) {
        this.budgetId = budgetId;
        this.name = name;
        this.spendingLimit = spendingLimit;
        this.amountSpent = amountSpent;
    }

    public AddSpendingCategoryToBudgetRequest(Builder builder) {
        this.name = builder.name;
        this.budgetId = builder.budgetId;
        this.spendingLimit = builder.spendingLimit;
        this.amountSpent = builder.amountSpent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(String budgetId) {
        this.budgetId = budgetId;
    }

    public Integer getSpendingLimit() {
        return spendingLimit;
    }

    public void setSpendingLimit(Integer spendingLimit) {
        this.spendingLimit = spendingLimit;
    }

    public Integer getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(Integer amountSpent) {
        this.amountSpent = amountSpent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddSpendingCategoryToBudgetRequest that = (AddSpendingCategoryToBudgetRequest) o;
        return budgetId.equals(that.budgetId) && name.equals(that.name) && spendingLimit.equals(that.spendingLimit) && amountSpent.equals(that.amountSpent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(budgetId, name, spendingLimit, amountSpent);
    }

    @Override
    public String toString() {
        return "AddSpendingCategoryToBudgetRequest{" +
                "budgetId='" + budgetId + '\'' +
                ", name='" + name + '\'' +
                ", spendingLimit=" + spendingLimit +
                ", amountSpent=" + amountSpent +
                '}';
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String budgetId;
        private String name;
        private Integer spendingLimit;
        private Integer amountSpent;

        private Builder() {
        }

        public Builder withBudgetId(String budgetIdToUse) {
            this.budgetId = budgetIdToUse;
            return this;
        }

        public Builder withName(String nameToUse) {
            this.name = nameToUse;
            return this;
        }

        public Builder withSpendingLimit(Integer spendingLimitToUse) {
            this.spendingLimit = spendingLimitToUse;
            return this;
        }

        public Builder withAmountSpent(Integer amountSpentToUse) {
            this.amountSpent = amountSpentToUse;
            return this;
        }

        public AddSpendingCategoryToBudgetRequest build() { return new AddSpendingCategoryToBudgetRequest(this); }

    }
}
