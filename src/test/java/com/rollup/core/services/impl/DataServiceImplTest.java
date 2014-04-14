package com.rollup.core.services.impl;

import com.rollup.core.DataTree;
import com.rollup.core.repositories.CacheManagerRepo;
import com.rollup.core.repositories.CubeDataRepo;
import com.rollup.core.repositories.WarehouseRepo;
import com.rollup.core.services.DataService;
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
