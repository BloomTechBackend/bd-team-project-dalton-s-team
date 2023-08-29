package main.models.requests;

import java.util.Objects;

public class CreateBudgetRequest {
    private String name;
    private Integer balance;

    public CreateBudgetRequest(String name, Integer balance) {
        this.name = name;
        this.balance = balance;
    }

    public CreateBudgetRequest() {
    }

    public CreateBudgetRequest(Builder builder) {
        this.name = builder.name;
        this.balance = builder.balance;
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
        CreateBudgetRequest that = (CreateBudgetRequest) o;
        return getName().equals(that.getName()) && getBalance().equals(that.getBalance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBalance());
    }

    @Override
    public String toString() {
        return "CreateBudgetRequest{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String name;
        private Integer balance;

        private Builder() {

        }

        public Builder withName(String nameToUse) {
            this.name = nameToUse;
            return this;
        }

        public Builder withBalance(Integer balanceToUse) {
            this.balance = balanceToUse;
            return this;
        }

        public CreateBudgetRequest build() { return new CreateBudgetRequest(this); }
    }
}
