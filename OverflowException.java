package expression.exceptions;

public class OverflowException extends ExpressionException {
    public OverflowException(String exp) {
        super("Overflow", exp);
    }
}
