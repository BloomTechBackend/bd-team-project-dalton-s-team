const idForm = document.querySelector("#enter-id");

idForm.onsubmit = async function(evt) {
  evt.preventDefault();
  const budgetId = document.querySelector("#budget-id").value;
  sessionStorage.setItem("id", budgetId);
  axios.get("https://n9ohm7k31b.execute-api.us-west-2.amazonaws.com/prod/budgets/"+budgetId, {
  }).then((res) => {
    console.log(res);
    window.location.href = "budget.html";
  })
}
