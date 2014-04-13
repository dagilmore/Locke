package com.forrest.core.services;

import com.forrest.core.DataTree;

/**
 * @author David Gilmore
 * @date 4/10/14
 */
public interface DataService {
    DataTree query(String resource, String view, String... conditions);
}
