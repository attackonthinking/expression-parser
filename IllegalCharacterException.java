package expression.exceptions;

public class IllegalCharacterException extends ParsingException {
    public IllegalCharacterException(String message, String expression, int pos) {
        super(message, expression, pos);
    }

}
