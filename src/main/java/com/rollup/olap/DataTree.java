package com.rollup.olap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David Gilmore
 * @date 4/13/14
 */
public class DataTree<T> {

    private DataNode<T> root;

    public DataTree(T rootData) {
        root = new DataNode<>();
        root.data = rootData;
        root.children = new ArrayList<>();
    }

    public static class DataNode<T> {
        private T data;
        private DataNode<T> parent;
        private List<DataNode<T>> children;

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
}
