package main.models.results;

import main.models.BudgetModel;

public class UpdateBudgetResult {
    private BudgetModel budget;

    public UpdateBudgetResult(Builder builder) { this.budget = builder.budget; }

    public BudgetModel getBudget() { return budget; }

    public void setBudget(BudgetModel budget) { this.budget = budget; }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private BudgetModel budget;

        public Builder withBudget(BudgetModel budgetToUse) {
            this.budget = budgetToUse;
            return this;
        }

        public UpdateBudgetResult build() { return new UpdateBudgetResult(this); }
    }
}
