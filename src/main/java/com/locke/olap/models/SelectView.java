package com.locke.olap.models;

import com.locke.olap.impl.Condition;

import java.util.List;
import java.util.Map;

/**
 * @author David Gilmore
 * @date 4/18/14
 */
public class SelectView extends View {

    private View from;
    private List<String> groupBy;
    private Map<String, String> functions;
    private List<Condition> whereConditions;


    public List<String> getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(List<String> groupBy) {
        this.groupBy = groupBy;
    }

    public View getFrom() {
        return from;
    }

    public void setFrom(View from) {
        this.from = from;
    }

    public Map<String, String> getFunctions() {
        return functions;
    }

    public void setFunctions(Map<String, String> functions) {
        this.functions = functions;
    }

    public List<Condition> getWhereConditions() {
        return whereConditions;
    }

    public void setWhereConditions(List<Condition> whereConditions) {
        this.whereConditions = whereConditions;
    }
}
