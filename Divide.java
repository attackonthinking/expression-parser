package expression;

import java.math.BigDecimal;

public class Divide extends AbstractBinaryOperation {
    public Divide(MainExpression firstOperand, MainExpression secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public int calc(int firstOperand, int secondOperand) {
        return firstOperand / secondOperand;
    }

    @Override
    public BigDecimal calc(BigDecimal firstOperand, BigDecimal secondOperand) {
        return firstOperand.divide(secondOperand);
    }

    @Override
    public boolean addCon(AbstractBinaryOperation exp) {
        return true;
    }

    @Override
    public String getOperation() {
        return "/";
    }

    @Override
    public int getPriority() {
        return 2;
    }
}
