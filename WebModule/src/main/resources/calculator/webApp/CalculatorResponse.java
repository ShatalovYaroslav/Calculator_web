package calculator.webApp;

import calculator.EvaluationException;
import calculator.FiniteStateMachineCalculator;
import calculator.MathExpressionCalculator;
import java.math.BigDecimal;

public class CalculatorResponse {
    private static final String ERROR_STATUS = "\"errorStatus\"";
    private static final String RESULT = "\"result\"";
    private static final String ERROR_POSITION = "\"errorPosition\"";
    private static final String ERROR_MESSAGE = "\"errorMessage\"";
    private String response;

    final MathExpressionCalculator calculator = new FiniteStateMachineCalculator();

    public CalculatorResponse(String expression) {
        response = "";

        System.out.println("Math Expression Calculator");
        try {
            final BigDecimal result;
            result = calculator.evaluate(expression);
            response += "{" + ERROR_STATUS + ":false,"
                    + RESULT + ":" + result.doubleValue() + "}";

        } catch (EvaluationException e) {
            String message = e.getLocalizedMessage()
                    .replaceAll("\\r\\n|\\n\\r|\\r|\\n", "<br/>")
                    .replaceAll("'", "`");

            response += "{" + ERROR_STATUS + ":true,"
                    + ERROR_POSITION + ":" + e.getErrorPosition() + ","
                    + ERROR_MESSAGE + ":\"" + message + "\"}";
        }
    }

    public String getResponse() {
        return response;
    }
}

