package com.locke.olap.models;

/**
 * @author David Gilmore
 * @date 4/18/14
 */
public class Condition<T> extends Conditional {

    public enum Operator { EQ, NE, GT, LT, GE, LE }

    private String view;
    private T field;
    private T value;
    private Operator operator;

    public Condition() {}

    public Condition(String view, T field, T value, Operator operator) {
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

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Condition condition = (Condition) o;

        if (field != null ? !field.equals(condition.field) : condition.field != null) return false;
        if (operator != null ? !operator.equals(condition.operator) : condition.operator != null) return false;
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

        return result;
    }
}

