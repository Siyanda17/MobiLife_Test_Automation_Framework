//package com.mobilife.Connect.Tables.SpecificDebit;
//
//import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.core.ResultSetExtractor;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
///**
// * Represents the Specific Debit Result Set Extractor
// * Extracts results from a query adds to a Collection
// *
// * @author Yakhuxolo Mxabo
// * @version 1.0
// * @deprecated  not define yet continue with {@link SpecificDebitRowMapper}
// **/
//@Deprecated
//public class SpecificDebitResultSetExtractor implements ResultSetExtractor<SpecificDebitTable> {
//    /**
//     * Implementations must implement this method to process the entire ResultSet.
//     *
//     * @param rs the ResultSet to extract data from. Implementations should
//     *           not close this: it will be closed by the calling JdbcTemplate.
//     * @return an arbitrary result object, or {@code null} if none
//     * (the extractor will typically be stateful in the latter case).
//     * @throws SQLException        if an SQLException is encountered getting column
//     *                             values or navigating (that is, there's no need to catch SQLException)
//     * @throws DataAccessException in case of custom exceptions
//     */
////    @Override
////    public SpecificDebitTable extractData (ResultSet rs) throws SQLException, DataAccessException {
////        if (rs.next()) {
////            // Extract data from the current row
////
////        }
////
////    }
////}
