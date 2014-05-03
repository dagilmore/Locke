package com.locke.olap.impl;

import com.locke.olap.CacheManagerRepo;
import com.locke.olap.CubeDataRepo;
import com.locke.olap.HolapClient;
import com.locke.olap.WarehouseRepo;
import com.locke.olap.error.QueryDoesNotExistException;
import com.locke.olap.models.DataNode;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createStrictControl;
import static org.easymock.EasyMock.expect;

/**
 * @author David Gilmore
 * @date 4/13/14
 */
public class HolapClientImplTest {

    private IMocksControl control;
    private CacheManagerRepo cacheManagerMock;
    private CubeDataRepo cubeRepoMock;
    private WarehouseRepo warehouseRepoMock;
    private HolapClient holapClient;

    @Before
    public void setUp() throws Exception {

        this.control = createStrictControl();

        this.cacheManagerMock = this.control.createMock(CacheManagerRepo.class);
        this.cubeRepoMock = this.control.createMock(CubeDataRepo.class);
        this.warehouseRepoMock = this.control.createMock(WarehouseRepo.class);

        HolapClientImpl dataServiceImpl = new HolapClientImpl();

        dataServiceImpl.setCacheManager(this.cacheManagerMock);
        dataServiceImpl.setCubeRepo(this.cubeRepoMock);
        dataServiceImpl.setWarehouseRepo(this.warehouseRepoMock);

        this.holapClient = dataServiceImpl;
    }

    @Test
    public void testQuery__Cached() throws Exception {

        Condition cond = new Condition("left", "right", ">");
        expect(this.cacheManagerMock.getQuery("resource", "resource_view")).andReturn("SELECT ?, ?, ? FROM test.test_view");
        expect(this.cacheManagerMock.getQueryExists("resource", "resource_view", cond)).andReturn(true);
        expect(this.cubeRepoMock.query(anyObject(String.class), anyObject(String.class), anyObject(Condition[].class))).andReturn(new DataNode(""));

        this.control.replay();

        this.holapClient.query("resource", "resource_view", cond);

        this.control.verify();
    }

    @Test
    public void testQuery__NotCached() throws Exception {

        Condition cond = new Condition("left", "right", ">");
        expect(this.cacheManagerMock.getQuery("resource", "resource_view")).andReturn("SELECT ?, ?, ? FROM test.test_view");
        expect(this.cacheManagerMock.getQueryExists("resource", "resource_view", cond)).andReturn(false);
        expect(this.warehouseRepoMock.query("SELECT ?, ?, ? FROM test.test_view", cond)).andReturn(new DataNode(""));

        this.control.replay();

        this.holapClient.query("resource", "resource_view", cond);

        this.control.verify();
    }

    @Test
    public void testQuery__QueryDoesNotExist() throws Exception {

        Condition cond = new Condition("left", "right", ">");
        expect(this.cacheManagerMock.getQuery("resource", "resource_view")).andThrow(new QueryDoesNotExistException());

        this.control.replay();

        this.holapClient.query("resource", "resource_view", cond);

        this.control.verify();
    }
}
