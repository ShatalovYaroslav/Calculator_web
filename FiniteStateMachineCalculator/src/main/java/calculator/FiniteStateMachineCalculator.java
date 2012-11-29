package calculator;

import calculator.stateMachine.AbstractStateMachine;

import java.math.BigDecimal;

public class FiniteStateMachineCalculator
        extends AbstractStateMachine<MachineState,
        EvaluationContext,
        MachineTransitionMatrix,
        MathExpressionEvaluator,
        EvaluationException,
        BigDecimal
        >
        implements MathExpressionCalculator {

    private static final MachineTransitionMatrix MATRIX =
            new MachineTransitionMatrix();

    private static final MathExpressionEvaluator EVALUATOR =
            new MathExpressionEvaluator();

    @Override
    public BigDecimal evaluate(String mathExpression) throws EvaluationException {

        EvaluationContext context = new EvaluationContext(mathExpression);
        try{
            return run(context);
        }catch (Exception ex){
            throw new EvaluationException(ex.getMessage(),
                    context.getCurrentPosition());
        }
    }

    @Override
    protected MachineTransitionMatrix getTransitionMatrix() {
        return MATRIX;
    }

    @Override
    protected MathExpressionEvaluator getStateRecognizer() {
        return EVALUATOR;
    }

    @Override
    protected void deadlock(EvaluationContext context) throws EvaluationException {
        throw new EvaluationException("Invalid expression format.",
                context.getCurrentPosition());
    }
}
