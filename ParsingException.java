package expression.exceptions;

public class ParsingException extends Exception {
    public ParsingException(String message) {
        super(message);
    }
    public ParsingException(String message, String expression, int pos){
        this(String.format("Parsing error at position: %d (symbol : '%c') in expression '%s' : %s",
                pos, expression.charAt(pos - 1), expression, message));
    }

}
