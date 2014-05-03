package com.locke.olap.impl;

import com.locke.olap.WarehouseRepo;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author David Gilmore
 * @date 5/3/14
 */
public class JdbcWarehouseRepoIntegrationTest {

    private Connection conn;
    private WarehouseRepo warehouseRepo;

    @Before
    public void setUp() throws Exception {

        JdbcDataSource ds = new JdbcDataSource();

        ds.setURL("jdbc:h2:˜/test");
        ds.setUser("sa");
        ds.setPassword("sa");

        this.conn = ds.getConnection();

        Statement stat = conn.createStatement();

        stat.execute("create table test(id int primary key, name varchar(255))");
        stat.execute("insert into test values(1, 'Hello')");

        ResultSet rs;
        rs = stat.executeQuery("select * from test");
        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }

        stat.close();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

        JdbcWarehouseRepo jdbcWarehouseRepo = new JdbcWarehouseRepo();

        jdbcWarehouseRepo.setJdbcTemplate(jdbcTemplate);

        this.warehouseRepo = jdbcWarehouseRepo;
    }

    @After
    public void tearDown() throws Exception {

        Statement stat = conn.createStatement();

        stat.execute("drop table test");

        this.conn.close();
    }

    @Test
    public void testQuery() throws Exception {


    }
}
