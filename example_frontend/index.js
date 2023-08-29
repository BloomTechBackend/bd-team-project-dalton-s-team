const budgetForm = document.querySelector("#create-budget-form");
const budgetsList = document.querySelector("#budgets");
const urlParams = new URLSearchParams(window.location.search);

budgetForm.onsubmit = async function(evt) {
  evt.preventDefault();
  const name = document.querySelector("#budget-name").value;
  const balance = document.querySelector("#balance").value;
  const budgetObj = {
    "name": name,
    "balance": balance,
  }
  axios.post("https://n9ohm7k31b.execute-api.us-west-2.amazonaws.com/prod/budgets", budgetObj, {
  }).then((res) => {
    console.log(res);
    sessionStorage.setItem("id", res.data.budget.id);
    window.location.href = "budget.html";
  })
}

