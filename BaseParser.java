package expression.exceptions;

public class BaseParser {
    private final static char END = 0;
    protected final CharSource source;
    private char ch;

    protected BaseParser(final CharSource source) {
        this.source = source;
        take();
    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected ParsingException error(final String message) {
        return source.error(message);
    }

    protected char take() {
        final char res = ch;
        ch = source.hasNext() ? source.next() : END;
        return res;
    }

    protected void back() {
        ch = source.back();
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    protected void expect(char expected) throws ParsingException {
        if (!take(expected)) {
            throw error(String.format("Expected '%s', found '%s'", expected,
                    (ch != END) ? ch : "end of source"));
        }
    }

    protected void expectWhiteSpace() throws ParsingException {
        if (!(Character.isWhitespace(ch) || ch == END)) {
            throw error(String.format("Expected whitespace, found '%s'", ch));
        }
        take();
    }

    protected boolean between(final char left, final char right) {
        return left <= ch && ch <= right;
    }

    protected boolean isEOS() {
        return test(END);
    }

    protected void skipWhitespaces() {
        while (Character.isWhitespace(ch)) {
            take();
        }
    }

}
