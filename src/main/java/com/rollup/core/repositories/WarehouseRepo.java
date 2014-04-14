package com.rollup.core.repositories;

import com.rollup.core.DataTree;

/**
 * The WareHouseRepo interfaces with the data warehouse to make queries against the full data set.
 *
 * @author David Gilmore
 * @date 4/10/14
 */
public interface WarehouseRepo {
    public DataTree query(String query, String... conditions);
}
