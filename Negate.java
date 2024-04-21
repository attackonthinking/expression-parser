package expression;

public class Negate extends AbstractUnaryOperation {
    public Negate(MainExpression operand) {
        super(operand);
    }

    @Override
    public int calc(int operand) {
        return -operand;
    }

    @Override
    public String getOperation() {
        return "-";
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
