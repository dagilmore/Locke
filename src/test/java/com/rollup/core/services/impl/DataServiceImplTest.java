package com.rollup.core.services.impl;

import com.rollup.core.services.DataService;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.createStrictControl;

/**
 * @author David Gilmore
 * @date 4/13/14
 */
public class DataServiceImplTest {

    private IMocksControl control;
    private DataService dataService;

    @Before
    public void setUp() throws Exception {
        this.control = createStrictControl();
        this.dataService = new DataServiceImpl();
    }

    @Test
    public void testQuery() throws Exception {

    }
}
