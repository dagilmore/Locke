package org.locke.query.models;

/**
* @author David Gilmore
* @date 5/16/14
*/
public enum Comparator {

    EQ, NE, GT, LT, GE, LE, IN, EMPTY;


    private boolean negated;

    public boolean isNegated() {
        return negated;
    }
}

