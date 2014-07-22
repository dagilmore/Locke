package org.locke.query.impl;

import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;
import org.locke.query.api.CacheManagerRepo;
import org.locke.query.api.CubeDataRepo;
import org.locke.query.api.HolapClient;
import org.locke.query.api.WarehouseRepo;
import org.locke.query.error.DoesNotExistException;
import org.locke.query.models.*;

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

        HolapClientImpl holapClientImpl = new HolapClientImpl();

        holapClientImpl.setCacheManager(this.cacheManagerMock);
        holapClientImpl.setCubeRepo(this.cubeRepoMock);
        holapClientImpl.setWarehouseRepo(this.warehouseRepoMock);

        this.holapClient = holapClientImpl;
    }

    @Test
    public void testCreateResource() throws Exception {

        this.cacheManagerMock.createResource("resource");

        this.control.replay();

        this.holapClient.createResource("resource");

        this.control.verify();
    }

    @Test
    public void testCreateView() throws Exception {

        View view = new TableView();

        expect(this.cacheManagerMock.getResource("resource")).andReturn(null);

        this.cacheManagerMock.createResource("resource");
        this.cacheManagerMock.createView("resource", view);

        this.control.replay();

        this.holapClient.createView("resource", view);

        this.control.verify();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testQuery__Cached() throws Exception {

        SimpleCondition cond = new SimpleCondition("resource_view", "left", "right", Comparator.GT);
        expect(this.cacheManagerMock.getView("resource", "resource_view")).andReturn(new SelectView());
        expect(this.cacheManagerMock.getQueryExists("resource", "resource_view", cond)).andReturn(true);
        expect(this.cubeRepoMock.query(anyObject(String.class), anyObject(String.class), anyObject(Condition.class))).andReturn(new DataNode(""));

        this.control.replay();

        this.holapClient.query("resource", "resource_view", cond);

        this.control.verify();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testQuery__NotCached() throws Exception {

        SimpleCondition cond = new SimpleCondition("resource_view", "left", "right", Comparator.GT);

        SelectView view = new SelectView();

        DataNode ret = new DataNode("");

        expect(this.cacheManagerMock.getView("resource", "resource_view")).andReturn(view);
        expect(this.cacheManagerMock.getQueryExists("resource", "resource_view", cond)).andReturn(false);
        expect(this.warehouseRepoMock.query("resource", view, cond)).andReturn(ret);

        this.cubeRepoMock.save("resource", "resource_view", ret);

        this.cacheManagerMock.setQueryExists("resource", "resource_view", cond);

        this.control.replay();

        this.holapClient.query("resource", "resource_view", cond);

        this.control.verify();
    }

    @Test(expected = DoesNotExistException.class)
    @SuppressWarnings("unchecked")
    public void testQuery__QueryDoesNotExist() throws Exception {

        SimpleCondition cond = new SimpleCondition("resource_view", "left", "right", Comparator.GT);
        expect(this.cacheManagerMock.getView("resource", "resource_view")).andThrow(new DoesNotExistException());

        this.control.replay();

        this.holapClient.query("resource", "resource_view", cond);
    }
}
