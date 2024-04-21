package expression.exceptions;
import expression.exceptions.ParsingException;

public interface CharSource {
    boolean hasNext();
    char next();
    char back();
    ParsingException error(final String message);
}
