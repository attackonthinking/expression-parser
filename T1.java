package expression;

public class T1 extends AbstractUnaryOperation{
    public T1(MainExpression operand) {
        super(operand);
    }

    @Override
    public int calc(int operand) {
        int res = 0;
        while (res < 32 && (operand & (1 << res)) != 0) res++;
        return res;
    }

    @Override
    public String getOperation() {
        return "t1";
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
