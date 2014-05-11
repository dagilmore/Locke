package com.locke.olap;

import com.locke.olap.error.QueryDoesNotExistException;
import com.locke.olap.impl.Condition;
import com.locke.olap.models.DataNode;
import com.locke.olap.models.View;

import java.util.List;
import java.util.Map;

/**
 * @author David Gilmore
 * @date 4/10/14
 */
public interface HolapClient {

    void createResource(String resource);

    void createResource(String resource, View defaultView);

    void createView(String resource, String view);

    DataNode query(String resource, String view, Condition... conditions) throws QueryDoesNotExistException;

    Map<Object, List<Map<String, Object>>> rollup(String field, List<Map<String, Object>> list);
}
