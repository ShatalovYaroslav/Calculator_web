
var CalculateService = function (serverURL) {
    this.serverURL = serverURL;
    var instance = this;
    $(document).bind(Events.USER_CALCULATE, function (e, data) {
        console.log("Calculation attempt detected by service!");
        instance.onCalculationAttempt(data.expression);
    });
    console.log("Binding has been successfully completed.");

};

CalculateService.prototype.onCalculationAttempt = function (expression) {
    var queryURL = this.serverURL /*+ "?userExpression=" + expression.value*/;

    $.getJSON(
        queryURL,
        {userExpression:expression.value},
        function (data) {
            console.log("Data have come on client : ");
            if (data.errorStatus) {
                console.log("Error in expression ");
                $(document).trigger(Events.ERROR_ANSWER, [data]);
            }
            else {
                console.log("Correct expression ");
                $(document).trigger(Events.CORRECT_ANSWER, [data]);
            }
        }
    );


};
