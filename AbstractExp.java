package expression;

public abstract class AbstractExp implements MainExpression {
    final StringBuilder str = new StringBuilder();
    final StringBuilder strMini = new StringBuilder();

    @Override
    public String toString() {
        if (str.isEmpty()) toString(str);
        return str.toString();
    }

    @Override
    public String toMiniString() {
        if (strMini.isEmpty()) toMiniString(strMini);
        return strMini.toString();
    }

}
