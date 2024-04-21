package expression;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Const extends AbstractExp {
    private final Number num;

    public Const(int num) {
        this.num = num;
    }

    public Const(BigDecimal num) {
        this.num = num;
    }

    @Override
    public void toString(StringBuilder str) {
        str.append(num);
    }

    @Override
    public void toMiniString(StringBuilder str) {
        str.append(num);
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object instanceof Const expObj) {
            return this.num.equals(expObj.num);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num) * 999;
    }

    @Override
    public int evaluate(int x) {
        return (int) this.num;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (int) this.num;
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return (BigDecimal) this.num;
    }

    @Override
    public int evaluate(List<Integer> variables) {
        return (int) this.num;
    }
}