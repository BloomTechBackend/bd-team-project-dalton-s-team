package main.models.requests;

import java.util.Objects;

public class UpdateSpendingCategoryRequest {
    private String id;
    private String budgetId;
    private String name;
    private Integer spendingLimit;
    private Integer amountSpent;

    public UpdateSpendingCategoryRequest() {
    }

    public UpdateSpendingCategoryRequest(String id, String budgetId, String name, Integer spendingLimit, Integer amountSpent) {
        this.id = id;
        this.budgetId = budgetId;
        this.name = name;
        this.spendingLimit = spendingLimit;
        this.amountSpent = amountSpent;
    }

    public UpdateSpendingCategoryRequest(UpdateSpendingCategoryRequest.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.budgetId = builder.budgetId;
        this.spendingLimit = builder.spendingLimit;
        this.amountSpent = builder.amountSpent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        UpdateSpendingCategoryRequest that = (UpdateSpendingCategoryRequest) o;
        return id.equals(that.id) && budgetId.equals(that.budgetId) && name.equals(that.name) && spendingLimit.equals(that.spendingLimit) && amountSpent.equals(that.amountSpent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, budgetId, name, spendingLimit, amountSpent);
    }

    @Override
    public String toString() {
        return "UpdateSpendingCategoryRequest{" +
                "id='" + id + '\'' +
                ", budgetId='" + budgetId + '\'' +
                ", name='" + name + '\'' +
                ", spendingLimit=" + spendingLimit +
                ", amountSpent=" + amountSpent +
                '}';
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String id;
        private String budgetId;
        private String name;
        private Integer spendingLimit;
        private Integer amountSpent;

        private Builder() {
        }

        public Builder withId(String idToUse) {
            this.id = idToUse;
            return this;
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

        public UpdateSpendingCategoryRequest build() { return new UpdateSpendingCategoryRequest(this); }

    }
}
