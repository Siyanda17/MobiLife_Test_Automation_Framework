package com.mobilife.Connect.Tables.SpecificDebit;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * SpecificDebitRowMapper Maps the Specific DebitTable Object to a map
 *
 * @author Yakhuxolo Mxabo
 * @version 1.0
 *
 **/
public class SpecificDebitRowMapper implements RowMapper<SpecificDebitTable> {

    @Override
    public SpecificDebitTable mapRow (ResultSet rs, int rowNum) throws SQLException {
        SpecificDebitTable specificDebitTable = new SpecificDebitTable();
        specificDebitTable.setId(rs.getInt("id"));
        specificDebitTable.setPolicy(rs.getInt("Policy"));
        specificDebitTable.setPremiumMonth(rs.getString("PremiumMonth"));
        specificDebitTable.setActionDate(rs.getString("ActionDate"));
        specificDebitTable.setNotes(rs.getString("Notes"));
        specificDebitTable.setCollectionMethod(rs.getString("CollectionMethod"));
        specificDebitTable.setDeleted(rs.getInt("Deleted"));
        specificDebitTable.setSubmitted(rs.getInt("Submitted"));
        specificDebitTable.setPolicyAmount(rs.getDouble("PolicyAmount"));
        specificDebitTable.setLastUpdated(rs.getString("LastUpdated"));

        // Set other properties as needed
        return specificDebitTable;
    }
}
