package com.locke.olap.models;

import java.util.LinkedList;
import java.util.List;

/**
 * @author David Gilmore
 * @date 5/16/14
 */
public class ConditionSet<T extends Conditional> extends Conditional {

    private List<T> and;
    private List<T> or;

    public ConditionSet() {
        this.and = new LinkedList<>();
        this.or = new LinkedList<>();
    }

    public ConditionSet and(T and) {
        this.and.add(and);
        return this;
    }

    public ConditionSet or(T or) {
        this.or.add(or);
        return this;
    }

    public List<T> getAnd() {
        return and;
    }

    public void setAnd(List<T> and) {
        this.and = and;
    }

    public List<T> getOr() {
        return or;
    }

    public void setOr(List<T> or) {
        this.or = or;
    }
}
