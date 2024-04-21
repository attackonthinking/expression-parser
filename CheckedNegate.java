package expression.exceptions;

import expression.MainExpression;
import expression.Negate;

public class CheckedNegate extends Negate {
    public CheckedNegate(MainExpression operand) {
        super(operand);
    }

    @Override
    public int calc(int x) {
        if (x == Integer.MIN_VALUE) {
            throw overflow(x);
        }
        return super.calc(x);
    }
}
