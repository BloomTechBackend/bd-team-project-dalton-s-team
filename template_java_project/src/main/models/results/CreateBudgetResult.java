package main.models.results;

import main.models.BudgetModel;

public class CreateBudgetResult {
    private BudgetModel budget;

    public CreateBudgetResult(Builder builder) {
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

        public Builder withBudget(BudgetModel budget) {
            this.budget = budget;
            return this;
        }

        public CreateBudgetResult build() {return new CreateBudgetResult(this);}
    }
}
