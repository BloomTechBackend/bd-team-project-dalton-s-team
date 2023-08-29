package main.models;

import com.amazonaws.internal.config.Builder;
import main.dynamodb.models.SpendingCategory;

import java.util.List;
import java.util.Objects;

public class BudgetModel {
    private String id;
    private String name;
    private Integer balance;

    public BudgetModel() {

    }

    public BudgetModel(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.balance = builder.balance;

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

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetModel that = (BudgetModel) o;
        return id.equals(that.id) && name.equals(that.name) && balance.equals(that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, balance);
    }

    @Override
    public String toString() {
        return "BudgetModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String id;
        private String name;
        private Integer balance;
        private List<SpendingCategory> spendingCategories;

        public Builder withId(String idToUse) {
            this.id = idToUse;
            return this;
        }

        public Builder withName(String nameToUse) {
            this.name = nameToUse;
            return this;
        }

        public Builder withBalance(Integer balanceToUse) {
            this.balance = balanceToUse;
            return this;
        }

        public BudgetModel build() { return new BudgetModel(this); }
    }
}
