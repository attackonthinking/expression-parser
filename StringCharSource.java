package expression.exceptions;

public class StringCharSource implements CharSource {
    private final String source;
    private int pos;

    public StringCharSource(String source) {
        this.source = source;
    }

    @Override
    public boolean hasNext() {
        return pos < source.length();
    }

    @Override
    public char next() {
        return source.charAt(pos++);
    }

    @Override
    public ParsingException error(final String message) {
        return new IllegalCharacterException(message, source, pos);
    }

    @Override
    public char back() {
        return source.charAt(pos == 0 ? pos : ((pos--) - 2));
    }

}
