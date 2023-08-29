package main.models.requests;

import java.util.Objects;

public class GetBudgetSpendingCategoriesRequest {
    private String id;

    public GetBudgetSpendingCategoriesRequest() {
    }

    public GetBudgetSpendingCategoriesRequest(String id) {
        this.id = id;
    }

    public GetBudgetSpendingCategoriesRequest(Builder builder) {
        this.id = builder.id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetBudgetSpendingCategoriesRequest that = (GetBudgetSpendingCategoriesRequest) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "GetBudgetSpendingCategoriesRequest{" +
                "id='" + id + '\'' +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String id;

        private Builder() {
        }

        public Builder withId(String idToUse) {
            this.id = idToUse;
            return this;
        }

        public GetBudgetSpendingCategoriesRequest build() { return new GetBudgetSpendingCategoriesRequest(this); }
    }
}
