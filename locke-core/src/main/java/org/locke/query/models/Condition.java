package org.locke.query.models;

/**
 * @author David Gilmore
 * @date 5/16/14
 */
public abstract class Condition {

    protected LogicalOperator nextOp;
    protected Condition next;

    /**
     * Return the conjunctive normal form of the given conditions
     * @return
     */
    public abstract Condition cnf();

    public LogicalOperator getNextOp() {
        return nextOp;
    }

    public void setNextOp(LogicalOperator nextOp) {
        this.nextOp = nextOp;
    }

    public Condition getNext() {
        return next;
    }

    public void setNext(Condition next) {
        this.next = next;
    }
}
