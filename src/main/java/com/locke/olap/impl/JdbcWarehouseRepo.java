package com.locke.olap.impl;

import com.locke.olap.models.DataNode;
import com.locke.olap.WarehouseRepo;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * The JdbcWarehouseRepo uses JDBC to interface with the data warehouse to make queries against the full data set
 *
 * @author David Gilmore
 * @date 4/10/14
 */
public class JdbcWarehouseRepo implements WarehouseRepo {

    private JdbcTemplate jdbcTemplate;

    @Override
    public DataNode query(String query, Condition... conditions) {
        return new DataNode("");
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
