package expression.exceptions;

public class InvalidNameVariable extends ExpressionException {
    public InvalidNameVariable(String name) {
        super(String.format("Invalid name variable : %s", name));
    }
}
