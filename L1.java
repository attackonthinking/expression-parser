package expression;

public class L1 extends AbstractUnaryOperation {
    public L1(MainExpression operand) {
        super(operand);
    }

    @Override
    public int calc(int operand) {
        int res = 0;
        while (res < 32 && (operand & (1 << (31 - res))) != 0) res++;
        return res;
    }

    @Override
    public String getOperation() {
        return "l1";
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
