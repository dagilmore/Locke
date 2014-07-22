package org.locke.query.models;

import java.util.List;

/**
 * @author David Gilmore
 * @date 4/13/14
 */
public class DataNode<T> {

    private T data;
    private DataNode<T> parent;
    private List<DataNode<T>> children;

    public DataNode() {}

    public DataNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public DataNode<T> getParent() {
        return parent;
    }

    public void setParent(DataNode<T> parent) {
        this.parent = parent;
    }

    public List<DataNode<T>> getChildren() {
        return children;
    }

    public void setChildren(List<DataNode<T>> children) {
        this.children = children;
    }
}
