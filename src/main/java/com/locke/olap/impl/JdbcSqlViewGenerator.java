package com.locke.olap.impl;

import ca.krasnay.sqlbuilder.SelectBuilder;
import ca.krasnay.sqlbuilder.SubSelectBuilder;
import com.locke.olap.ViewGenerator;
import com.locke.olap.models.JoinView;
import com.locke.olap.models.SelectView;
import com.locke.olap.models.TableView;
import com.locke.olap.models.View;

/**
 * @author David Gilmore
 * @date 4/12/14
 */
public class JdbcSqlViewGenerator implements ViewGenerator {

    @Override
    public String createQuery(View view) {
        return createQuery(view, false);
    }

    private String createQuery(View view, boolean subQuery) {

        if (view.getClass() == TableView.class) {
            return view.getResource() != null ? view.getResource() + "." + view.getName() : view.getName();
        }
        else if (view.getClass() == JoinView.class) {
            return "join";
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

            //Add columns to the query
            if (selectView.getColumns() != null)
                for (String col: selectView.getColumns()) {
                String column = col;
                if (selectView.getFunctions() != null && selectView.getFunctions().containsKey(col))
                    column = selectView.getFunctions().get(col) + "(" + col + ")";
                query.column(column);
            }

            //Recursively add "from" clauses
            if (selectView.getFrom() != null)
                query.from(createQuery(selectView.getFrom(), true));

            if (selectView.getWhere() != null) {
                for (Condition cond: selectView.getWhere()) {
                    query.where(cond.getView() + "." + cond.getLeft() + cond.getOperator() + cond.getRight());
                }
            }

            if (selectView.getGroup() != null) for (String col: selectView.getGroup()) query.groupBy(col);

            return query.toString();
        }

        return "";
    }
}
