package com.rollup.olap.impl;

import com.rollup.olap.*;
import com.rollup.olap.error.QueryDoesNotExistException;
import com.rollup.olap.models.DataNode;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author David Gilmore
 * @date 4/13/14
 */
public class HolapClientImpl implements HolapClient {

    private CacheManagerRepo cacheManager;
    private CubeDataRepo cubeRepo;
    private WarehouseRepo warehouseRepo;

    private static final Logger logger = Logger.getLogger(HolapClientImpl.class);

    /**
     *
     * @param resource
     * @param view
     * @param conditions
     * @return
     * @throws QueryDoesNotExistException
     */
    @Override
    public DataNode query(String resource, String view, String... conditions) throws QueryDoesNotExistException {

        DataNode ret;
        String query = cacheManager.getQuery(resource, view);

        if (cacheManager.getQueryExists(resource, view, conditions)) {
            ret = cubeRepo.query(resource, view, conditions);
        }

        else {
            ret = warehouseRepo.query(query, conditions);
        }

        return ret;
    }

    /**
     * "Rollup" a collection of data on a certain field.
     * @param field
     * @param list
     * @return
     */
    @Override
    public Map<Object, List<Map<String, Object>>> rollup(String field, List<Map<String, Object>> list) {

        HashMap<Object, List<Map<String, Object>>> ret = new HashMap<>();

        for (Map<String, Object> data: list) {
            Object key = data.get(field);
            if (ret.containsKey(key))
                ret.get(key).add(data);
            else {
                List<Map<String, Object>> mapList = new LinkedList<>();
                mapList.add(data);
                ret.put(key, mapList);
            }
        }

        return ret;
    }

    public void setCacheManager(CacheManagerRepo cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void setCubeRepo(CubeDataRepo cubeRepo) {
        this.cubeRepo = cubeRepo;
    }

    public void setWarehouseRepo(WarehouseRepo warehouseRepo) {
        this.warehouseRepo = warehouseRepo;
    }
}
