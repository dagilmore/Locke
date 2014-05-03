package com.locke.olap.impl;

import com.locke.olap.CacheManagerRepo;
import com.locke.olap.models.View;

import java.util.HashMap;

/**
 * @author David Gilmore
 * @date 4/10/14
 */
public class InMemoryCacheManagerRepo implements CacheManagerRepo {

    private HashMap<String, HashMap<String, View>> domains;
    private HashMap<String, String> cubes;


    public InMemoryCacheManagerRepo() {
        this.domains = new HashMap<>();
        this.cubes = new HashMap<>();
    }

    @Override
    public HashMap<String, View> getResource(String resource) {
        return this.domains.get(resource);
    }

    @Override
    public void createResource(String resource) {
        domains.put(resource, new HashMap<String, View>());
    }

    @Override
    public View getQuery(String resource, String view) {
        return domains.get(resource).get(view);
    }

    @Override
    public boolean getQueryExists(String resource, String view, Condition... conditions) {
        return false;
    }

    @Override
    public void setQueryExists(String resource, String view, Condition... conditions) {}
}
