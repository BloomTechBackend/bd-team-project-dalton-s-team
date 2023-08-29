package main.models.results;

import main.models.BudgetModel;

public class GetBudgetResult {
    private BudgetModel budget;

    public GetBudgetResult(Builder builder) {
        this.budget = builder.budget;
    }

    public BudgetModel getBudget() {
        return budget;
    }

    public void setBudget(BudgetModel budget) {
        this.budget = budget;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private BudgetModel budget;

        public Builder withBudget(BudgetModel budgetToUse) {
            this.budget = budgetToUse;
            return this;
        }

        public GetBudgetResult build() {return new GetBudgetResult(this);}
    }
}
