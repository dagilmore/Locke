package com.rollup.olap.impl;

import com.rollup.olap.View;

import java.util.List;

/**
 * @author David Gilmore
 * @date 4/18/14
 */
public class TableView implements View {

    private String id;
    private String resourceName;
    private String tableName;
    private List<String> columns;

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
}
