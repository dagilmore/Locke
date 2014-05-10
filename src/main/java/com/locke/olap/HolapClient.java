package com.locke.olap;

import com.locke.olap.error.DoesNotExistException;
import com.locke.olap.models.Condition;
import com.locke.olap.models.DataNode;

import java.util.List;
import java.util.Map;

/**
 * @author David Gilmore
 * @date 4/10/14
 */
public interface HolapClient {
    DataNode query(String resource, String view, Condition... conditions) throws DoesNotExistException;

    Map<Object, List<Map<String, Object>>> rollup(String field, List<Map<String, Object>> list);
}
