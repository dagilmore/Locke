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
    private List<String> group;
    private Map<String, String> functions;
    private List<Condition> where;

    public List<String> getGroup() {
        return group;
    }

    public void setGroup(List<String> group) {
        this.group = group;
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

    public List<Condition> getWhere() {
        return where;
    }

    public void setWhere(List<Condition> where) {
        this.where = where;
    }
}
