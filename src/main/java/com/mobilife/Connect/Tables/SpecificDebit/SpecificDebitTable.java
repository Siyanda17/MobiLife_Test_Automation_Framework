package com.mobilife.Connect.Tables.SpecificDebit;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a Specific Debit Table from the policies_preprod
 *
 * @author Yakhuxolo Mxabo
 * @version 1.0
 *
 **/
public class SpecificDebitTable {
    private int Id;
	private String ActionDate;
	private String PremiumMonth;
    private Double PolicyAmount;
	private int Submitted ;
	private String Notes;
    private LocalDateTime LastUpdated;
	private String SearchMetaInfo;
    private String CollectionMethod;
    private int Policy;
	private int Deleted;

    public SpecificDebitTable () {
    }

    public int getId () {
        return Id;
    }

    public void setId (int id) {
        Id = id;
    }

    public String getActionDate () {
        return ActionDate;
    }

    public void setActionDate (String actionDate) {
        ActionDate = actionDate;
    }

    public String getPremiumMonth () {
        return PremiumMonth;
    }

    public void setPremiumMonth (String premiumMonth) {
        PremiumMonth = premiumMonth;
    }

    public Double getPolicyAmount () {
        return PolicyAmount;
    }

    public void setPolicyAmount (Double policyAmount) {
        PolicyAmount = policyAmount;
    }

    public int getSubmitted () {
        return Submitted;
    }

    public void setSubmitted (int submitted) {
        Submitted = submitted;
    }

    public String getNotes () {
        return Notes;
    }

    public void setNotes (String notes) {
        Notes = notes;
    }

    public LocalDateTime getLastUpdated () {
        return LastUpdated;
    }

    public void setLastUpdated (LocalDateTime lastUpdated) {
        LastUpdated = lastUpdated;
    }

    public String getSearchMetaInfo () {
        return SearchMetaInfo;
    }

    public void setSearchMetaInfo (String searchMetaInfo) {
        SearchMetaInfo = searchMetaInfo;
    }

    public String getCollectionMethod () {
        return CollectionMethod;
    }

    public void setCollectionMethod (String collectionMethod) {
        CollectionMethod = collectionMethod;
    }

    public int getPolicy () {
        return Policy;
    }

    public void setPolicy (int policy) {
        Policy = policy;
    }

    public int getDeleted () {
        return Deleted;
    }

    public void setDeleted (int deleted) {
        Deleted = deleted;
    }
}
