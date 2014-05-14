package com.locke.olap;

import com.locke.olap.error.MalformedViewException;
import com.locke.olap.models.Condition;
import com.locke.olap.models.DataNode;
import com.locke.olap.models.View;

/**
 * The WareHouseRepo interfaces with the data warehouse to make queries against the full data set.
 *
 * @author David Gilmore
 * @date 4/10/14
 */
public interface WarehouseRepo {
    public DataNode query(String resource, View query, Condition... conditions) throws MalformedViewException;
}
