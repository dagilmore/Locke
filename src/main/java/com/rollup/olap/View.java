package com.rollup.olap;

import java.util.List;

/**
 * @author David Gilmore
 * @date 4/18/14
 */
public interface View {
    public String getId();
    public void setId(String id);
    public String getResourceName();
    public void setResourceName(String resourceName);
    public List<String> getColumns();
    public void setColumns(List<String> columns);
}
