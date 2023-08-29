package main.models.requests;

import java.util.Objects;

public class GetBudgetRequest {
    private String id;

    public GetBudgetRequest() {

    }

    public GetBudgetRequest(Builder builder) { this.id = builder.id; }

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
        GetBudgetRequest that = (GetBudgetRequest) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "GetBudgetRequest{" +
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

        public GetBudgetRequest build() { return new GetBudgetRequest(this); }
    }
}
