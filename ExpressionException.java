package expression.exceptions;

public class ExpressionException extends RuntimeException {
    public ExpressionException(String message) {
        super(message);
    }
    public ExpressionException(String message, String exp){
        this(String.format("%s: attempt to calculate: %s", message, exp));
    }

}
