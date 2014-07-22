package org.locke.query.impl;

import org.locke.query.api.CacheManagerRepo;
import org.locke.query.api.CubeDataRepo;
import org.locke.query.api.HolapClient;
import org.locke.query.api.WarehouseRepo;
import org.locke.query.error.DoesNotExistException;
import org.locke.query.error.ExistsException;
import org.locke.query.error.MalformedViewException;
import org.locke.query.models.Condition;
import org.locke.query.models.DataNode;
import org.locke.query.models.View;
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
    public void createResource(String resource) throws ExistsException {

        this.cacheManager.createResource(resource);
    }

    /**
     *
     * @param resource
     * @param view
     */
    @Override
    public void createView(String resource, View view) throws ExistsException, MalformedViewException {

        if (cacheManager.getResource(resource) == null)
            createResource(resource);

        this.cacheManager.createView(resource, view);
    }

    /**
     *
     * @param resource
     * @param viewName
     * @param condition
     * @return
     * @throws org.locke.query.error.DoesNotExistException
     */
    @Override
    @SuppressWarnings("unchecked")
    public DataNode query(String resource, String viewName, Condition condition) throws DoesNotExistException {

        DataNode ret;

        View view = cacheManager.getView(resource, viewName);

        if (cacheManager.getQueryExists(resource, viewName, condition)) {
            ret = cubeRepo.query(resource, viewName, condition);
        }

        else {
            try {
                ret = warehouseRepo.query(resource, view, condition);
                cubeRepo.save(resource, viewName, ret);
                cacheManager.setQueryExists(resource, viewName, condition);
            } catch (MalformedViewException e) {
                //Should not be thrown
                ret = new DataNode("");
            }
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
