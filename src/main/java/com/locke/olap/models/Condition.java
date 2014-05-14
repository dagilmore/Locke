package com.locke.olap.models;

/**
 * @author David Gilmore
 * @date 4/18/14
 */
public class Condition<T extends Comparable> {

    private String view;
    private T field;
    private T value;
    private String operator;
    private Condition and;
    private Condition or;

    public Condition(String view, T field, T value, String operator) {
        this.view = view;
        this.field = field;
        this.value = value;
        this.operator = operator;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public T getField() {
        return field;
    }

    public void setField(T field) {
        this.field = field;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Condition getAnd() {
        return and;
    }

    public void setAnd(Condition and) {
        this.and = and;
    }

    public Condition getOr() {
        return or;
    }

    public void setOr(Condition or) {
        this.or = or;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Condition condition = (Condition) o;

        if (and != null ? !and.equals(condition.and) : condition.and != null) return false;
        if (field != null ? !field.equals(condition.field) : condition.field != null) return false;
        if (operator != null ? !operator.equals(condition.operator) : condition.operator != null) return false;
        if (or != null ? !or.equals(condition.or) : condition.or != null) return false;
        if (value != null ? !value.equals(condition.value) : condition.value != null) return false;
        if (view != null ? !view.equals(condition.view) : condition.view != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = view != null ? view.hashCode() : 0;
        result = 31 * result + (field != null ? field.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (and != null ? and.hashCode() : 0);
        result = 31 * result + (or != null ? or.hashCode() : 0);
        return result;
    }
}
