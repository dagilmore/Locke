package com.locke.olap.impl;

import com.locke.olap.CacheManagerRepo;
import com.locke.olap.CubeDataRepo;
import com.locke.olap.HolapClient;
import com.locke.olap.WarehouseRepo;
import com.locke.olap.error.QueryDoesNotExistException;
import com.locke.olap.models.DataNode;
import com.locke.olap.models.SelectView;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*;

/**
 * @author David Gilmore
 * @date 4/13/14
 */
public class HolapClientImplUnitTest {

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
    public void testCreateResource() throws Exception {

    }

    @Test
    public void testCreateResource__WithDefaultView() throws Exception {

    }

    @Test
    public void testCreateView() throws Exception {

    }

    @Test
    @SuppressWarnings("unchecked")
    public void testQuery__Cached() throws Exception {

        Condition cond = new Condition("resource_view", "left", "right", ">");
        expect(this.cacheManagerMock.getQuery("resource", "resource_view")).andReturn(new SelectView());
        expect(this.cacheManagerMock.getQueryExists("resource", "resource_view", cond)).andReturn(true);
        expect(this.cubeRepoMock.query(anyObject(String.class), anyObject(String.class), anyObject(Condition[].class))).andReturn(new DataNode(""));

        this.control.replay();

        this.holapClient.query("resource", "resource_view", cond);

        this.control.verify();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testQuery__NotCached() throws Exception {

        Condition cond = new Condition("resource_view", "left", "right", ">");

        SelectView view = new SelectView();

        DataNode ret = new DataNode("");

        expect(this.cacheManagerMock.getQuery("resource", "resource_view")).andReturn(view);
        expect(this.cacheManagerMock.getQueryExists("resource", "resource_view", cond)).andReturn(false);
        expect(this.warehouseRepoMock.query("resource", view, cond)).andReturn(ret);

        this.cubeRepoMock.save("resource", "resource_view", ret);

        this.cacheManagerMock.setQueryExists("resource", "resource_view", cond);

        this.control.replay();

        this.holapClient.query("resource", "resource_view", cond);

        this.control.verify();
    }

    @Test(expected = QueryDoesNotExistException.class)
    public void testQuery__QueryDoesNotExist() throws Exception {

        Condition cond = new Condition("resource_view", "left", "right", ">");
        expect(this.cacheManagerMock.getQuery("resource", "resource_view")).andThrow(new QueryDoesNotExistException());

        this.control.replay();

        this.holapClient.query("resource", "resource_view", cond);
    }
}
