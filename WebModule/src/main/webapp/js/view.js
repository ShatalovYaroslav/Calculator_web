

var GuessView = function (inputFieldID, buttonID, resolutionID) {
    this.inputFieldID = inputFieldID;
    this.resolutionID = resolutionID;
    var instance = this;
    $("#" + buttonID).click(function () {
        instance.onUserSubmittedCalculation();
    });

    $(document).bind(Events.CORRECT_ANSWER,
        function onServerResponseCorrect(e, event) {
            console.log("onServerResponse event binded! Success " + event.result);
            var resolution = "The answer is: " + event.result;
            $("#" + instance.resolutionID).html(resolution);
        });

    $(document).bind(Events.ERROR_ANSWER,
        function onServerResponseError(e, event) {
            console.log("onServerResponse event binded! Error");
            var resolution = "There is the error: " + event.errorMessage;
            instance.setErrorPosition(event.errorPosition);
            $("#" + instance.resolutionID).html(resolution);
        });
};

GuessView.prototype.setErrorPosition = function (errorPosit) {
    var textField = document.getElementById(this.inputFieldID);

    if (textField != null) {
        if (textField.createTextRange) {
            var r = textField.createTextRange();
            r.move('character', errorPosit);
            r.select();
        }
        else {
            if (textField.selectionStart) {
                textField.focus();
                textField.setSelectionRange(errorPosit, errorPosit);
            }
            else
                textField.focus();
        }
    }
};


GuessView.prototype.onUserSubmittedCalculation = function () {
    console.log("User clicked the button!");

    var inputValue = $("#" + this.inputFieldID).val();
    var attempt = new UserExpression(inputValue);
    var event = new UserCalculateEvent(attempt);
    $(document).trigger(Events.USER_CALCULATE, [event]);
    console.log("Guess attempt event has been triggered by the view...");
};


