package org.locke.query.impl;

import junit.framework.Assert;
import org.locke.query.api.ViewSerde;
import org.locke.query.models.JoinView;
import org.locke.query.models.SelectView;
import org.locke.query.models.TableView;
import org.locke.query.models.View;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author David Gilmore
 * @date 5/15/14
 */
public class JsonViewSerdeTest {

    private ViewSerde viewSerde;

    @Before
    public void setUp() throws Exception {

        this.viewSerde = new JsonViewSerde();
    }

    @Test
    public void testDeserialize() throws Exception {

        String json =
                "{\n" +
                "   \"resource\": \"contributions\",\n" +
                "   \"name\": \"summary\",\n" +
                "   \"type\": \"select\",\n" +
                "   \"distinct\": \"true\",\n" +
                "   \"columns\": [\n" +
                "      \"pac_contributions.bioguide_id\"\n" +
                "    , \"individual_contributions\"\n" +
                "    , \"pac_contributions\"\n" +
                "   ],\n" +
                "   \"from\": {\n" +
                "        \"name\": \"pac_contributions\",\n" +
                "        \"type\": \"join\",\n" +
                "        \"join\": \"FULL_OUTER\",\n" +
//                "        \"on\": { \n" +
//                "                \"field\": \"pac_contributions.bioguide_id\",\n" +
//                "                \"value\": \"individual_contributions.bioguide_id\",\n" +
//                "                \"operator\": \"EQ\"\n" +
//                "        },\n" +
                "        \"left\": {\n" +
                "            \"name\": \"pac_contributions\",\n" +
                "            \"type\": \"join\",\n" +
                "            \"join\": \"INNER\",\n" +
                "            \"columns\": [\n" +
                "                \"bioguide_id\",\n" +
                "                \"amount\"\n" +
                "            ],\n" +
                "            \"functions\": {\n" +
                "                \"amount\": \"sum\"\n" +
                "            },\n" +
                "            \"alias\": {\n" +
                "                \"amount\": \"pac_contributions\"\n" +
                "            },\n" +
//                "            \"on\": {\n" +
//                "                 \"field\": \"cid\",\n" +
//                "                 \"value\": \"opensecrets_id\",\n" +
//                "                 \"operator\": \"EQ\"   \n" +
//                "            },\n" +
//                "            \"where\": {\n" +
//                "                 \"field\": \"type\",\n" +
//                "                 \"value\": \"'24K'\",\n" +
//                "                 \"operator\": \"EQ\"   \n" +
//                "            },\n" +
                "            \"group\": [ \n" +
                "                \"bioguide_id\" \n" +
                "            ],\n" +
                "            \"left\": {\n" +
                "                \"type\": \"table\",\n" +
                "                \"resource\": \"crp\",\n" +
                "                \"name\": \"pac_to_candidate_contributions\"\n" +
                "            },\n" +
                "            \"right\": {\n" +
                "                \"type\": \"table\",\n" +
                "                \"resource\": \"entities\",\n" +
                "                \"name\": \"legislators\"\n" +
                "            }\n" +
                "        },\n" +
                "        \"right\": {\n" +
                "            \"name\": \"individual_contributions\",\n" +
                "            \"type\": \"join\",\n" +
                "            \"join\": \"INNER\",\n" +
                "            \"columns\": [\n" +
                "                \"bioguide_id\",\n" +
                "                \"amount\"\n" +
                "            ],   \n" +
                "            \"functions\": {\n" +
                "                \"amount\": \"sum\"\n" +
                "            },\n" +
                "            \"alias\": {\n" +
                "                \"amount\": \"individual_contributions\"\n" +
                "            },\n" +
//                "            \"on\": {\n" +
//                "                 \"field\": \"recip_id\",\n" +
//                "                 \"value\": \"opensecrets_id\",\n" +
//                "                 \"operator\": \"EQ\"   \n" +
//                "            },\n" +
                "            \"group\": [\n" +
                "                \"bioguide_id\"\n" +
                "            ],\n" +
                "            \"left\": {\n" +
                "                \"type\": \"table\",\n" +
                "                \"resource\": \"crp\",\n" +
                "                \"name\": \"individual_contributions\"\n" +
                "            },\n" +
                "            \"right\": {\n" +
                "                \"type\": \"table\",\n" +
                "                \"resource\": \"entities\",\n" +
                "                \"name\": \"legislators\"\n" +
                "            }\n" +
                "        }\n" +
                "   }\n" +
                "}\n";

        View view = this.viewSerde.deserialize(json);

        SelectView selectView = (SelectView) view;

        Assert.assertEquals("contributions", selectView.getResource());
        Assert.assertEquals("summary", selectView.getName());
        Assert.assertEquals(true, selectView.isDistinct());

        String[] columns = new String[]{"pac_contributions.bioguide_id","individual_contributions","pac_contributions"};
        for (String col : columns) {
            Assert.assertEquals(true, selectView.getColumns().contains(col));
        }

        JoinView fullOuterJoin = (JoinView) selectView.getFrom();

        Assert.assertEquals("pac_contributions", fullOuterJoin.getName());
        Assert.assertEquals(JoinView.Join.FULL_OUTER, fullOuterJoin.getJoin());

        JoinView leftSubJoin = (JoinView) fullOuterJoin.getLeft();
        JoinView rightSubJoin = (JoinView) fullOuterJoin.getRight();

        TableView pacToCandTable = (TableView) leftSubJoin.getLeft();
        TableView legislatorsLeft = (TableView) leftSubJoin.getRight();

        TableView indToCandTable = (TableView) rightSubJoin.getLeft();
        TableView legislatorsRight = (TableView) rightSubJoin.getRight();

        //TODO: assert more dominance!

        assertNotNull(view);
    }

    @Test
    public void testSerialize() throws Exception {

    }
}
