package expression.exceptions;

import expression.Divide;
import expression.MainExpression;

public class CheckedDivide extends Divide {
    public CheckedDivide(MainExpression firstOperand, MainExpression secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public int calc(int x, int y) {
        if (x == Integer.MIN_VALUE && y == -1) throw overflow(x, y);
        if (y == 0) {
            throw new DivisionByZeroException(String.format("%d / %d", x, y));
        }
        return super.calc(x, y);
    }
}
