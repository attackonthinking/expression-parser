package expression.exceptions;
import expression.MainExpression;
import expression.Subtract;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(MainExpression firstOperand, MainExpression secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public int calc(int x, int y) {
        if (y > 0 && x < Integer.MIN_VALUE + y || y < 0 && x > Integer.MAX_VALUE + y) {
            throw overflow(x, y);
        }
        return super.calc(x, y);
    }
}
