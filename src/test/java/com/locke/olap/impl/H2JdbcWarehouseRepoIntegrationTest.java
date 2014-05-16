package com.locke.olap.impl;

import com.locke.IntegrationTestCase;
import com.locke.olap.WarehouseRepo;
import com.locke.olap.models.Condition;
import com.locke.olap.models.Condition.Operator;
import com.locke.olap.models.DataNode;
import com.locke.olap.models.SelectView;
import com.locke.olap.models.TableView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertNotNull;

/**
 * @author David Gilmore
 * @date 5/3/14
 */
public class H2JdbcWarehouseRepoIntegrationTest extends IntegrationTestCase {

    private WarehouseRepo warehouseRepo;

    @Before
    public void setUp() throws Exception {

        Statement stat = conn.createStatement();

        stat.execute("create table test_cats(id int primary key, name varchar(255),type varchar(255), amount double)");

        String insert = "insert into test_cats values(%1$s, '%2$s', '%3$s', %4$s)";
        String name, type;

        for (int i = 0; i < 50; i++) {
            switch (i % 3)  {
                case 0: name = "tom"; type = "cat"; break;
                case 1: name = "jerry"; type = "mouse"; break;
                case 2: name = "mickey"; type = "mouse"; break;
                default: name = "garfield"; type = "cat"; break;
            }
            stat.execute(String.format(insert, new Integer(i+100), name, type, (i+1) * 10));
        }

        stat.close();

        JdbcWarehouseRepo jdbcWarehouseRepo = new JdbcWarehouseRepo();

        jdbcWarehouseRepo.setJdbcTemplate(jdbcTemplate);

        this.warehouseRepo = jdbcWarehouseRepo;
    }

    @After
    public void tearDown() throws Exception {

        Statement stat = conn.createStatement();

        stat.execute("drop table test_cats");
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testQuery() throws Exception {

        SelectView select = new SelectView();

        List<String> columns = new LinkedList<>();

        columns.add("name");
        columns.add("type");
        columns.add("amount");

        Map<String, String> functions = new HashMap<>();
        functions.put("amount", "SUM");

        List<Condition> where = new LinkedList<>();
        where.add(new Condition("test_cats", "name", "'tom'", Operator.EQ));
        where.add(new Condition("test_cats", "type", "'cat'", Operator.EQ));

        List<String> group = new LinkedList<>();
        group.add("name");
        group.add("type");

        TableView from = new TableView();
        from.setName("test_cats");

        select.setResource("test");
        select.setName("test_aggregate");
        select.setColumns(columns);
        select.setFunctions(functions);
        select.setFrom(from);
        select.setGroup(group);
        select.setWhere(where);

        DataNode ret = warehouseRepo.query("test", select, null);

        assertNotNull(ret);
    }
}
