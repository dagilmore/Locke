package com.forrest.core.services;

import java.util.Map;

/**
 * @author David Gilmore
 * @date 4/10/14
 */
public interface ViewService {
    String createView(String domain, Map<String, String[]> params);
}
