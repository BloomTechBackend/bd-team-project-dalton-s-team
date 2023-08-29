package main.models.results;

import main.models.SpendingCategoryModel;

public class AddSpendingCategoryToBudgetResult {
    private SpendingCategoryModel spendingCategoryModel;

    public AddSpendingCategoryToBudgetResult(Builder builder) {this.spendingCategoryModel = builder.spendingCategoryModel;}

    public SpendingCategoryModel getSpendingCategoryModel() { return spendingCategoryModel; }

    public void setSpendingCategoryModel(SpendingCategoryModel spendingCategoryModel) {
        this.spendingCategoryModel = spendingCategoryModel;
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private SpendingCategoryModel spendingCategoryModel;

        public Builder withSpendingCategory(SpendingCategoryModel spendingCategoryToUse) {
            this.spendingCategoryModel = spendingCategoryToUse;
            return this;
        }

        public AddSpendingCategoryToBudgetResult build() { return new AddSpendingCategoryToBudgetResult(this); }
    }
}
