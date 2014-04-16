package com.rollup.olap.repositories;

import com.rollup.olap.DataTree;

/**
 * The WareHouseRepo interfaces with the data warehouse to make queries against the full data set.
 *
 * @author David Gilmore
 * @date 4/10/14
 */
public interface WarehouseRepo {
    public DataTree query(String query, String... conditions);
}
