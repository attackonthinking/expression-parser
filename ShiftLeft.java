package expression;

import java.math.BigDecimal;

public class ShiftLeft extends AbstractBinaryOperation {
    public ShiftLeft(MainExpression firstOperand, MainExpression secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public int calc(int firstOperand, int secondOperand) {
        return firstOperand << secondOperand;
    }

    @Override
    public BigDecimal calc(BigDecimal firstOperand, BigDecimal secondOperand) {
        throw new AssertionError("Can't calculate << for BigDecimal");
    }

    @Override
    public boolean addCon(AbstractBinaryOperation exp) {
        return getPriority() == exp.getPriority();
    }

    @Override
    public String getOperation() {
        return "<<";
    }

    @Override
    public int getPriority() {
        return 7;
    }
}
