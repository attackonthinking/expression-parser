package expression;

import expression.exceptions.InvalidNameVariable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;


public class Variable extends AbstractExp implements expression.ListExpression {
    private String num;
    private int index;

    public Variable(String num) {
        this.num = num;
    }

    public Variable(int index) {
        this.index = index;
    }

    public Variable(String num, int index) {
        this(index);
        this.num = num;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object instanceof Variable expObj) {
            return this.num.equals(expObj.num);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num) * 999;
    }

    @Override
    public void toMiniString(StringBuilder str) {
        str.append(num);
    }

    @Override
    public void toString(StringBuilder str) {
        str.append(num);
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return switch (this.num) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new InvalidNameVariable(this.num);
        };
    }

    @Override
    public int evaluate(List<Integer> variables) {
        return variables.get(index);
    }
}
