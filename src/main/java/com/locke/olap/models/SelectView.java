package com.locke.olap.models;

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
    private Map<String, String> alias;
    private List<Condition> where;
    private boolean distinct;

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

    public Map<String, String> getAlias() {
        return alias;
    }

    public void setAlias(Map<String, String> alias) {
        this.alias = alias;
    }

    public List<Condition> getWhere() {
        return where;
    }

    public void setWhere(List<Condition> where) {
        this.where = where;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }
}
