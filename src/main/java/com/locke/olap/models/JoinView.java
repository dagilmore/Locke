package com.locke.olap.models;

import java.util.List;

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
    private Join type;


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

    public Join getType() {
        return type;
    }

    public void setType(Join type) {
        this.type = type;
    }
}
