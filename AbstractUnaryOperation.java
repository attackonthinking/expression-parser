package expression;

import expression.exceptions.OverflowException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public abstract class AbstractUnaryOperation extends AbstractExp {
    final private MainExpression operand;

    public AbstractUnaryOperation(MainExpression operand) {
        this.operand = operand;
    }

    public abstract int calc(int operand);

    public abstract String getOperation();

    @Override
    public int evaluate(int x) {
        return calc(operand.evaluate(x));
    }

    @Override
    public int evaluate(List<Integer> listVariables) {
        return calc(operand.evaluate(listVariables));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calc(operand.evaluate(x, y, z));
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        throw new AssertionError("Can't evaluate for BigDecimal");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof AbstractUnaryOperation expObj) {
            return this.operand.equals(expObj.operand) &&
                    this.getClass().equals(o.getClass());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.operand, getClass()) * 999;
    }

    @Override
    public void toString(StringBuilder str) {
        str.append(getOperation()).append("(");
        operand.toString(str);
        str.append(")");
    }

    @Override
    public void toMiniString(StringBuilder str) {
        str.append(getOperation());
        if (this.getPriority() >= operand.getPriority()) {
            str.append(" ");
            operand.toMiniString(str);
        } else {
            str.append("(");
            operand.toMiniString(str);
            str.append(")");
        }
    }

    protected OverflowException overflow(int x){
        return new OverflowException(String.format("%s(%d)", getOperation(), x));
    }

}
