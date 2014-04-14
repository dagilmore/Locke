package com.rollup.core.repositories.impl;

import com.rollup.core.repositories.CacheManagerRepo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * @author David Gilmore
 * @date 4/10/14
 */
@Repository
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
        domains.put(resource, new HashMap<>());
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
