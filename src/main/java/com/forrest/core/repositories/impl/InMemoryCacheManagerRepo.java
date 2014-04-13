package com.forrest.core.repositories.impl;

import com.forrest.core.repositories.CacheManagerRepo;
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
    public HashMap<String, String> getDomain(String domain) {
        return this.domains.get(domain);
    }

    @Override
    public void createDomain(String domain) {
        domains.put(domain, new HashMap<>());
    }

    @Override
    public String getQuery(String domain, String view) {
        return domains.get(domain).get(view);
    }

    @Override
    public boolean getQueryExists(String domain, String view, String... where) {
        return false;
    }

    @Override
    public void setQueryExists(String domain, String view, String... where) {}
}
