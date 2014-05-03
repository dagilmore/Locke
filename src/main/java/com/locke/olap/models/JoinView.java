package com.locke.olap.models;

import com.locke.olap.impl.Condition;

import java.util.List;

/**
 * @author David Gilmore
 * @date 4/18/14
 */
public class JoinView extends View {

    private View left;
    private View right;
    private List<Condition> joinConditions;
    private List<Condition> whereConditions;


    public View getLeft() {
        return left;
    }

    public void setLeft(View left) {
        this.left = left;
    }

    public View getRight() {
        return right;
    }

    public void setRight(View right) {
        this.right = right;
    }

    public List<Condition> getJoinConditions() {
        return joinConditions;
    }

    public void setJoinConditions(List<Condition> joinConditions) {
        this.joinConditions = joinConditions;
    }

    public List<Condition> getWhereConditions() {
        return whereConditions;
    }

    public void setWhereConditions(List<Condition> whereConditions) {
        this.whereConditions = whereConditions;
    }
}
