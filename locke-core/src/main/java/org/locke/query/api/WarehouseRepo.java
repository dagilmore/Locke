package org.locke.query.api;

import org.locke.query.error.MalformedViewException;
import org.locke.query.models.Condition;
import org.locke.query.models.DataNode;
import org.locke.query.models.View;

/**
 * The WareHouseRepo interfaces with the data warehouse to make queries against the full data set.
 *
 * @author David Gilmore
 * @date 4/10/14
 */
public interface WarehouseRepo {
    public DataNode query(String resource, View query, Condition condition) throws MalformedViewException;
}
