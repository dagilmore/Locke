package com.rollup.olap.services.impl;

import com.rollup.olap.DataTree;
import com.rollup.olap.error.QueryDoesNotExistException;
import com.rollup.olap.repositories.CacheManagerRepo;
import com.rollup.olap.repositories.CubeDataRepo;
import com.rollup.olap.repositories.WarehouseRepo;
import com.rollup.olap.services.DataService;
import org.apache.log4j.Logger;

/**
 * @author David Gilmore
 * @date 4/13/14
 */
public class DataServiceImpl implements DataService {

    private CacheManagerRepo cacheManager;
    private CubeDataRepo cubeRepo;
    private WarehouseRepo warehouseRepo;

    private static final Logger logger = Logger.getLogger(DataServiceImpl.class);

    /**
     *
     * @param resource
     * @param view
     * @param conditions
     * @return
     * @throws QueryDoesNotExistException
     */
    @Override
    public DataTree query(String resource, String view, String... conditions) throws QueryDoesNotExistException {

        DataTree ret;
        String query = cacheManager.getQuery(resource, view);

        if (cacheManager.getQueryExists(resource, view, conditions)) {
            ret = cubeRepo.query(resource, view, conditions);
        }

        else {
            ret = warehouseRepo.query(query, conditions);
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
