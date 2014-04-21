package com.rollup.olap;

import com.rollup.olap.models.DataNode;

/**
 * The CubeDataRepo interfaces with whatever data source is being used to handle persistence of data slices and cubes
 *
 * @author David Gilmore
 * @date 4/10/14
 */
public interface CubeDataRepo {
    public DataNode query(String resource, String view, String... where);
}
