package org.locke.query.models;

/**
 * @author David Gilmore
 * @date 4/18/14
 */
public class SimpleCondition<T extends Comparable> extends Condition {

    private String view;
    private T field;
    private T value;
    private Comparator comparator;

    public SimpleCondition() {}

    public SimpleCondition(String view, T field, T value, Comparator comparator) {
        this.view = view;
        this.field = field;
        this.value = value;
        this.comparator = comparator;
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

    public Comparator getComparator() {
        return comparator;
    }

    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleCondition simpleCondition = (SimpleCondition) o;

        if (field != null ? !field.equals(simpleCondition.field) : simpleCondition.field != null) return false;
        if (comparator != null ? !comparator.equals(simpleCondition.comparator) : simpleCondition.comparator != null) return false;
        if (value != null ? !value.equals(simpleCondition.value) : simpleCondition.value != null) return false;
        if (view != null ? !view.equals(simpleCondition.view) : simpleCondition.view != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = view != null ? view.hashCode() : 0;
        result = 31 * result + (field != null ? field.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (comparator != null ? comparator.hashCode() : 0);

        return result;
    }

    @Override
    public Condition cnf() {
        return this;
    }
}

