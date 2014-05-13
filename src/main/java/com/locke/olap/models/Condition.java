package com.locke.olap.models;

/**
 * @author David Gilmore
 * @date 4/18/14
 */
public class Condition<T extends Comparable> {

    private String view;
    private T left;
    private T right;
    private String operator;
    private Condition and;
    private Condition or;

    public Condition(String view, T left, T right, String operator) {
        this.view = view;
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public T getLeft() {
        return left;
    }

    public void setLeft(T left) {
        this.left = left;
    }

    public T getRight() {
        return right;
    }

    public void setRight(T right) {
        this.right = right;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Condition getAnd() {
        return and;
    }

    public void setAnd(Condition and) {
        this.and = and;
    }

    public Condition getOr() {
        return or;
    }

    public void setOr(Condition or) {
        this.or = or;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Condition condition = (Condition) o;

        if (and != null ? !and.equals(condition.and) : condition.and != null) return false;
        if (left != null ? !left.equals(condition.left) : condition.left != null) return false;
        if (operator != null ? !operator.equals(condition.operator) : condition.operator != null) return false;
        if (or != null ? !or.equals(condition.or) : condition.or != null) return false;
        if (right != null ? !right.equals(condition.right) : condition.right != null) return false;
        if (view != null ? !view.equals(condition.view) : condition.view != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = view != null ? view.hashCode() : 0;
        result = 31 * result + (left != null ? left.hashCode() : 0);
        result = 31 * result + (right != null ? right.hashCode() : 0);
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (and != null ? and.hashCode() : 0);
        result = 31 * result + (or != null ? or.hashCode() : 0);
        return result;
    }
}
