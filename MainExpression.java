package expression;

public interface MainExpression extends Expression, TripleExpression, ListExpression, BigDecimalExpression, ToMiniString {
    void toString(StringBuilder newString);
    void toMiniString(StringBuilder newString);
    int getPriority();
}
