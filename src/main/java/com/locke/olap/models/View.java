package com.locke.olap.models;

import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.util.List;

/**
 * @author David Gilmore
 * @date 4/18/14
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type")
public abstract class View {

    protected String id;
    protected String resource;
    protected String name;

    List<String> columns;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }
}
