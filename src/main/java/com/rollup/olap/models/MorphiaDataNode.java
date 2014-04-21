package com.rollup.olap.models;

import com.google.code.morphia.annotations.Entity;

/**
 * @author David Gilmore
 * @date 4/20/14
 */
@Entity
public class MorphiaDataNode extends DataNode {

    private String resourceName;

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
