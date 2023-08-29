const addSpendingCategoryForm = document.querySelector("#add-spending-category-form");
const scList = document.querySelector("#spending-categories");
const urlParams = new URLSearchParams(window.location.search);
const changeText = document.querySelector("#change-text");
const id = sessionStorage.getItem("id");
const updateSCForm = document.querySelector("#update-spending-category-form");

addSpendingCategoryForm.onsubmit = function(evt) {
  evt.preventDefault();
  const name = document.querySelector("#category-name").value;
  const spendingLimit = document.querySelector("#spending-limit").value;
  const amountSpent = document.querySelector("#amount-spent").value;
  const scObj = {
      "name": name,
      "budgetId": id,
      "spendingLimit": spendingLimit,
      "amountSpent": amountSpent
    }
  axios.post("https://n9ohm7k31b.execute-api.us-west-2.amazonaws.com/prod/budgets/"+id+"/categories", scObj, {
  }).then((res) => {
    console.log(res);
    window.location.reload();
  });
}

updateSCForm.onsubmit = async function(evt) {
    evt.preventDefault();
    const categoryId = document.querySelector("#category-id").value;
    const updateName = document.querySelector("#update-name").value;
    const updateLimit = document.querySelector("#update-spending-limit").value;
    const updateSpent = document.querySelector("#update-amount-spent").value;
    const updateObj = {
        "name": updateName,
        "budgetId": id,
        "spendingLimit": updateLimit,
        "amountSpent": updateSpent
    }
    axios.put("https://n9ohm7k31b.execute-api.us-west-2.amazonaws.com/prod/budgets/"+id+"/categories/"+categoryId, updateObj,{
    }).then((res) => {
       console.log(res);
       window.location.reload();
       });
}

window.onload = async function(evt) {
  evt.preventDefault();
  console.log("Getting Spending Category Data...");
  changeText.textContent = "Budget ID: " + id;
  axios.get("https://n9ohm7k31b.execute-api.us-west-2.amazonaws.com/prod/budgets/"+id+"/categories", {

  }).then(res => {
    console.log(res);
    if (!res.data) {
      throw "No data for budget with id:" + id;
    }

      res.data.spendingCategoryList.forEach(function(element) {
            console.log(element);
            let li = document.createElement("li");
            let a = document.createElement("a");
            let text = document.createTextNode(element.name + ": ID: " + element.id + ", BudgetID: " + element.budgetId + ", Spending Limit: " + element.spendingLimit + ", Amount Spent: " + element.amountSpent);

            a.appendChild(text);
            li.appendChild(a);
            scList.appendChild(li);
      });

  })
}

function populateSpendingCategories(spendingCategoryData) {

  for (let category in spendingCategoryData) {
    let li = document.createElement("li");
        let a = document.createElement("a");
        let text = document.createTextNode(category.name);

        a.appendChild(text);
        li.appendChild(a);
        scList.appendChild(li);
  }
}
