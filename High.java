package expression;

public class High extends AbstractUnaryOperation {

    public High(MainExpression operand) {
        super(operand);
    }

    @Override
    public int calc(int operand) {
        return Integer.highestOneBit(operand);
    }

    @Override
    public String getOperation() {
        return "high";
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
