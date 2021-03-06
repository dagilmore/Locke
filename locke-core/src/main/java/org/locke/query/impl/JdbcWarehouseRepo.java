package org.locke.query.impl;

import org.locke.query.api.ViewGenerator;
import org.locke.query.api.WarehouseRepo;
import org.locke.query.error.MalformedViewException;
import org.locke.query.models.Condition;
import org.locke.query.models.DataNode;
import org.locke.query.models.View;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * The JdbcWarehouseRepo uses JDBC to interface with the data warehouse to make queries against the full data set
 *
 * @author David Gilmore
 * @date 4/10/14
 */
public class JdbcWarehouseRepo implements WarehouseRepo {

    private ViewGenerator viewGenerator;
    private JdbcTemplate jdbcTemplate;

    public JdbcWarehouseRepo() {
        this.viewGenerator = new HiveQLViewGenerator();
    }

    @Override
    @SuppressWarnings("unchecked")
    public DataNode query(String resource, View view, Condition condition) throws DataAccessException, MalformedViewException {

        String query = viewGenerator.createQuery(view);

        List<Map<String, Object>> ret = jdbcTemplate.queryForList(query);

        return new DataNode(ret);
    }

    public void setViewGenerator(ViewGenerator viewGenerator) {
        this.viewGenerator = viewGenerator;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
