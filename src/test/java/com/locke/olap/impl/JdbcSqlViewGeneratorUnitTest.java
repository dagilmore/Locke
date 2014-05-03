package com.locke.olap.impl;


import com.locke.olap.models.SelectView;
import com.locke.olap.models.TableView;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David Gilmore
 * @date 4/17/14
 */
public class JdbcSqlViewGeneratorUnitTest {

    private JdbcSqlViewGenerator jdbcSqlViewGenerator;

    @Before
    public void setUp() throws Exception {
        this.jdbcSqlViewGenerator = new JdbcSqlViewGenerator();
    }

    @Test
    public void testCreateView__SimpleSelectFromTable() throws Exception {

        SelectView selectView = new SelectView();

        selectView.setResource("test");
        selectView.setName("test_select_view");

        List<String> columns = new ArrayList<>();

        columns.add("name");
        columns.add("type");
        columns.add("amount");

        selectView.setColumns(columns);

        TableView from = new TableView();

        from.setResource("test");
        from.setName("test_table");

        selectView.setFrom(from);

        String query = this.jdbcSqlViewGenerator.createQuery(selectView);
    }
}
