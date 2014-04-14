package com.rollup.core.services;

import com.rollup.core.DataTree;
import com.rollup.core.error.QueryDoesNotExistException;

/**
 * @author David Gilmore
 * @date 4/10/14
 */
public interface DataService {
    DataTree query(String resource, String view, String... conditions) throws QueryDoesNotExistException;
}
