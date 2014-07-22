package org.locke.query.models;

/**
 * @author David Gilmore
 * @date 5/16/14
 */
public class NestedCondition<T extends Condition> extends Condition {

    private T cond1;
    private T cond2;
    private LogicalOperator op;

    public NestedCondition condition(T condition) {
        this.cond1 = condition;
        return this;
    }

    public NestedCondition and(T and) {
        this.cond2 = and;
        this.op = LogicalOperator.AND;
        return this;
    }

    public NestedCondition or(T or) {
        this.cond2 = or;
        this.op = LogicalOperator.OR;
        return this;
    }

    public NestedCondition xor(T xor) {
        this.cond2 = xor;
        this.op = LogicalOperator.XOR;
        return this;
    }

    public T getCond1() {
        return cond1;
    }

    public T getCond2() {
        return cond2;
    }

    public LogicalOperator getOp() {
        return op;
    }

    @Override
    public Condition cnf() {

        NestedCondition nestedCondition = new NestedCondition();

        if (cond1 instanceof NestedCondition) {

            return null;
        }
        else {

            return null;
        }
    }

    private NestedCondition cnf(NestedCondition nestedCondition) {


        return null;
    }

    private SimpleCondition negate(SimpleCondition c) {

        Comparator o = c.getComparator();

        switch (o) {
            case EQ: c.setComparator(Comparator.NE); break;
            case NE: c.setComparator(Comparator.EQ); break;
            case GT: c.setComparator(Comparator.LE); break;
            case LT: c.setComparator(Comparator.GE); break;
            case GE: c.setComparator(Comparator.LT); break;
            case LE: c.setComparator(Comparator.GT); break;
        }

        return c;
    }
}
