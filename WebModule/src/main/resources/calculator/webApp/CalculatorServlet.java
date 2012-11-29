package calculator.webApp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CalculatorServlet extends HttpServlet {

    public static final String USER_EXPRESSION_REQUEST_PARAM = "userExpression";
    public static final String JSON_MIME_TYPE = "application/json";


    /**
     * @param req  sent to "calculator?attempt=EXPRESSION"
     * @param resp JSON object
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String userExpression = req.getParameter(USER_EXPRESSION_REQUEST_PARAM);
        final CalculatorResponse calcResponse = new CalculatorResponse(userExpression);

        PrintWriter writer = resp.getWriter();
        writer.print(calcResponse.getResponse());
        resp.setContentType(JSON_MIME_TYPE);
        resp.setStatus(200);
    }

}
