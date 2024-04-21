package expression.exceptions;

import expression.Add;
import expression.MainExpression;

public class CheckedAdd extends Add {
    public CheckedAdd(MainExpression firstOperand, MainExpression secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public int calc(int x, int y) {
        if (y > 0 && x > Integer.MAX_VALUE - y || y < 0 && x < Integer.MIN_VALUE - y) {
            throw overflow(x, y);
        }
        return super.calc(x, y);
    }

}
