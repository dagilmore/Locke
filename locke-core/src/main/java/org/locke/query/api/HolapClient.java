package org.locke.query.api;

import org.locke.query.error.DoesNotExistException;
import org.locke.query.error.ExistsException;
import org.locke.query.error.MalformedViewException;
import org.locke.query.models.Condition;
import org.locke.query.models.DataNode;
import org.locke.query.models.View;

import java.util.List;
import java.util.Map;

/**
 * @author David Gilmore
 * @date 4/10/14
 */
public interface HolapClient {

    void createResource(String resource) throws ExistsException;

    void createView(String resource, View view) throws ExistsException, MalformedViewException;

    DataNode query(String resource, String view, Condition condition) throws DoesNotExistException;

    Map<Object, List<Map<String, Object>>> rollup(String field, List<Map<String, Object>> list);
}
