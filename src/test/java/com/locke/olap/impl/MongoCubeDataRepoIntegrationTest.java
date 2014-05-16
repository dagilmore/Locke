package com.locke.olap.impl;

import com.locke.IntegrationTestCase;
import com.locke.olap.CubeDataRepo;
import com.locke.olap.models.Condition;
import com.locke.olap.models.DataNode;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandFailureException;
import com.mongodb.DBCollection;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertNotNull;

/**
 * @author David Gilmore
 * @date 5/4/14
 */
public class MongoCubeDataRepoIntegrationTest extends IntegrationTestCase {

    private CubeDataRepo cubeDataRepo;

    @Before
    public void setUp() throws Exception {

        DBCollection col;

        try {
            col = mongoDB.createCollection("test_cube", new BasicDBObject());
        } catch (CommandFailureException e) {
            col = mongoDB.getCollection("test_cube");
        }

        Map<String, Object> values1 = new HashMap<>();
        values1.put("name", "garfield");
        values1.put("type", "cat");
        values1.put("SUM(amount)", new Double(1234.56));
        values1.put("view", "test_aggregate");

        Map<String, Object> values2 = new HashMap<>();
        values2.put("name", "tom");
        values2.put("type", "cat");
        values2.put("SUM(amount)", new Double(6543.21));
        values2.put("view", "test_aggregate");

        BasicDBObject testDoc1 = new BasicDBObject(values1);
        BasicDBObject testDoc2 = new BasicDBObject(values2);

        col.save(testDoc1);
        col.save(testDoc2);

        MongoCubeDataRepo mongoCubeDataRepo = new MongoCubeDataRepo();
        mongoCubeDataRepo.setMongoDb(this.mongoDB);

        this.cubeDataRepo = mongoCubeDataRepo;
    }

    @Test
    public void testQuery() throws Exception {

        DataNode ret = cubeDataRepo.query("test_cube", "test_aggregate", new Condition());
        assertNotNull(ret);
    }

    @Test
    public void testSave() throws Exception {

    }
}
