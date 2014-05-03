package com.locke.olap;

import com.locke.olap.error.QueryDoesNotExistException;
import com.locke.olap.impl.Condition;

import java.util.HashMap;

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
    HashMap<String, String> getResource(String resource);


    /**
     *
     * @param resource
     * @return
     */
    void createResource(String resource);

    /**
     *
     * @param resource
     * @param view
     * @return
     */
    String getQuery(String resource, String view) throws QueryDoesNotExistException;

    /**
     *
     * @param resource
     * @param view
     * @param conditions
     * @return
     */
    boolean getQueryExists(String resource, String view, Condition... conditions);

    /**
     *
     * @param resource
     * @param view
     * @param conditions
     */
    void setQueryExists(String resource, String view, Condition... conditions);

}
