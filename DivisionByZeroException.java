package expression.exceptions;

public class DivisionByZeroException extends ExpressionException {
    public DivisionByZeroException(String exp) {
        super("division by zero", exp);
    }
}
