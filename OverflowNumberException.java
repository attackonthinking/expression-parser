package expression.exceptions;

public class OverflowNumberException extends ParsingException {
    public OverflowNumberException(String message, String number) {
        super(String.format("%s for number: %s", message, number));
    }
}
