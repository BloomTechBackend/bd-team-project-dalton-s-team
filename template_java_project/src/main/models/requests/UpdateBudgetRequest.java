package main.models.requests;

import java.util.Objects;

public class UpdateBudgetRequest {
    public String id;
    public String name;
    public Integer balance;

    public UpdateBudgetRequest() {

    }

    public UpdateBudgetRequest(String id, String name, Integer balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public UpdateBudgetRequest(Builder builder) {
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
        if (!(o instanceof UpdateBudgetRequest)) return false;
        UpdateBudgetRequest that = (UpdateBudgetRequest) o;
        return getId().equals(that.getId()) && getName().equals(that.getName()) && getBalance().equals(that.getBalance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBalance());
    }

    @Override
    public String toString() {
        return "UpdateBudgetRequest{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String id;
        private String name;
        private Integer balance;

        private Builder() {

        }

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

        public UpdateBudgetRequest build() { return new UpdateBudgetRequest(this); }
    }
}
