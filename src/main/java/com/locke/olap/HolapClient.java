package com.locke.olap;

import com.locke.olap.error.DoesNotExistException;
import com.locke.olap.error.ExistsException;
import com.locke.olap.models.Condition;
import com.locke.olap.models.DataNode;
import com.locke.olap.models.View;

import java.util.List;
import java.util.Map;

/**
 * @author David Gilmore
 * @date 4/10/14
 */
public interface HolapClient {

    void createResource(String resource) throws ExistsException;

    void createView(String resource, View view) throws ExistsException;

    DataNode query(String resource, String view, Condition... conditions) throws DoesNotExistException;

    Map<Object, List<Map<String, Object>>> rollup(String field, List<Map<String, Object>> list);
}
