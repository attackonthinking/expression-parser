package expression;

import java.math.BigDecimal;

public class Multiply extends AbstractBinaryOperation {

    public Multiply(MainExpression firstOperand, MainExpression secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public int calc(int firstOperand, int secondOperand) {
        return firstOperand * secondOperand;
    }

    @Override
    public BigDecimal calc(BigDecimal firstOperand, BigDecimal secondOperand) {
        return firstOperand.multiply(secondOperand);
    }

    @Override
    public boolean addCon(AbstractBinaryOperation exp) {
        return false;
    }

    @Override
    public String getOperation() {
        return "*";
    }

    @Override
    public int getPriority() {
        return 2;
    }
}
