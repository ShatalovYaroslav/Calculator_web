package calculator;

import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class CommonCasesTest {

    private static final MathExpressionCalculator calculator =
            new FiniteStateMachineCalculator();

    @Test(expected = EvaluationException.class)
    public void emptyExpressionTest() throws Exception {

        calculator.evaluate("");
        fail("Expected exception wasn't thrown.");
    }

    @Test
    public void onlyNumberTest() throws Exception {
        final BigDecimal result = calculator.evaluate(" 158 ");
        assertEquals("Number wasn't evaluated correctly.",
                new BigDecimal(158), result);
    }

    @Test
    public void negativeNumberTest() throws Exception {
        final BigDecimal result = calculator.evaluate(" -300 ");
        assertEquals("Negative number wasn't evaluated correctly.",
                new BigDecimal(-300), result);
    }

    @Test
    public void evaluationExceptionTest() throws Exception {

        try {
            calculator.evaluate("300aaa");
            fail("Expected exception wasn't thrown.");
        } catch (EvaluationException e) {
            assertEquals("Wrong error position reported.",
                    3, e.getErrorPosition());
        }
    }

    @Test
    public void realNumberTest() throws Exception {
        final BigDecimal result = calculator.evaluate(" 30.8 ");
        assertEquals("Negative number wasn't evaluated correctly.",
                new BigDecimal(30.8), result);
    }


}
