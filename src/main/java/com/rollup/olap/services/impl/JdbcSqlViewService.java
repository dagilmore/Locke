package com.rollup.olap.services.impl;

import com.rollup.olap.services.ViewService;

import java.util.Map;

/**
 * @author David Gilmore
 * @date 4/12/14
 */
public class JdbcSqlViewService implements ViewService {
    @Override
    public String createView(String domain, Map<String, String[]> params) {
        return "SELECT * FROM crp.individual_contributions";
    }
}
