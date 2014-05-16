package com.locke.olap.impl;


import com.locke.olap.models.*;
import com.locke.olap.models.Condition.Operator;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author David Gilmore
 * @date 4/17/14
 */
public class HiveQLViewGeneratorUnitTest {

    private HiveQLViewGenerator hiveQLViewGenerator;

    @Before
    public void setUp() throws Exception {
        this.hiveQLViewGenerator = new HiveQLViewGenerator();
    }

    @Test
    public void testCreateView__SimpleSelectFromTable() throws Exception {

        SelectView selectView = new SelectView();

        selectView.setResource("test");
        selectView.setName("test_select_view");

        List<String> columns = new LinkedList<>();

        columns.add("name");
        columns.add("type");
        columns.add("amount");

        selectView.setColumns(columns);

        TableView from = new TableView();

        from.setResource("test");
        from.setName("test_table");

        selectView.setFrom(from);

        String query = this.hiveQLViewGenerator.createQuery(selectView);

        assertEquals("select name, type, amount from test.test_table", query);
    }

    @Test
    public void testCreateView__SimpleSelectFromTableNoTableResource() throws Exception {

        SelectView selectView = new SelectView();

        selectView.setResource("test");
        selectView.setName("test_select_view");

        List<String> columns = new LinkedList<>();

        columns.add("name");
        columns.add("type");
        columns.add("amount");

        selectView.setColumns(columns);

        TableView from = new TableView();

        from.setName("test_table");

        selectView.setFrom(from);

        String query = this.hiveQLViewGenerator.createQuery(selectView);

        assertEquals("select name, type, amount from test_table", query);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreateView__NestedSelectWithFunctions() throws Exception {

        SelectView from = new SelectView();

        List<String> subColumns = new LinkedList<>();

        subColumns.add("name");
        subColumns.add("type");
        subColumns.add("amount");

        Map<String, String> functions = new HashMap<>();
        functions.put("amount", "SUM");

        ConditionSet where = new ConditionSet();
        where.and(new Condition("test_subquery", "name", "tom", Operator.EQ));
        where.and(new Condition("test_subquery", "type", "person", Operator.EQ));

        List<String> group = new LinkedList<>();
        group.add("name");
        group.add("type");

        from.setResource("test");
        from.setName("test_subquery");
        from.setColumns(subColumns);
        from.setFunctions(functions);
        from.setGroup(group);
        from.setWhere(where);


        SelectView selectView = new SelectView();

        List<String> columns = new ArrayList<>();
        columns.add("name");
        columns.add("type");
        columns.add("total");

        selectView.setResource("test");
        selectView.setName("test_select_view");
        selectView.setColumns(columns);
        selectView.setFrom(from);

        String query = this.hiveQLViewGenerator.createQuery(selectView);

        assertNotNull(query);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreateView__SelectWithJoin() throws Exception {

        SelectView selectView = new SelectView();

        List<String> columns = new ArrayList<>(
                Arrays.asList("pac_contributions.bioguide_id",
                              "individual_contributions",
                              "pac_contributions")
        );

        selectView.setColumns(columns);


            JoinView leftJoinView = new JoinView();

                SelectView leftLeftSubSelect = new SelectView();

                columns = new ArrayList<>(
                        Arrays.asList("bioguide_id",
                                      "amount")
                );

                leftLeftSubSelect.setColumns(columns);

                Map<String, String> functions = new HashMap<>(), aliases = new HashMap<>();

                functions.put("amount", "sum");
                aliases.put("amount", "pac_contributions");

                leftLeftSubSelect.setFunctions(functions);
                leftLeftSubSelect.setAlias(aliases);

            leftJoinView.setLeft(leftLeftSubSelect);

                TableView leftRightTableView = new TableView();

                leftRightTableView.setResource("entities");
                leftRightTableView.setName("legislators");


            leftJoinView.setRight(leftRightTableView);

            Condition on = new Condition("", "cid", "opensecrets_id", Operator.EQ);

            Condition where = new Condition("", "cid", "'24K'", Operator.EQ);

            List<String> group = new ArrayList<>(Arrays.asList("bioguide_id"));

            leftJoinView.setOn(on);
            leftJoinView.setWhere(where);
            leftJoinView.setJoin(JoinView.Join.INNER);
            leftJoinView.setGroup(group);
            leftJoinView.setName("pac_contributions");


            JoinView rightJoinView = new JoinView();

                SelectView rightLeftSubSelect = new SelectView();

                columns = new ArrayList<>(
                        Arrays.asList("bioguide_id",
                                      "amount")
                );

                rightLeftSubSelect.setColumns(columns);

                functions = new HashMap<>();
                aliases = new HashMap<>();

                functions.put("amount", "sum");
                aliases.put("amount", "individual_contributions");

                rightLeftSubSelect.setFunctions(functions);
                rightLeftSubSelect.setAlias(aliases);

            rightJoinView.setLeft(rightLeftSubSelect);

                TableView rightRightTableView = new TableView();

                rightRightTableView.setResource("entities");
                rightRightTableView.setName("legislators");


            rightJoinView.setRight(rightRightTableView);

            on = new Condition("", "recip_id", "opensecrets_id", Operator.EQ);


            rightJoinView.setOn(on);
            rightJoinView.setWhere(where);
            rightJoinView.setJoin(JoinView.Join.INNER);
            rightJoinView.setGroup(group);
            rightJoinView.setName("individual_contributions");

        functions = new HashMap<>();
        aliases = new HashMap<>();

        functions.put("amount", "sum");
        aliases.put("amount", "individual_contributions");

        JoinView joinView = new JoinView();

        joinView.setLeft(leftJoinView);
        joinView.setRight(rightJoinView);


        joinView.setJoin(JoinView.Join.FULL_OUTER);

        selectView.setFrom(joinView);

        on = new Condition("", "cid", "opensecrets_id", Operator.EQ);

        where = new Condition("", "cid", "'24K'", Operator.EQ);

        String query = hiveQLViewGenerator.createQuery(selectView);

        assertNotNull(query);
    }
}

// SELECT DISTINCT
//       pac_contributions.bioguide_id
//     , individual_contributions
//     , pac_contributions
// FROM
//
//   (SELECT
//         bioguide_id
//       , SUM(amount) as pac_contributions
//   FROM
//       crp.pac_to_candidate_contributions
//   JOIN
//         entities.legislators
//     ON
//         cid = opensecrets_id
//   WHERE
//       type = '24K'
//   GROUP BY
//         bioguide_id
//   ) pac_contributions
// FULL OUTER JOIN
//
//   (SELECT
//         bioguide_id
//       , SUM(amount) as individual_contributions
//   FROM
//       crp.individual_contributions
//   JOIN
//         entities.legislators
//     ON
//         recip_id = opensecrets_id
//   GROUP BY
//         bioguide_id ) individual_contributions
// ON
//     pac_contributions.bioguide_id = individual_contributions.bioguide_id;
