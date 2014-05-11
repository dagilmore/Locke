package com.locke.olap.impl;

import com.locke.IntegrationTestCase;
import com.locke.olap.CacheManagerRepo;
import com.locke.olap.CubeDataRepo;
import com.locke.olap.HolapClient;
import com.locke.olap.WarehouseRepo;
import org.junit.Before;
import org.junit.Test;

/**
 * @author David Gilmore
 * @date 5/11/14
 */
public class HolapClientImplIntegrationTest extends IntegrationTestCase {

    private HolapClient holapClient;

    private CubeDataRepo cubeDataRepo;
    private CacheManagerRepo cacheManagerRepo;
    private WarehouseRepo warehouseRepo;

    @Before
    public void setUp() throws Exception {

        MongoCubeDataRepo mongoCubeDataRepo = new MongoCubeDataRepo();
        mongoCubeDataRepo.setMongoDb(mongoDB);
        
        JdbcWarehouseRepo jdbcWarehouseRepo = new JdbcWarehouseRepo();
        jdbcWarehouseRepo.setJdbcTemplate(jdbcTemplate);
        
        this.cubeDataRepo = mongoCubeDataRepo;
        this.warehouseRepo = jdbcWarehouseRepo;
        this.cacheManagerRepo = new InMemoryCacheManagerRepo();

        HolapClientImpl holapClientImpl = new HolapClientImpl();
        holapClientImpl.setCubeRepo(this.cubeDataRepo);
        holapClientImpl.setWarehouseRepo(this.warehouseRepo);
        holapClientImpl.setCacheManager(this.cacheManagerRepo);
        
        this.holapClient = holapClientImpl;
    }

    @Test
    public void testCreateAndQuery() throws Exception {


    }
}
