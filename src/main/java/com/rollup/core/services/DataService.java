package com.rollup.core.services;

import com.rollup.core.DataTree;

/**
 * @author David Gilmore
 * @date 4/10/14
 */
public interface DataService {
    DataTree query(String resource, String view, String... conditions);
}
