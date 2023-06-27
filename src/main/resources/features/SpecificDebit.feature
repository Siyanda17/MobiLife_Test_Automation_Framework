Feature: Specific Debit
  As a User I want to be able to create a Specific Debit
  In order to Collect Premiums
#After you're done attach screenshots to the report
  #After  you finish use policies from the database to conduct test
  #Make this more dynamic fetch test policies with
  Background: On Mobility
  @Done
    @SuccessfulScenario
  Scenario: Successful login
    Given I am on the login page
    When I enter my valid username and password
    And click the "Login" button
    Then I should be redirected to the homepage
    And see a welcome message with my "Welcome"

    @Done
    Scenario: Go to Specific Debit
      Given I am on Specific Debit Tab

      #Detect Duplicate Specific Debits
  @Done
  Scenario Outline: Add Specific Debit without filling in fields

    When  I Add a Specific Debit
    Then Specific Debit Details window appears
    And Find the policy "<uniquePolicyNumber>" "<policyId>"
    Then the policy is "Selected"
    And Buttons "Save" and "Cancel" Appear an the bottom
    And Matches the current Nett Premium
    And Click Save
    Then Error message will show below the empty textboxes
     # Action date cannot be in the past(Past dates on the date picker are greyed out)
      # Action date should not be a non collection day(weekend days and holidays should be greyed out)
    Then Enter a Weekend Action Date and in the past
    Then Enter Action Date
    And If it's after "14:30" Mobility will show an error text
    Examples:
      | policyId | uniquePolicyNumber |
      | 1467372  | TMA134FF0          |
      | 108603   | P0057609002L01     |
      | 1576942  | LFA00004           |
      | 5249709  | NFA00523           |






  @Done
  Scenario: Check Submitted
    When  Submitted checkbox empty(no tick) before the linked collection item is Submitted
    And Cannot tick Submitted checkbox



      #Add specific debit for policy with inactive contract payment Status
  @Done
  Scenario:  Add Specific Debit for inactive CPS
    When Adding for inactive contract payment status




  @Done
    @SuccessfulScenario
    Scenario: Add Specific Debit
      When I Add a Specific Debit
      Then Specific Debit Details window appears
      Then Find the policy "<uniquePolicyNumber>" "<policyId>"
      Then the policy is "Selected"
      Then Policy number filed is populated
      And Policy number is uneditable
      And Collection Method Should be "SSVS"
      And Premium Month date picker translates to MM/YY
      And The Amount is automatically populated
      And Matches the current Nett Premium
      And Nett Premium cannot be negative
      #If it after 14:30  Mobility will not allow to create for today
      Then  Enter Action Date
      And Can add notes
      Then Click Save
      Then client contract payment status updates to 'active' and contract payment status reason to 'Client requested'





  @Done
    Scenario:  Delete Specific Debit
      When Allow Edit Specific Debit before Submission
      And delete a saved  specific debit before it has been submitted
      Then deleting a Specific Debit the 'Deleted' column in the database table gets populated
      And  Deny Edit Specific Debit after Submission
      Then Submitted checkbox ticked after the linked collection item is submitted
      Then  Cannot delete a specific debit after it has been submitted





