package org.locke.query.impl;

import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;
import org.locke.query.api.ViewGenerator;
import org.locke.query.models.SelectView;
import org.locke.query.models.SimpleCondition;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.easymock.EasyMock.createStrictControl;
import static org.easymock.EasyMock.expect;

/**
 * @author David Gilmore
 * @date 5/3/14
 */
public class JdbcWarehouseRepoUnitTest {

    private IMocksControl control;
    private ViewGenerator viewGeneratorMock;
    private JdbcTemplate jdbcTemplateMock;
    private JdbcWarehouseRepo jdbcWarehouseRepo;

    @Before
    public void setUp() throws Exception {

        this.control = createStrictControl();

        this.viewGeneratorMock = this.control.createMock(ViewGenerator.class);
        this.jdbcTemplateMock = this.control.createMock(JdbcTemplate.class);

        this.jdbcWarehouseRepo = new JdbcWarehouseRepo();

        this.jdbcWarehouseRepo.setViewGenerator(viewGeneratorMock);
        this.jdbcWarehouseRepo.setJdbcTemplate(jdbcTemplateMock);
    }

    @Test
    public void testQuery() throws Exception {

        List<Map<String, Object>> mockReturn = new LinkedList<>();
        SelectView view = new SelectView();
        expect(this.viewGeneratorMock.createQuery(view)).andReturn("SELECT * FROM TEST");
        expect(this.jdbcTemplateMock.queryForList("SELECT * FROM TEST")).andReturn(mockReturn);

        this.control.replay();

        this.jdbcWarehouseRepo.query("resource", view, new SimpleCondition());

        this.control.verify();
    }
}
