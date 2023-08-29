package main.models.results;

import main.models.SpendingCategoryModel;

import java.util.List;

public class GetBudgetSpendingCategoriesResult {
    private List<SpendingCategoryModel> spendingCategoryList;

    public GetBudgetSpendingCategoriesResult(Builder builder) {this.spendingCategoryList = builder.spendingCategoryList;}

    public List<SpendingCategoryModel> getSpendingCategoryList() {return spendingCategoryList;}

    public void setSpendingCategoryList(List<SpendingCategoryModel> spendingCategoryList) {
        this.spendingCategoryList = spendingCategoryList;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<SpendingCategoryModel> spendingCategoryList;

        public Builder withSpendingCategoryList(List<SpendingCategoryModel> spendingCategoryListToUse) {
            this.spendingCategoryList = spendingCategoryListToUse;
            return this;
        }

        public GetBudgetSpendingCategoriesResult build() {
            return new GetBudgetSpendingCategoriesResult(this);
        }
    }
}
