package com.locke.olap.impl;

import com.locke.olap.View;
import com.locke.olap.ViewGenerator;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author David Gilmore
 * @date 4/12/14
 */
public class JdbcSqlViewGenerator implements ViewGenerator {

    private Connection conn;

    public JdbcSqlViewGenerator(Connection conn) throws SQLException {

        this.conn = conn;
    }

    @Override
    public String createQuery(View view) {

        if (view.getClass() == TableView.class) {
            return "table";
        }
        else if (view.getClass() == JoinView.class) {
            return "join";
        }
        else if (view.getClass() == SelectView.class) {
            return "select";
        }
//        SelectBuilder query = new SelectBuilder();
//
//
//        SubSelectBuilder pacContributions = new SubSelectBuilder("pac_contributions");
//
//        pacContributions
//
//                .column("cid", true)
//                .column("SUM(amount) as industry_contributions")
//                .column("congress", true)
//
//                .from("crp.pac_to_candidate_contributions");
//
//        SubSelectBuilder industryContributions = new SubSelectBuilder("industry_contributions");
//
//        industryContributions
//
//                .column("recip_id", true)
//                .column("SUM(amount) as pac_contributions")
//                .column("congress", true)
//
//                .from("crp.individual_contributions");
//
//        SubSelectBuilder combinedContributions = new SubSelectBuilder("combined_contributions");
//
//        combinedContributions
//
//                .column("cid")
//                .column("pac_contributions")
//                .column("industry_contributions")
//                .column("pac_contributions.congress")
//
//                .from(pacContributions.toString())
//                .join(industryContributions.toString() + " ON cid = recip_id");
//
//        query
//                .distinct()
//                .column("bioguide_id")
//                .column("pac_contributions")
//                .column("industry_contributions")
//                .column("congress")
//
//                .from(combinedContributions.toString())
//                .join("entities.legislators l ON cid = opensecrets_id");
//
//        return query.toString();
        return null;
    }
}
