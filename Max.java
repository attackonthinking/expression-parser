package expression;

import java.math.BigDecimal;

public class Max extends AbstractBinaryOperation {
    public Max(MainExpression firstOperand, MainExpression secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public int calc(int firstOperand, int secondOperand) {
        return Math.max(firstOperand, secondOperand);
    }

    @Override
    public BigDecimal calc(BigDecimal firstOperand, BigDecimal secondOperand) {
        throw new AssertionError("Can't calculate max for BigDecimal");
    }

    @Override
    public boolean addCon(AbstractBinaryOperation exp) {
        return exp.getOperation().equals("min");
    }

    @Override
    public String getOperation() {
        return "max";
    }

    @Override
    public int getPriority() {
        return 7;
    }
}
