
$(document).ready(function () {
    console.log("Document ready!");
    var view = new GuessView("expression", "calculateBtn", "resolution");
    var service = new CalculateService("http://localhost:8080/calculator");
});

