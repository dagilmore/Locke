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
}
