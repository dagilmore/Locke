package org.locke.query.impl;

import org.locke.query.api.CubeDataRepo;
import org.locke.query.models.Condition;
import org.locke.query.models.DataNode;
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
    public DataNode query(String resource, String view, Condition condition) {

        DBCollection coll = mongoDb.getCollection(resource);

        BasicDBObject baseQuery = new BasicDBObject("viewName", view);

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

                    obj.put("viewName", view);

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
