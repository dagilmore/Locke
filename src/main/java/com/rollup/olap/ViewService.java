package com.rollup.olap;

import java.util.Map;

/**
 * @author David Gilmore
 * @date 4/10/14
 */
public interface ViewService {
    String createView(String domain, Map<String, String[]> params);
}
