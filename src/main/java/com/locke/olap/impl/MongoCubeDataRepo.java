package com.locke.olap.impl;

import com.locke.olap.CubeDataRepo;
import com.locke.olap.models.Condition;
import com.locke.olap.models.DataNode;
import com.mongodb.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Cube data repository that uses MongoDB for persistence.
 * @author David Gilmore
 * @date 4/10/14
 */
public class MongoCubeDataRepo implements CubeDataRepo {

    private DB mongoDb;

    @Override
    @SuppressWarnings("unchecked")
    public DataNode query(String resource, String view, Condition... conditions) {

        DBCollection coll = mongoDb.getCollection(resource);

        BasicDBObject baseQuery = new BasicDBObject("view", view);

        DBCursor cur = coll.find(baseQuery);

        return new DataNode(cur.toArray());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void save(String resourceName, String view, DataNode data) {

        if (data == null || data.getData() == null) return;

        if (data.getData() instanceof Collection<?>) {

            Collection coll = (Collection) data.getData();

            DBObject[] objectArray = new DBObject[coll.size()];

            int i = 0;
            for (Object item: coll) {

                if (item instanceof Map) {

                    Map<Object, Object> map = (Map) item;

                    DBObject obj = new BasicDBObject();
                    Iterator it = map.entrySet().iterator();

                    while (it.hasNext()) {
                        Map.Entry entry = (Map.Entry) it.next();
                        obj.put((String) entry.getKey(), entry.getValue());
                    }

                    objectArray[i++] = obj;
                }
            }

            DBCollection mongoColl = mongoDb.getCollection(resourceName);

            mongoColl.insert(objectArray, WriteConcern.SAFE);
        }

        else {

        }
    }

    public void setMongoDb(DB mongoDb) {
        this.mongoDb = mongoDb;
    }
}
