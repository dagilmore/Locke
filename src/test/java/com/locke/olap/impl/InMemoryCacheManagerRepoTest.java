package com.locke.olap.impl;

import com.locke.olap.models.Condition;
import com.locke.olap.models.SelectView;
import com.locke.olap.models.View;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author David Gilmore
 * @date 5/4/14
 */
public class InMemoryCacheManagerRepoTest {

    private InMemoryCacheManagerRepo cacheRepo;

    @Before
    public void setUp() throws Exception {
        this.cacheRepo = new InMemoryCacheManagerRepo();
    }


    @Test
    public void testGetResource() throws Exception {

    }

    @Test
    public void testCreateResource() throws Exception {

    }

    @Test
    public void testGetView() throws Exception {

    }

    @Test
    public void testCreateView() throws Exception {

    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetQueryExists__Simple() throws Exception {

        Condition exists1 = new Condition("test_view", "field", 3, ">");

        this.cacheRepo.createResource("test_resource");

        View testView = new SelectView();
        testView.setName("test_view");
        this.cacheRepo.createView("test_resource", testView);

        this.cacheRepo.setQueryExists("test_resource", "test_view", exists1);

        Condition curr1 = new Condition("test_view", "field", 5, ">");

        assertEquals(true, this.cacheRepo.getQueryExists("test_resource", "test_view", curr1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetQueryExists__AndUpperLimitNotComputed() throws Exception {

        Condition exists1 = new Condition("test_view", "field", 3, ">");
        Condition exists2 = new Condition("test_view", "field", 10, "<");

        exists1.setAnd(exists2);

        this.cacheRepo.createResource("test_resource");

        View testView = new SelectView();
        testView.setName("test_view");
        this.cacheRepo.createView("test_resource", testView);

        this.cacheRepo.setQueryExists("test_resource", "test_view", exists1);

        Condition curr1 = new Condition("test_view", "field", 5, ">");

        assertEquals(false, this.cacheRepo.getQueryExists("test_resource", "test_view", curr1));
    }
}
