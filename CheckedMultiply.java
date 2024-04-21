package expression.exceptions;

import expression.MainExpression;
import expression.Multiply;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(MainExpression firstOperand, MainExpression secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public int calc(int x, int y) {
        if (isOverflow(x, y)) {
            throw overflow(x, y);
        }
        return super.calc(x, y);
    }

    private boolean isOverflow(int x, int y) {
        return (x > 0 && y > 0 && x > Integer.MAX_VALUE / y) ||
                (x > 0 && y < 0 && y < Integer.MIN_VALUE / x) ||
                (x < 0 && y > 0 && x < Integer.MIN_VALUE / y) ||
                (x < 0 && y < 0 && y < Integer.MAX_VALUE / x);
    }
}
