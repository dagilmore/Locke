package com.locke.olap.models;

import java.util.List;
import java.util.Map;

/**
 * @author David Gilmore
 * @date 4/18/14
 */
public class JoinView extends View {

    public enum Join { INNER, LEFT_OUTER, RIGHT_OUTER, FULL_OUTER, CROSS }

    private View left;
    private View right;
    private List<Condition> on;
    private List<Condition> where;
    private List<String> group;
    private Join join;
    private Map<String, String> functions;
    private Map<String, String> alias;
    private boolean distinct;


    public View getLeft() {
        return left;
    }

    public void setLeft(View left) {
        this.left = left;
    }

    public View getRight() {
        return right;
    }

    public void setRight(View right) {
        this.right = right;
    }

    public List<Condition> getOn() {
        return on;
    }

    public void setOn(List<Condition> on) {
        this.on = on;
    }

    public List<Condition> getWhere() {
        return where;
    }

    public void setWhere(List<Condition> where) {
        this.where = where;
    }

    public List<String> getGroup() {
        return group;
    }

    public void setGroup(List<String> group) {
        this.group = group;
    }

    public Join getJoin() {
        return join;
    }

    public void setJoin(Join join) {
        this.join = join;
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

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }
}
