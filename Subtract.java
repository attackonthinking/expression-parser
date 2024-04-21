package expression;

import java.math.BigDecimal;

public class Subtract extends AbstractBinaryOperation {

    public Subtract(MainExpression firstOperand, MainExpression secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public int calc(int firstOperand, int secondOperand) {
        return firstOperand - secondOperand;
    }

    @Override
    public BigDecimal calc(BigDecimal firstOperand, BigDecimal secondOperand) {
        return firstOperand.subtract(secondOperand);
    }

    @Override
    public boolean addCon(AbstractBinaryOperation exp) {
        return getPriority() == exp.getPriority();
    }

    @Override
    public String getOperation() {
        return "-";
    }

    @Override
    public int getPriority() {
        return 3;
    }
}
