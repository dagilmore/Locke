package com.locke.olap.impl;

import com.mongodb.*;
import com.locke.olap.CubeDataRepo;
import com.locke.olap.models.DataNode;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Cube data repository that uses MongoDB for persistence.
 * @author David Gilmore
 * @date 4/10/14
 */
public class MongoCubeDataRepo implements CubeDataRepo {

    private DB mongoDb;

    @Override
    public DataNode query(String resource, String view, String... where) {

        BasicDBList list = new BasicDBList();

        return null;
    }

    @Override
    public void save(String resourceName, List<Map<String, Object>> list) {

        DBObject[] objectArray = new DBObject[list.size()];

        int i = 0;
        for (Map<String, Object> map: list) {

            DBObject obj = new BasicDBObject(map.size());
            Iterator it = map.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                obj.put((String) entry.getKey(), entry.getValue());
            }

            objectArray[i++] = obj;
        }

        DBCollection coll = mongoDb.getCollection(resourceName);

        coll.insert(objectArray, WriteConcern.SAFE);
    }

    public void setMongoDb(DB mongoDb) {
        this.mongoDb = mongoDb;
    }
}
