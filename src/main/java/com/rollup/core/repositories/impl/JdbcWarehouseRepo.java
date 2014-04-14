package com.rollup.core.repositories.impl;

import com.rollup.core.DataTree;
import com.rollup.core.repositories.WarehouseRepo;
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
    public DataTree query(String query, String... conditions) {
        return new DataTree("");
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
