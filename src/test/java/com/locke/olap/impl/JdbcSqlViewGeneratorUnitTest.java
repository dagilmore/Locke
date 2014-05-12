package com.locke.olap.impl;


import com.locke.olap.models.Condition;
import com.locke.olap.models.SelectView;
import com.locke.olap.models.TableView;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

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

        List<String> columns = new LinkedList<>();

        columns.add("name");
        columns.add("type");
        columns.add("amount");

        selectView.setColumns(columns);

        TableView from = new TableView();

        from.setResource("test");
        from.setName("test_table");

        selectView.setFrom(from);

        String query = this.jdbcSqlViewGenerator.createQuery(selectView);

        assertEquals("select name, type, amount from test.test_table", query);
    }

    @Test
    public void testCreateView__SimpleSelectFromTableNoTableResource() throws Exception {

        SelectView selectView = new SelectView();

        selectView.setResource("test");
        selectView.setName("test_select_view");

        List<String> columns = new LinkedList<>();

        columns.add("name");
        columns.add("type");
        columns.add("amount");

        selectView.setColumns(columns);

        TableView from = new TableView();

        from.setName("test_table");

        selectView.setFrom(from);

        String query = this.jdbcSqlViewGenerator.createQuery(selectView);

        assertEquals("select name, type, amount from test_table", query);
    }

    @Test
    public void testCreateView__NestedSelectWithFunctions() throws Exception {

        SelectView from = new SelectView();

        List<String> subColumns = new LinkedList<>();

        subColumns.add("name");
        subColumns.add("type");
        subColumns.add("amount");

        Map<String, String> functions = new HashMap<>();
        functions.put("amount", "SUM");

        List<Condition> where = new LinkedList<>();
        where.add(new Condition("test_subquery", "name", "tom", "="));
        where.add(new Condition("test_subquery", "type", "person", "="));

        List<String> group = new LinkedList<>();
        group.add("name");
        group.add("type");

        from.setResource("test");
        from.setName("test_subquery");
        from.setColumns(subColumns);
        from.setFunctions(functions);
        from.setGroup(group);
        from.setWhere(where);


        SelectView selectView = new SelectView();

        List<String> columns = new ArrayList<>();
        columns.add("name");
        columns.add("type");
        columns.add("total");

        selectView.setResource("test");
        selectView.setName("test_select_view");
        selectView.setColumns(columns);
        selectView.setFrom(from);

        String query = this.jdbcSqlViewGenerator.createQuery(selectView);

        assertNotNull(query);
    }
}
