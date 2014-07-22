package org.locke.query.api;

import org.locke.query.models.Condition;
import org.locke.query.models.DataNode;

/**
 * The CubeDataRepo interfaces with whatever data source is being used to handle persistence of data slices and cubes
 *
 * @author David Gilmore
 * @date 4/10/14
 */
public interface CubeDataRepo {
    public DataNode query(String resource, String view, Condition condition);

    void save(String resourceName, String view, DataNode data);
}
