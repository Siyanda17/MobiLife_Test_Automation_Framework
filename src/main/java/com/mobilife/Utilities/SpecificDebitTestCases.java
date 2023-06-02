package com.mobilife.Utilities;

public enum SpecificDebitTestCases {
    T1("Add Specific Debit"),
    T2("Add Specific Debit without filling in fields"),
    T3("Action Date"),
    T4("Check Submitted"),
    T5("Delete Specific Debit"),
    T6(" Add Specific Debit for inactive CPS");
    private String testName;

    SpecificDebitTestCases (String s) {
        this.testName = s;
    }

    public String getTestName () {
        return testName;
    }
}
