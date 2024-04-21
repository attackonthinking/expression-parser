package expression;

public class Low extends AbstractUnaryOperation {
    public Low(MainExpression operand) {
        super(operand);
    }

    @Override
    public int calc(int operand) {
        return Integer.lowestOneBit(operand);
    }

    @Override
    public String getOperation() {
        return "low";
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
