package com.rollup.olap.services.impl;

import com.rollup.olap.DataTree;
import com.rollup.olap.repositories.CacheManagerRepo;
import com.rollup.olap.repositories.CubeDataRepo;
import com.rollup.olap.repositories.WarehouseRepo;
import com.rollup.olap.services.DataService;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.createStrictControl;
import static org.easymock.EasyMock.expect;

/**
 * @author David Gilmore
 * @date 4/13/14
 */
public class DataServiceImplTest {

    private IMocksControl control;
    private CacheManagerRepo cacheManagerMock;
    private CubeDataRepo cubeRepoMock;
    private WarehouseRepo warehouseRepoMock;
    private DataService dataService;

    @Before
    public void setUp() throws Exception {

        this.control = createStrictControl();

        this.cacheManagerMock = this.control.createMock(CacheManagerRepo.class);
        this.cubeRepoMock = this.control.createMock(CubeDataRepo.class);
        this.warehouseRepoMock = this.control.createMock(WarehouseRepo.class);

        DataServiceImpl dataServiceImpl = new DataServiceImpl();

        dataServiceImpl.setCacheManager(this.cacheManagerMock);
        dataServiceImpl.setCubeRepo(this.cubeRepoMock);
        dataServiceImpl.setWarehouseRepo(this.warehouseRepoMock);

        this.dataService = dataServiceImpl;
    }

    @Test
    public void testQuery__Cached() throws Exception {

        expect(this.cacheManagerMock.getQuery("resource", "resource_view")).andReturn("SELECT ?, ?, ? FROM test.test_view");
        expect(this.cacheManagerMock.getQueryExists("resource", "resource_view", "cond1=cond2")).andReturn(true);
        expect(this.cubeRepoMock.query("resource", "resource_view", "cond1=cond2")).andReturn(new DataTree(""));

        this.control.replay();

        this.dataService.query("resource", "resource_view", "cond1=cond2");

        this.control.verify();
    }

    @Test
    public void testQuery__NotCached() throws Exception {

        expect(this.cacheManagerMock.getQuery("resource", "resource_view")).andReturn("SELECT ?, ?, ? FROM test.test_view");
        expect(this.cacheManagerMock.getQueryExists("resource", "resource_view", "cond1=cond2")).andReturn(false);
        expect(this.warehouseRepoMock.query("SELECT ?, ?, ? FROM test.test_view", "cond1=cond2")).andReturn(new DataTree(""));

        this.control.replay();

        this.dataService.query("resource", "resource_view", "cond1=cond2");

        this.control.verify();
    }
}
