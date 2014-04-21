package com.rollup.olap.impl;

import com.rollup.olap.View;

import java.util.List;

/**
 * @author David Gilmore
 * @date 4/18/14
 */
public class JoinView implements View {

    private String id;
    private String resourceName;

    private List<String> columns;

    private View left;
    private View right;
    private List<Condition> joinConditions;
    private List<Condition> whereConditions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

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
