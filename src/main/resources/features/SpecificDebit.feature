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
    @SuccessfulScenario
    Scenario: Add Specific Debit
      Given I am on Specific Debit Tab
      When I Add a Specific Debit
      Then Specific Debit Details window appears
      Then Find the policy
      When I select the policy
      Then Policy number filed is populated
      And Policy number is uneditable
      And Collection Method Should be "SSVS"
      And Premium Month date picker translates to MM/YY
      And The Amount is automatically populated
      And Matches the current Nett Premium
      And Nett Premium cannot be negative
      #If it after 14:30  Mobility will not allow to create for today
      Then  Enter Action Date
      Then Click Save
      And If it's after "14:30" Mobility will show an error text

      #Detect Duplicate Specific Debits
    @Done
    Scenario: Add Specific Debit without filling in fields
      When  I Add a Specific Debit
      Then Specific Debit Details window appears
      And Find the policy
      When  I select the policy
      And Click Save
      Then Error message will show below the empty textboxes

      @Done
    Scenario: Action Date
      # Action date cannot be in the past(Past dates on the date picker are greyed out)
      # Action date should not be a non collection day(weekend days and holidays should be greyed out)
      Then Enter Action Date

    @Done
    Scenario: Check Submitted
      When  Submitted checkbox empty(no tick) before the linked collection item is Submitted
      And Cannot tick Submitted checkbox
      And Can add notes
      Then Submitted checkbox ticked after the linked collection item is submitted

  @Done
    Scenario:  Delete Specific Debit
      When delete a saved  specific debit before it has been submitted
      Then deleting a Specific Debit the 'Deleted' column in the database table gets populated

      And  Cannot delete a specific debit after it has been submitted

      #Add specific debit for policy with inactive contract payment Status

      Scenario:  Add Specific Debit for inactive CPS
        When Adding for inactive contract payment status
        Then client contract payment status updates to 'active' and contract payment status reason to 'Client requested'

        @Done
        Scenario:  Edit Specific Debit
          When Allow Edit Specific Debit before Submission
          When Deny Edit Specific Debit after Submission


