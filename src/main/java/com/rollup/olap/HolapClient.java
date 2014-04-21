package com.rollup.olap;

import com.rollup.olap.error.QueryDoesNotExistException;
import com.rollup.olap.models.DataNode;

import java.util.List;
import java.util.Map;

/**
 * @author David Gilmore
 * @date 4/10/14
 */
public interface HolapClient {
    DataNode query(String resource, String view, String... conditions) throws QueryDoesNotExistException;

    Map<Object, List<Map<String, Object>>> rollup(String field, List<Map<String, Object>> list);
}
