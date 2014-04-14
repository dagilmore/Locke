package com.rollup.core.repositories;

import com.rollup.core.DataTree;

/**
 * The CubeDataRepo interfaces with whatever data source is being used to handle persistence of data slices and cubes
 *
 * @author David Gilmore
 * @date 4/10/14
 */
public interface CubeDataRepo {
    public DataTree query(String resource, String view, String... where);
}
