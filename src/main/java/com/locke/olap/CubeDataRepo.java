package com.locke.olap;

import com.locke.olap.models.DataNode;

import java.util.List;
import java.util.Map;

/**
 * The CubeDataRepo interfaces with whatever data source is being used to handle persistence of data slices and cubes
 *
 * @author David Gilmore
 * @date 4/10/14
 */
public interface CubeDataRepo {
    public DataNode query(String resource, String view, String... where);

    void save(String resourceName, List<Map<String, Object>> list);
}