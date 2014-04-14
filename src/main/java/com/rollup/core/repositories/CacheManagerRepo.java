package com.rollup.core.repositories;

import java.util.HashMap;

/**
 * @author David Gilmore
 * @date 4/10/14
 */
public interface CacheManagerRepo {

    /**
     *
     * @param domain
     * @return
     */
    HashMap<String, String> getDomain(String domain);


    /**
     *
     * @param domain
     * @return
     */
    void createDomain(String domain);

    /**
     *
     * @param domain
     * @param view
     * @return
     */
    String getQuery(String domain, String view);

    /**
     *
     * @param domain
     * @param view
     * @param where
     * @return
     */
    boolean getQueryExists(String domain, String view, String... where);

    /**
     *
     * @param domain
     * @param view
     * @param where
     */
    void setQueryExists(String domain, String view, String... where);

}
