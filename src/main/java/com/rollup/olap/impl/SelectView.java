package com.rollup.olap.impl;

import com.rollup.olap.View;

import java.util.List;
import java.util.Map;

/**
 * @author David Gilmore
 * @date 4/18/14
 */
public class SelectView implements View {

    private String id;

    private String resourceName;

    private List<String> columns;
    private List<String> groupBy;

    private View from;

    private Map<String, String> functions;

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

    public List<String> getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(List<String> groupBy) {
        this.groupBy = groupBy;
    }

    public View getFrom() {
        return from;
    }

    public void setFrom(View from) {
        this.from = from;
    }

    public Map<String, String> getFunctions() {
        return functions;
    }

    public void setFunctions(Map<String, String> functions) {
        this.functions = functions;
    }

    public List<Condition> getWhereConditions() {
        return whereConditions;
    }

    public void setWhereConditions(List<Condition> whereConditions) {
        this.whereConditions = whereConditions;
    }
}
