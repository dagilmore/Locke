package org.locke.query.impl;

import ca.krasnay.sqlbuilder.SelectBuilder;
import ca.krasnay.sqlbuilder.SubSelectBuilder;
import org.locke.query.api.ViewGenerator;
import org.locke.query.error.MalformedViewException;
import org.locke.query.models.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author David Gilmore
 * @date 4/12/14
 */
public class HiveQLViewGenerator implements ViewGenerator {

    private Map<Comparator, String> ops;

    public HiveQLViewGenerator() {
        this.ops = new HashMap<>();

        this.ops.put(Comparator.EQ, " = ");
        this.ops.put(Comparator.NE, " != ");
        this.ops.put(Comparator.GT, " > ");
        this.ops.put(Comparator.LT, " < ");
        this.ops.put(Comparator.GE, " >= ");
        this.ops.put(Comparator.LE, " <= ");
    }

    @Override
    public String createQuery(View view) throws MalformedViewException {
        return createQuery(view, false);
    }

    private String createQuery(View view, boolean subQuery) throws MalformedViewException {

        if (view.getClass() == TableView.class) {
            return view.getResource() != null ? view.getResource() + "." + view.getName() : view.getName();
        }

        else if (view.getClass() == JoinView.class) {

            JoinView j = (JoinView) view;

            String query = (subQuery) ? " ( ": "";

            switch (j.getJoin()) {
                case INNER: query = createQuery(j.getLeft()) + " join " + createQuery(j.getRight()); break;
                case CROSS: query =  createQuery(j.getLeft()) + " cross join " + createQuery(j.getRight()); break;
                case LEFT_OUTER: query =  createQuery(j.getLeft()) + " left outer join " + createQuery(j.getRight()); break;
                case FULL_OUTER: query =  createQuery(j.getLeft()) + " full outer join " + createQuery(j.getRight()); break;
                case RIGHT_OUTER: query = createQuery(j.getLeft()) + " right outer join " + createQuery(j.getRight()); break;
            }

            if (j.getOn() != null) {

                query += " ON ";


//                for (Condition cond: j.getOn()) {
//                    builder.where(cond.getView() + "." + cond.getField() + ops.get(cond.getOperator()) + cond.getValue());
//                }
            }

            if (j.getWhere() != null) {

                query += " WHERE ";

//                for (Condition cond: j.getWhere()) {
//                    builder.where(cond.getView() + "." + cond.getField() + ops.get(cond.getOperator()) + cond.getValue());
//                }
            }

            query += (subQuery) ? " ) " + j.getName() + " " : "";

            return query;
        }

        else if (view.getClass() == SelectView.class) {

            SelectView selectView = (SelectView) view;

            SelectBuilder query;

            if (subQuery) {
                query = new SubSelectBuilder(view.getName());
            }
            else {
                query = new SelectBuilder();
            }

            query = (selectView.isDistinct()) ? query.distinct(): query;

            //Add columns to the query
            if (selectView.getColumns() != null)
                for (String col: selectView.getColumns()) {

                    String column = col;

                    if (selectView.getFunctions() != null && selectView.getFunctions().containsKey(col))
                        column = selectView.getFunctions().get(col) + "(" + col + ")";

                    if (selectView.getAlias() != null && selectView.getAlias().containsKey(col))
                        column += " as " + selectView.getAlias().get(col);

                    query.column(column);
            }

            //Recursively add "from" clauses
            if (selectView.getFrom() != null)
                query.from(createQuery(selectView.getFrom(), true));

            if (selectView.getWhere() != null) {
                //TODO: implement condition set logic
//                for (Condition cond: selectView.getWhere()) {
//                    query.where(cond.getView() + "." + cond.getField() + ops.get(cond.getOperator()) + cond.getValue());
//                }
            }

            if (selectView.getGroup() != null) {
                for (Object col : selectView.getGroup()) {
                    query.groupBy( (String) col);
                }
            }

            return query.toString();
        }

        return "";
    }
}
