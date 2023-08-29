# Dalton's Design Document

## Financial Fitness Design

## 1. Problem Statement

Financial Fitness is a budgeting service that will help its users create a budget
and then track their spending in each category. Financial fitness will allow users to create
their own spending categories. Users will be able to update their income and spending.


## 2. Top Questions to Resolve in Review

1.   How would I be able to incorporate real bank data into my application?
2.   
3.  

## 3. Use Cases

U1. As a customer, I want to create a new,
empty budget with a name and list of spending categories

U2. As a customer, I want to retrieve my budget with a given ID.
    
U3. As a customer, I want to be able to update my budget name and balance.

U4. As a customer, I want to be able to add or remove a spending category.

U5. As a customer, I want to be able to enter my spending into a spending category.

U6. As a customer, I want to be able to retrieve how much I have spent in a spending category.

## 4. Project Scope

### 4.1. In Scope

* Creating, retrieving, and updating a budget
* Adding and retrieving a budgets list of spending categories

### 4.2. Out of Scope

* Integration with banking clients

# 5. Proposed Architecture Overview

The minimum loveable product will include creating, retrieving, and updating a budget,
as well as adding to and retrieving spending categories.

We will use API Gateway and Lambda to create five endpoints (`GetBudget`, `CreateBudget`,
`UpdateBudget`, `AddSpendingCategoryToBudget`, and `GetBudgetSpendingCategories`)

# 6. API

## 6.1. Public Models

```
// BudgetModel

String id;
String name;
Integer balance;
```

```
// SpendingCategoryModel

String id;
String budgetId;
String name;
Integer spendingLimit;
Integer amountSpent;
```

## 6.2. Get Budget Endpoint

* Accepts `GET` requests to `/budgets/:id`
* Accepts a budget ID and returns the corresponding BudgetModel.
  * If the given budget id is not found, will throw a `BudgetNotFoundException`


## 6.3 Create Budget Endpoint

* Accepts `POST` requests to `/budgets`
* Accepts data to create a new budget with provided name and income.
Returns new budget, with a unique budget ID.

## 6.4 Update Budget Endpoint

* Accepts `PUT` requests to `budgets/:id`
* Accepts data to update a budget including playlist ID, an updated name, and an updated balance
  * If the budget ID is not found, will throw a `BudgetNotFoundException`

## 6.5 Add SpendingCategory to Budget Endpoint

* Accepts `POST` requests to `/budgets/:id/categories`
* Accepts a budget ID and a category to be added. The spending category will be created and given a unique ID.
  * If the budget is not found, will throw a BudgetNotFoundException

## 6.6 Get Budget SpendingCategories Endpoint

* Accepts `GET` requests to `/budgets/:id/categories`
* Retrieves all spending categories of a budget with the given budget ID
  * Returns the spending category list.
* If the budget is found but has no spending categories the list will be empty
* If the budget ID is not found. will throw a `BudgetNotFoundException`


# 7. Tables

## 7.1 `budgets`

```
id // parition key, string
name // string
balance // number
spendingCategoryList // list
```

## 7.2 `spending_categories`

```
id // parition key, string
budgetId // sort key, string
name // string
spendingLimit // number
amountSpent // number
```

## 7.3 `customer_ids`

```
id // parition key, string
name // string
```

# 8. Pages

*Include mock-ups of the web pages you expect to build. These can be as
sophisticated as mockups/wireframes using drawing software, or as simple as
hand-drawn pictures that represent the key customer-facing components of the
pages. It should be clear what the interactions will be on the page, especially
where customers enter and submit data. You may want to accompany the mockups
with some description of behaviors of the page (e.g. “When customer submits the
submit-dog-photo button, the customer is sent to the doggie detail page”)*
