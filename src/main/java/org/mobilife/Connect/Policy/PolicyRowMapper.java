package org.mobilife.Connect.Policy;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PolicyRowMapper implements RowMapper<PolicyTable> {
    @Override
    public PolicyTable mapRow (ResultSet rs, int rowNum) throws SQLException {
        PolicyTable policy = new PolicyTable();

        policy.setId(rs.getInt("Id"));
        policy.setTerm(rs.getInt("Term"));
        policy.setActive(rs.getInt("Active"));
        policy.setAnniversaryDate(rs.getTimestamp("AnniversaryDate").toLocalDateTime());
        policy.setUniquePolicyNumber(rs.getString("UniquePolicyNumber"));
        policy.setIndividualId(rs.getLong("IndividualId"));
        policy.setAge(rs.getInt("Age"));
        policy.setIDNumber(rs.getString("IDNumber"));
        policy.setFirstName(rs.getString("FirstName"));
        policy.setSurname(rs.getString("Surname"));
        policy.setEmailAddress(rs.getString("EmailAddress"));
        policy.setCellphone(rs.getString("Cellphone"));
        policy.setProductId(rs.getLong("ProductId"));
        policy.setProductName(rs.getString("ProductName"));
        policy.setBankName(rs.getString("BankName"));
        policy.setBankAccountNumber(rs.getString("BankAccountNumber"));
        policy.setDebitOrderDay(rs.getInt("DebitOrderDay"));
        policy.setSumAssured(rs.getDouble("SumAssured"));
        policy.setBasePremium(rs.getDouble("BasePremium"));
        policy.setGrossPremium(rs.getDouble("GrossPremium"));
        policy.setNettPremium(rs.getDouble("NettPremium"));
        policy.setDiscountPercentage(rs.getFloat("DiscountPercentage"));
        policy.setDateCreated(rs.getTimestamp("DateCreated").toLocalDateTime());
        policy.setDiscountAmount(rs.getFloat("DiscountAmount"));
        policy.setInceptionDate(rs.getTimestamp("InceptionDate").toLocalDateTime());
        policy.setWaitingPeriodEndDate(rs.getTimestamp("WaitingPeriodEndDate").toLocalDateTime());
        policy.setBankBranchCode(rs.getString("BankBranchCode"));
        policy.setCalculatedBenefitOptionId(rs.getInt("CalculatedBenefitOptionId"));
        policy.setProductVersion(rs.getLong("ProductVersion"));
        policy.setLastUpdated(rs.getTimestamp("LastUpdated"));
        policy.setContractPaymentStatus(rs.getString("ContractPaymentStatus"));
        policy.setContractPaymentStatusReason(rs.getString("ContractPaymentStatusReason"));
        policy.setExpiryDate(rs.getString("ExpiryDate"));
        policy.setContractStatusReason(rs.getString("ContractStatusReason"));
        policy.setBenefitAmount(rs.getInt("BenefitAmount"));
        policy.setFirstDebitDate(rs.getDate("FirstDebitDate"));
        policy.setSalaryDay(rs.getInt("SalaryDay"));
        policy.setSalesEvent(rs.getInt("SalesEvent"));
        policy.setSearchMetaInfo(rs.getString("SearchMetaInfo"));
        policy.setDemographic(rs.getString("Demographic"));
        policy.setContractStatusReasonDate(rs.getDate("ContractStatusReasonDate"));
        policy.setDeleted(rs.getInt("Deleted"));
        policy.setLastAnniversaryDate(rs.getTimestamp("LastAnniversaryDate").toLocalDateTime());
        policy.setCollectionTrackingDays(rs.getInt("CollectionTrackingDays"));
        policy.setFinalSkipDate(rs.getDate("FinalSkipDate"));
        policy.setConsultantCodeId(rs.getLong("ConsultantCodeId"));
        policy.setContractStatusDateTime(rs.getTimestamp("ContractStatusDateTime").toLocalDateTime());
        policy.setAutomaticBenefitIncrease(rs.getString("AutomaticBenefitIncrease"));
        policy.setWaiveWaitingPeriod(rs.getInt("WaiveWaitingPeriod"));
        policy.setCollectionMethod(rs.getInt("CollectionMethod"));
        policy.setPolicyFee(rs.getFloat("PolicyFee"));
        policy.setOriginalProductId(rs.getInt("OriginalProductId"));
        policy.setContractPaymentStatusDate(rs.getDate("ContractPaymentStatusDate"));
        policy.setAdditionalData(rs.getString("AdditionalData"));
        policy.setCoverPercentage(rs.getDouble("CoverPercentage"));
        policy.setDateOfCoverPercentage(rs.getDate("DateOfCoverPercentage"));
        policy.setBankDetailsId(rs.getInt("BankDetailsId"));
        policy.setTenantId(rs.getInt("TenantId"));
        policy.setPassportNumber(rs.getString("PassportNumber"));
        policy.setBankAccountType(rs.getString("BankAccountType"));
        policy.setBankProtocolCount(rs.getString("BankProtocolCount"));
        policy.setMigrated(rs.getInt("Migrated"));
        policy.setAutomaticContributionIncrease(rs.getString("AutomaticContributionIncrease"));
        policy.setAffinityName(rs.getString("AffinityName"));
        policy.setExternalPolicyNumber(rs.getString("ExternalPolicyNumber"));
        policy.setPremiumCount(rs.getInt("PremiumCount"));
        policy.setPassportData(rs.getString("PassportData"));
        policy.setPolicyGroup(rs.getInt("PolicyGroup"));

        return policy;
    }
}
