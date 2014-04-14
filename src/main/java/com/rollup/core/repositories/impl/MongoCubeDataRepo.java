package com.rollup.core.repositories.impl;

import com.google.code.morphia.Datastore;
import com.rollup.core.DataTree;
import com.rollup.core.repositories.CubeDataRepo;

/**
 * Cube data repository that uses MongoDB for persistence.
 * @author David Gilmore
 * @date 4/10/14
 */
public class MongoCubeDataRepo implements CubeDataRepo {

    private Datastore mongoStore;

    @Override
    public DataTree query(String resource, String view, String... where) {
        return null;
    }

    public void setMongoStore(Datastore mongoStore) {
        this.mongoStore = mongoStore;
    }
}
