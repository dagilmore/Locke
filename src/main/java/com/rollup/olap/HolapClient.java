package com.rollup.olap;

import com.rollup.olap.DataTree;
import com.rollup.olap.error.QueryDoesNotExistException;

/**
 * @author David Gilmore
 * @date 4/10/14
 */
public interface HolapClient {
    DataTree query(String resource, String view, String... conditions) throws QueryDoesNotExistException;
}
