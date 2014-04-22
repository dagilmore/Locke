package com.locke.olap;

import com.locke.olap.models.DataNode;

/**
 * The WareHouseRepo interfaces with the data warehouse to make queries against the full data set.
 *
 * @author David Gilmore
 * @date 4/10/14
 */
public interface WarehouseRepo {
    public DataNode query(String query, String... conditions);
}