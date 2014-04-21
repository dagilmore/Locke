package com.rollup.olap.impl;

import com.google.code.morphia.Datastore;
import com.rollup.olap.CubeDataRepo;
import com.rollup.olap.models.DataNode;

/**
 * Cube data repository that uses MongoDB for persistence.
 * @author David Gilmore
 * @date 4/10/14
 */
public class MongoCubeDataRepo implements CubeDataRepo {

    private Datastore mongoStore;

    @Override
    public DataNode query(String resource, String view, String... where) {
        return null;
    }

    public void setMongoStore(Datastore mongoStore) {
        this.mongoStore = mongoStore;
    }
}
