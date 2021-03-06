package org.locke.query.api;

import org.locke.query.error.DoesNotExistException;
import org.locke.query.error.ExistsException;
import org.locke.query.models.Condition;
import org.locke.query.models.View;

import java.util.Map;

/**
 * @author David Gilmore
 * @date 4/10/14
 */
public interface CacheManagerRepo {

    /**
     *
     * @param resource
     * @return
     */
    Map<String, View> getResource(String resource);


    /**
     *
     * @param resource
     * @return
     */
    void createResource(String resource) throws ExistsException;

    /**
     *
     * @param resource
     * @param view
     * @return
     */
    View getView(String resource, String view) throws DoesNotExistException;

    /**
     *
     * @param resource
     * @param view
     * @throws org.locke.query.error.ExistsException
     */
    void createView(String resource, View view) throws ExistsException;

    /**
     *
     * @param resource
     * @param view
     * @param conditions
     * @return
     */
    boolean getQueryExists(String resource, String view, Condition conditions);

    /**
     *
     * @param resource
     * @param view
     * @param conditions
     */
    void setQueryExists(String resource, String view, Condition conditions) throws DoesNotExistException;
}
