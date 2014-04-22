package com.locke.olap;

import com.locke.olap.error.QueryDoesNotExistException;

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
     * @param where
     * @return
     */
    boolean getQueryExists(String resource, String view, String... where);

    /**
     *
     * @param resource
     * @param view
     * @param where
     */
    void setQueryExists(String resource, String view, String... where);

}
