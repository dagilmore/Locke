package com.locke.olap.impl;

import com.locke.olap.CubeDataRepo;
import com.locke.olap.models.DataNode;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.Command;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.config.RuntimeConfigBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.config.IRuntimeConfig;
import de.flapdoodle.embed.process.config.io.ProcessOutput;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static junit.framework.Assert.assertNotNull;

/**
 * @author David Gilmore
 * @date 5/4/14
 */
public class MongoCubeDataRepoIntegrationTest {

    private DB mongoDB;
    private MongodExecutable mongodExecutable;
    private CubeDataRepo cubeDataRepo;

    @Before
    public void setUp() throws Exception {

        Logger logger = Logger.getLogger(getClass().getName());

        IRuntimeConfig runtimeConfig = new RuntimeConfigBuilder()
                .defaultsWithLogger(Command.MongoD, logger)
                .processOutput(ProcessOutput.getDefaultInstanceSilent())
                .build();

        MongodStarter runtime = MongodStarter.getInstance(runtimeConfig);

        int port = 12345;

        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(port, true))
                .build();

        this.mongodExecutable = runtime.prepare(mongodConfig);
        this.mongodExecutable.start();

        MongoClient mongo = new MongoClient("localhost", port);

        this.mongoDB = mongo.getDB("test");

        DBCollection col = mongoDB.createCollection("test_cube", new BasicDBObject());

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

    @After
    public void tearDown() throws Exception {
        if (this.mongodExecutable != null)
            this.mongodExecutable.stop();
    }

    @Test
    public void testQuery() throws Exception {

        DataNode ret = cubeDataRepo.query("test_cube", "test_aggregate", null);
        assertNotNull(ret);
    }

    @Test
    public void testSave() throws Exception {

    }
}
