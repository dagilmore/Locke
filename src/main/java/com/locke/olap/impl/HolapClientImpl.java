package com.locke.olap.impl;

import com.locke.olap.*;
import com.locke.olap.error.DoesNotExistException;
import com.locke.olap.models.Condition;
import com.locke.olap.models.DataNode;
import com.locke.olap.models.View;
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
     */
    @Override
    public void createResource(String resource) {

    }

    /**
     *
     * @param resource
     * @param defaultView
     */
    @Override
    public void createResource(String resource, View defaultView) {

    }

    /**
     *
     * @param resource
     * @param view
     */
    @Override
    public void createView(String resource, String view) {

    }

    /**
     *
     * @param resource
     * @param viewName
     * @param conditions
     * @return
     * @throws com.locke.olap.error.DoesNotExistException
     */
    @Override
    public DataNode query(String resource, String viewName, Condition... conditions) throws DoesNotExistException {

        DataNode ret;

        View view = cacheManager.getView(resource, viewName);

        if (cacheManager.getQueryExists(resource, viewName, conditions)) {
            ret = cubeRepo.query(resource, viewName, conditions);
        }

        else {
            ret = warehouseRepo.query(resource, view, conditions);
            cubeRepo.save(resource, viewName, ret);
            cacheManager.setQueryExists(resource, viewName, conditions);
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
