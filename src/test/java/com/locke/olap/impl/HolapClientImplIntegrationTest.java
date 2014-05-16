package com.locke.olap.impl;

import com.locke.IntegrationTestCase;
import com.locke.olap.CacheManagerRepo;
import com.locke.olap.CubeDataRepo;
import com.locke.olap.HolapClient;
import com.locke.olap.WarehouseRepo;
import com.locke.olap.models.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * @author David Gilmore
 * @date 5/11/14
 */
public class HolapClientImplIntegrationTest extends IntegrationTestCase {

    private HolapClient holapClient;

    private View testTable;

    @Before
    public void setUp() throws Exception {

        MongoCubeDataRepo mongoCubeDataRepo = new MongoCubeDataRepo();
        mongoCubeDataRepo.setMongoDb(mongoDB);
        
        JdbcWarehouseRepo jdbcWarehouseRepo = new JdbcWarehouseRepo();
        jdbcWarehouseRepo.setJdbcTemplate(jdbcTemplate);

        CubeDataRepo cubeDataRepo = mongoCubeDataRepo;
        WarehouseRepo warehouseRepo = jdbcWarehouseRepo;
        CacheManagerRepo cacheManagerRepo = new InMemoryCacheManagerRepo();

        HolapClientImpl holapClientImpl = new HolapClientImpl();
        holapClientImpl.setCubeRepo(cubeDataRepo);
        holapClientImpl.setWarehouseRepo(warehouseRepo);
        holapClientImpl.setCacheManager(cacheManagerRepo);
        
        this.holapClient = holapClientImpl;

        this.testTable = new TableView();
        this.testTable.setName("test_table");
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreateAndQuery__FlatQuery() throws Exception {

        //Create simple table view to reflect the previously created table
        SelectView simpleSelect = new SelectView();

        List<String> columns = new ArrayList<>();
        columns.addAll(Arrays.asList("name", "item", "day", "amount"));

        simpleSelect.setFrom(this.testTable);
        simpleSelect.setColumns(columns);
        simpleSelect.setResource("philosophers");
        simpleSelect.setName("");

        this.holapClient.createResource("philosophers");
        this.holapClient.createView("philosophers", simpleSelect);

        this.holapClient.query("philosophers", "", new Condition("","","", Condition.Operator.EQ));

        //Assert that mongoDB was updated appropriately
        DBCollection coll = mongoDB.getCollection("philosophers");

        BasicDBObject query = new BasicDBObject();
        query.put("viewName", "");

        assertEquals(50, coll.count(query));

        //Destroy datawarehouse tables to ensure that future queries come from the cube
        destroyTables();

        DataNode data = this.holapClient.query("philosophers", "", new Condition("","","", Condition.Operator.EQ));
        assertEquals(50, ( (List) data.getData()).size());
    }
}
