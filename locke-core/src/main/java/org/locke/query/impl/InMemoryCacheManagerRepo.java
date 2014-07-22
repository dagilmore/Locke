package org.locke.query.impl;

import org.locke.query.api.CacheManagerRepo;
import org.locke.query.error.DoesNotExistException;
import org.locke.query.error.ExistsException;
import org.locke.query.models.Condition;
import org.locke.query.models.View;

import java.util.*;

/**
 * @author David Gilmore
 * @date 4/10/14
 */
public class InMemoryCacheManagerRepo implements CacheManagerRepo {

    private Map<String, Map<String, View>> resourceViews;
    private Map<String, Map<String, List<Condition>>> queryHistory;


    public InMemoryCacheManagerRepo() {
        this.resourceViews = new HashMap<>();
        this.queryHistory = new HashMap<>();
    }

    @Override
    public Map<String, View> getResource(String resource) {
        return this.resourceViews.get(resource);
    }

    @Override
    public void createResource(String resource) throws ExistsException {

        if (this.queryHistory.containsKey(resource) || this.resourceViews.containsKey(resource))
            throw new ExistsException(resource + " already exists");

        queryHistory.put(resource, new HashMap<String, List<Condition>>());
        resourceViews.put(resource, new HashMap<String, View>());
    }

    @Override
    public View getView(String resource, String view) {
        return resourceViews.get(resource).get(view);
    }

    @Override
    public void createView(String resource, View view) throws ExistsException {

        if (this.queryHistory.containsKey(resource) && this.resourceViews.containsKey(resource)) {

            if (this.queryHistory.get(resource).containsKey(view) || this.resourceViews.get(resource).containsKey(view))
                throw new ExistsException(view + " already exists");

            this.resourceViews.get(resource).put(view.getName(), view);
            this.queryHistory.get(resource).put(view.getName(), new LinkedList<Condition>());
        }

        else {
            createResource(resource);
            createView(resource, view);
        }
    }

    @Override
    public boolean getQueryExists(String resource, String view, Condition conditions) {
        return getQueryExists(queryHistory.get(resource).get(view), conditions);
    }

    private boolean getQueryExists(List<Condition> existing, Condition current) {

        if (existing.contains(current))
            return true;
        else
            for (Condition exists: existing)
                if (conditionIsContained(exists, current))
                    return true;

        return false;
    }

    @SuppressWarnings("unchecked")
    private boolean conditionIsContained(Condition exists, Condition current) {

//        if (exists.getField().equals(current.getField())) {
//
//            if (exists.getOperator().equals(current.getOperator())) {
//
//                if (exists.getValue().getClass() != current.getValue().getClass()) return false;
//
//                switch (exists.getOperator()) {
//                    case GT: if (((T) exists.getValue()).compareTo(current.getValue()) < 0) return true; return false;
//                    case LT: if (((T)exists.getValue()).compareTo(current.getValue()) > 0) return true; return false;
//                    case EQ: if (exists.getValue().equals(current.getValue())) return true; return false;
//                    default: return false;
//                }
//            }
//        }


        return false;
    }

    @Override
    public void setQueryExists(String resource, String view, Condition conditions) throws DoesNotExistException {

        if (this.queryHistory.containsKey(resource))
            if (this.queryHistory.get(resource).containsKey(view))
                this.queryHistory.get(resource).get(view).addAll(Arrays.asList(conditions));
            else throw new DoesNotExistException(view + " does not exist");
        else
            throw new DoesNotExistException(resource + " does not exist");
    }
}
