package com.locke.olap.impl;


import com.locke.olap.models.TableView;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author David Gilmore
 * @date 4/17/14
 */
public class JdbcSqlViewGeneratorTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testCreateView() throws Exception {

        org.h2.Driver.load();
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
//        Connection conn = new HiveConnection("jdbc:hive2://ec2-54-227-18-49.compute-1.amazonaws.com:21050/;auth=noSasl",new Properties());
        JdbcSqlViewGenerator generator = new JdbcSqlViewGenerator(conn);
        String sql = generator.createQuery(new TableView());
        System.out.println(sql);
    }
}
