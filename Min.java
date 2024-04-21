package expression;

import java.math.BigDecimal;

public class Min extends AbstractBinaryOperation {
    public Min(MainExpression firstOperand, MainExpression secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public int calc(int firstOperand, int secondOperand) {
        return Math.min(firstOperand, secondOperand);
    }

    @Override
    public BigDecimal calc(BigDecimal firstOperand, BigDecimal secondOperand) {
        throw new AssertionError("Can't calculate min for BigDecimal");
    }

    @Override
    public boolean addCon(AbstractBinaryOperation exp) {
        return exp.getOperation().equals("max");
    }

    @Override
    public String getOperation() {
        return "min";
    }

    @Override
    public int getPriority() {
        return 7;
    }
}
