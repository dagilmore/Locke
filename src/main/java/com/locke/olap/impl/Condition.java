package com.locke.olap.impl;

/**
 * @author David Gilmore
 * @date 4/18/14
 */
public class Condition {

    private String left;
    private String right;
    private String operator;

    public Condition(String left, String right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}