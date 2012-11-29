
var UserExpression = function (attemptString) {
    this.value = attemptString;
};

var UserCalculateEvent = function (userExpression) {
    this.expression = userExpression;
};

var Events = {
    USER_CALCULATE:'user-calculate',
    CORRECT_ANSWER:'correct-answer',
    ERROR_ANSWER:'error-answer'
};

