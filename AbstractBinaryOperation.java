package expression;

import expression.exceptions.OverflowException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public abstract class AbstractBinaryOperation extends AbstractExp {
    final private MainExpression firstOperand;
    final private MainExpression secondOperand;

    public AbstractBinaryOperation(MainExpression firstOperand, MainExpression secondOperand) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    @Override
    public int evaluate(int x) {
        return calc(firstOperand.evaluate(x), secondOperand.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calc(firstOperand.evaluate(x, y, z), secondOperand.evaluate(x, y, z));
    }

    @Override
    public int evaluate(List<Integer> listVariables) {
        return calc(firstOperand.evaluate(listVariables), secondOperand.evaluate(listVariables));
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return calc(firstOperand.evaluate(x), secondOperand.evaluate(x));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof AbstractBinaryOperation expObj) {
            return this.firstOperand.equals(expObj.firstOperand) &&
                    this.secondOperand.equals(expObj.secondOperand) &&
                    this.getClass().equals(o.getClass());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.firstOperand, getClass(), this.secondOperand) * 999;
    }

    public abstract int calc(int firstOperand, int secondOperand);

    public abstract BigDecimal calc(BigDecimal firstOperand, BigDecimal secondOperand);

    public abstract boolean addCon(AbstractBinaryOperation exp);

    public abstract String getOperation();

    @Override
    public void toString(StringBuilder str) {
        str.append('(');
        firstOperand.toString(str);
        str.append(' ').append(getOperation()).append(' ');
        secondOperand.toString(str);
        str.append(')');
    }

    @Override
    public void toMiniString(StringBuilder str) {
        boolean firstBrackets = isFirstBrackets(firstOperand);
        boolean secondBrackets = isSecondBrackets(secondOperand);
        str.append(firstBrackets ? "(" : "");
        firstOperand.toMiniString(str);
        str.append(firstBrackets ? ")" : "").
                append(' ').append(getOperation()).append(' ').append(secondBrackets ? "(" : "");
        secondOperand.toMiniString(str);
        str.append(secondBrackets ? ")" : "");
    }

    private boolean isFirstBrackets(MainExpression expression) {
        if (expression instanceof AbstractBinaryOperation exp) {
            return exp.getPriority() > this.getPriority();
        }
        return false;
    }

    private boolean isSecondBrackets(MainExpression expression) {
        if (expression instanceof AbstractBinaryOperation exp) {
            return exp.getPriority() > getPriority() ||
                    exp.getOperation().equals("/") && getPriority() == exp.getPriority()
                    || addCon(exp);
        }
        return false;
    }

    protected OverflowException overflow(int x, int y) {
        return new OverflowException(String.format("%d %s %d", x, getOperation(), y));
    }
}
