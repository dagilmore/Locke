package com.locke.olap.impl;

import com.locke.olap.CacheManagerRepo;

import java.util.HashMap;

/**
 * @author David Gilmore
 * @date 4/10/14
 */
public class InMemoryCacheManagerRepo implements CacheManagerRepo {

    private HashMap<String, HashMap<String, String>> domains;
    private HashMap<String, String> cubes;


    public InMemoryCacheManagerRepo() {
        this.domains = new HashMap<>();
        this.cubes = new HashMap<>();
    }

    @Override
    public HashMap<String, String> getResource(String resource) {
        return this.domains.get(resource);
    }

    @Override
    public void createResource(String resource) {
        domains.put(resource, new HashMap<String, String>());
    }

    @Override
    public String getQuery(String resource, String view) {
        return domains.get(resource).get(view);
    }

    @Override
    public boolean getQueryExists(String resource, String view, String... where) {
        return false;
    }

    @Override
    public void setQueryExists(String resource, String view, String... where) {}
}
