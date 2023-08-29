package main.models;

import java.util.Objects;

public class SpendingCategoryModel {
    private String id;
    private String budgetId;
    private String name;
    private Integer spendingLimit;
    private Integer amountSpent;

    public SpendingCategoryModel() {
    }

    public SpendingCategoryModel(Builder builder) {
        this.id = builder.id;
        this.budgetId = builder.budgetId;
        this.name = builder.name;
        this.spendingLimit = builder.spendingLimit;
        this.amountSpent = builder.amountSpent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(String budgetId) {
        this.budgetId = budgetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        SpendingCategoryModel that = (SpendingCategoryModel) o;
        return id.equals(that.id) && budgetId.equals(that.budgetId) && name.equals(that.name) && spendingLimit.equals(that.spendingLimit) && amountSpent.equals(that.amountSpent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, budgetId, name, spendingLimit, amountSpent);
    }

    @Override
    public String toString() {
        return "SpendingCategoryModel{" +
                "id='" + id + '\'' +
                ", budgetId='" + budgetId + '\'' +
                ", name='" + name + '\'' +
                ", spendingLimit=" + spendingLimit +
                ", amountSpent=" + amountSpent +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String id;
        private String budgetId;
        private String name;
        private Integer spendingLimit;
        private Integer amountSpent;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withBudgetId(String budgetId) {
            this.budgetId = budgetId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSpendingLimit(Integer spendingLimit) {
            this.spendingLimit = spendingLimit;
            return this;
        }

        public Builder withAmountSpent(Integer amountSpent) {
            this.amountSpent = amountSpent;
            return this;
        }

        public SpendingCategoryModel build() { return new SpendingCategoryModel(this); }
    }
}
