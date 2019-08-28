Feature: Frontend UI Testing Task
  It searches google resources for similar information to confirm that the article is valid

  Scenario: Login as a authenticated user
    Given user is on homepage
    When user copies news article text and searches for that text with google
    Then search results are displayed and user checks and verifies matching results 
    