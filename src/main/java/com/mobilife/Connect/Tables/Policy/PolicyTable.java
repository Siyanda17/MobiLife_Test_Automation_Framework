package com.mobilife.Connect.Tables.Policy;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class PolicyTable {
    private int id;
    private Integer term;
    private Integer active;
    private LocalDateTime anniversaryDate;
    private String uniquePolicyNumber;
    private Long individualId;
    private Integer age;
    private String IDNumber;
    private String firstName;
    private String surname;
    private String emailAddress;
    private String cellphone;
    private Long productId;
    private String productName;
    private String bankName;
    private String bankAccountNumber;
    private Integer debitOrderDay;
    private Double sumAssured;
    private Double basePremium;
    private Double grossPremium;
    private Double nettPremium;
    private Float discountPercentage;
    private LocalDateTime dateCreated;
    private Float discountAmount;
    private LocalDateTime inceptionDate;
    private LocalDateTime waitingPeriodEndDate;
    private String bankBranchCode;
    private Integer calculatedBenefitOptionId;
    private Long productVersion;
    private Timestamp lastUpdated;
    private String contractPaymentStatus;
    private String contractPaymentStatusReason;
    private String expiryDate;
    private String contractStatusReason;
    private Integer benefitAmount;
    private Date firstDebitDate;
    private Integer salaryDay;
    private Integer salesEvent;
    private String searchMetaInfo;
    private String demographic;
    private Date contractStatusReasonDate;
    private Integer deleted;
    private LocalDateTime lastAnniversaryDate;
    private Integer collectionTrackingDays;
    private Date finalSkipDate;
    private Long consultantCodeId;
    private LocalDateTime contractStatusDateTime;
    private String automaticBenefitIncrease;
    private Integer waiveWaitingPeriod;
    private Integer collectionMethod;
    private Float policyFee;
    private Integer originalProductId;
    private Date contractPaymentStatusDate;
    private String additionalData;
    private Double coverPercentage;
    private Date dateOfCoverPercentage;
    private Integer bankDetailsId;
    private Integer tenantId;
    private String passportNumber;
    private String bankAccountType;
    private String bankProtocolCount;
    private Integer migrated;
    private String automaticContributionIncrease;
    private String affinityName;
    private String externalPolicyNumber;
    private Integer premiumCount;
    private String passportData;
    private Integer policyGroup;

    public PolicyTable(){}

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public Integer getTerm () {
        return term;
    }

    public void setTerm (Integer term) {
        this.term = term;
    }

    public Integer getActive () {
        return active;
    }

    public void setActive (Integer active) {
        this.active = active;
    }

    public LocalDateTime getAnniversaryDate () {
        return anniversaryDate;
    }

    public void setAnniversaryDate (LocalDateTime anniversaryDate) {
        this.anniversaryDate = anniversaryDate;
    }

    public void setUniquePolicyNumber (String uniquePolicyNumber) {
        this.uniquePolicyNumber = uniquePolicyNumber;
    }

    public String getUniquePolicyNumber () {
        return uniquePolicyNumber;
    }


    public Long getIndividualId () {
        return individualId;
    }

    public void setIndividualId (Long individualId) {
        this.individualId = individualId;
    }

    public Integer getAge () {
        return age;
    }

    public void setAge (Integer age) {
        this.age = age;
    }

    public String getIDNumber () {
        return IDNumber;
    }

    public void setIDNumber (String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getFirstName () {
        return firstName;
    }

    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }

    public String getSurname () {
        return surname;
    }

    public void setSurname (String surname) {
        this.surname = surname;
    }

    public String getEmailAddress () {
        return emailAddress;
    }

    public void setEmailAddress (String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCellphone () {
        return cellphone;
    }

    public void setCellphone (String cellphone) {
        this.cellphone = cellphone;
    }

    public Long getProductId () {
        return productId;
    }

    public void setProductId (Long productId) {
        this.productId = productId;
    }

    public String getProductName () {
        return productName;
    }

    public void setProductName (String productName) {
        this.productName = productName;
    }

    public String getBankName () {
        return bankName;
    }

    public void setBankName (String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccountNumber () {
        return bankAccountNumber;
    }

    public void setBankAccountNumber (String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public Integer getDebitOrderDay () {
        return debitOrderDay;
    }

    public void setDebitOrderDay (Integer debitOrderDay) {
        this.debitOrderDay = debitOrderDay;
    }

    public Double getSumAssured () {
        return sumAssured;
    }

    public void setSumAssured (Double sumAssured) {
        this.sumAssured = sumAssured;
    }

    public Double getBasePremium () {
        return basePremium;
    }

    public void setBasePremium (Double basePremium) {
        this.basePremium = basePremium;
    }

    public Double getGrossPremium () {
        return grossPremium;
    }

    public void setGrossPremium (Double grossPremium) {
        this.grossPremium = grossPremium;
    }

    public Double getNettPremium () {
        return nettPremium;
    }

    public void setNettPremium (Double nettPremium) {
        this.nettPremium = nettPremium;
    }

    public Float getDiscountPercentage () {
        return discountPercentage;
    }

    public void setDiscountPercentage (Float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public LocalDateTime getDateCreated () {
        return dateCreated;
    }

    public void setDateCreated (LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Float getDiscountAmount () {
        return discountAmount;
    }

    public void setDiscountAmount (Float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public LocalDateTime getInceptionDate () {
        return inceptionDate;
    }

    public void setInceptionDate (LocalDateTime inceptionDate) {
        this.inceptionDate = inceptionDate;
    }

    public LocalDateTime getWaitingPeriodEndDate () {
        return waitingPeriodEndDate;
    }

    public void setWaitingPeriodEndDate (LocalDateTime waitingPeriodEndDate) {
        this.waitingPeriodEndDate = waitingPeriodEndDate;
    }

    public String getBankBranchCode () {
        return bankBranchCode;
    }

    public void setBankBranchCode (String bankBranchCode) {
        this.bankBranchCode = bankBranchCode;
    }

    public Integer getCalculatedBenefitOptionId () {
        return calculatedBenefitOptionId;
    }

    public void setCalculatedBenefitOptionId (Integer calculatedBenefitOptionId) {
        this.calculatedBenefitOptionId = calculatedBenefitOptionId;
    }

    public Long getProductVersion () {
        return productVersion;
    }

    public void setProductVersion (Long productVersion) {
        this.productVersion = productVersion;
    }

    public Timestamp getLastUpdated () {
        return lastUpdated;
    }

    public void setLastUpdated (Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getContractPaymentStatus () {
        return contractPaymentStatus;
    }

    public void setContractPaymentStatus (String contractPaymentStatus) {
        this.contractPaymentStatus = contractPaymentStatus;
    }

    public String getContractPaymentStatusReason () {
        return contractPaymentStatusReason;
    }

    public void setContractPaymentStatusReason (String contractPaymentStatusReason) {
        this.contractPaymentStatusReason = contractPaymentStatusReason;
    }

    public String getExpiryDate () {
        return expiryDate;
    }

    public void setExpiryDate (String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getContractStatusReason () {
        return contractStatusReason;
    }

    public void setContractStatusReason (String contractStatusReason) {
        this.contractStatusReason = contractStatusReason;
    }

    public Integer getBenefitAmount () {
        return benefitAmount;
    }

    public void setBenefitAmount (Integer benefitAmount) {
        this.benefitAmount = benefitAmount;
    }

    public Date getFirstDebitDate () {
        return firstDebitDate;
    }

    public void setFirstDebitDate (Date firstDebitDate) {
        this.firstDebitDate = firstDebitDate;
    }

    public Integer getSalaryDay () {
        return salaryDay;
    }

    public void setSalaryDay (Integer salaryDay) {
        this.salaryDay = salaryDay;
    }

    public Integer getSalesEvent () {
        return salesEvent;
    }

    public void setSalesEvent (Integer salesEvent) {
        this.salesEvent = salesEvent;
    }

    public String getSearchMetaInfo () {
        return searchMetaInfo;
    }

    public void setSearchMetaInfo (String searchMetaInfo) {
        this.searchMetaInfo = searchMetaInfo;
    }

    public String getDemographic () {
        return demographic;
    }

    public void setDemographic (String demographic) {
        this.demographic = demographic;
    }

    public Date getContractStatusReasonDate () {
        return contractStatusReasonDate;
    }

    public void setContractStatusReasonDate (Date contractStatusReasonDate) {
        this.contractStatusReasonDate = contractStatusReasonDate;
    }

    public Integer getDeleted () {
        return deleted;
    }

    public void setDeleted (Integer deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getLastAnniversaryDate () {
        return lastAnniversaryDate;
    }

    public void setLastAnniversaryDate (LocalDateTime lastAnniversaryDate) {
        this.lastAnniversaryDate = lastAnniversaryDate;
    }

    public Integer getCollectionTrackingDays () {
        return collectionTrackingDays;
    }

    public void setCollectionTrackingDays (Integer collectionTrackingDays) {
        this.collectionTrackingDays = collectionTrackingDays;
    }

    public Date getFinalSkipDate () {
        return finalSkipDate;
    }

    public void setFinalSkipDate (Date finalSkipDate) {
        this.finalSkipDate = finalSkipDate;
    }

    public Long getConsultantCodeId () {
        return consultantCodeId;
    }

    public void setConsultantCodeId (Long consultantCodeId) {
        this.consultantCodeId = consultantCodeId;
    }

    public LocalDateTime getContractStatusDateTime () {
        return contractStatusDateTime;
    }

    public void setContractStatusDateTime (LocalDateTime contractStatusDateTime) {
        this.contractStatusDateTime = contractStatusDateTime;
    }

    public String getAutomaticBenefitIncrease () {
        return automaticBenefitIncrease;
    }

    public void setAutomaticBenefitIncrease (String automaticBenefitIncrease) {
        this.automaticBenefitIncrease = automaticBenefitIncrease;
    }

    public Integer getWaiveWaitingPeriod () {
        return waiveWaitingPeriod;
    }

    public void setWaiveWaitingPeriod (Integer waiveWaitingPeriod) {
        this.waiveWaitingPeriod = waiveWaitingPeriod;
    }

    public Integer getCollectionMethod () {
        return collectionMethod;
    }

    public void setCollectionMethod (Integer collectionMethod) {
        this.collectionMethod = collectionMethod;
    }

    public Float getPolicyFee () {
        return policyFee;
    }

    public void setPolicyFee (Float policyFee) {
        this.policyFee = policyFee;
    }

    public Integer getOriginalProductId () {
        return originalProductId;
    }

    public void setOriginalProductId (Integer originalProductId) {
        this.originalProductId = originalProductId;
    }

    public Date getContractPaymentStatusDate () {
        return contractPaymentStatusDate;
    }

    public void setContractPaymentStatusDate (Date contractPaymentStatusDate) {
        this.contractPaymentStatusDate = contractPaymentStatusDate;
    }

    public String getAdditionalData () {
        return additionalData;
    }

    public void setAdditionalData (String additionalData) {
        this.additionalData = additionalData;
    }

    public Double getCoverPercentage () {
        return coverPercentage;
    }

    public void setCoverPercentage (Double coverPercentage) {
        this.coverPercentage = coverPercentage;
    }

    public Date getDateOfCoverPercentage () {
        return dateOfCoverPercentage;
    }

    public void setDateOfCoverPercentage (Date dateOfCoverPercentage) {
        this.dateOfCoverPercentage = dateOfCoverPercentage;
    }

    public Integer getBankDetailsId () {
        return bankDetailsId;
    }

    public void setBankDetailsId (Integer bankDetailsId) {
        this.bankDetailsId = bankDetailsId;
    }

    public Integer getTenantId () {
        return tenantId;
    }

    public void setTenantId (Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getPassportNumber () {
        return passportNumber;
    }

    public void setPassportNumber (String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getBankAccountType () {
        return bankAccountType;
    }

    public void setBankAccountType (String bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    public String getBankProtocolCount () {
        return bankProtocolCount;
    }

    public void setBankProtocolCount (String bankProtocolCount) {
        this.bankProtocolCount = bankProtocolCount;
    }

    public Integer getMigrated () {
        return migrated;
    }

    public void setMigrated (Integer migrated) {
        this.migrated = migrated;
    }

    public String getAutomaticContributionIncrease () {
        return automaticContributionIncrease;
    }

    public void setAutomaticContributionIncrease (String automaticContributionIncrease) {
        this.automaticContributionIncrease = automaticContributionIncrease;
    }

    public String getAffinityName () {
        return affinityName;
    }

    public void setAffinityName (String affinityName) {
        this.affinityName = affinityName;
    }

    public String getExternalPolicyNumber () {
        return externalPolicyNumber;
    }

    public void setExternalPolicyNumber (String externalPolicyNumber) {
        this.externalPolicyNumber = externalPolicyNumber;
    }

    public Integer getPremiumCount () {
        return premiumCount;
    }

    public void setPremiumCount (Integer premiumCount) {
        this.premiumCount = premiumCount;
    }

    public String getPassportData () {
        return passportData;
    }

    public void setPassportData (String passportData) {
        this.passportData = passportData;
    }

    public Integer getPolicyGroup () {
        return policyGroup;
    }

    public void setPolicyGroup (Integer policyGroup) {
        this.policyGroup = policyGroup;
    }
}
