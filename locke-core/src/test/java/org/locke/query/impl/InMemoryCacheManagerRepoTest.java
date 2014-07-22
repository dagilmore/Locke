package org.locke.query.impl;

import org.locke.query.models.Comparator;
import org.locke.query.models.SelectView;
import org.locke.query.models.SimpleCondition;
import org.locke.query.models.View;
import org.junit.Before;
import org.junit.Test;

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

        SimpleCondition exists1 = new SimpleCondition("test_view", "field", 3, Comparator.GT);

        this.cacheRepo.createResource("test_resource");

        View testView = new SelectView();
        testView.setName("test_view");
        this.cacheRepo.createView("test_resource", testView);

        this.cacheRepo.setQueryExists("test_resource", "test_view", exists1);

        SimpleCondition curr1 = new SimpleCondition("test_view", "field", 5, Comparator.GT);

        //todo:finish test
//        assertEquals(true, this.cacheRepo.getQueryExists("test_resource", "test_view", curr1));
    }

    @Test
    public void testGetQueryExists__AndUpperLimitNotComputed() throws Exception {

    }
}
