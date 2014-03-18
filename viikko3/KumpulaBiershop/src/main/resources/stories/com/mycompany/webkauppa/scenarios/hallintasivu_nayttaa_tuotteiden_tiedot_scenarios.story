Narrative: user can go to admin page and see there price and saldo of products


Scenario: Admin link leads to admin page

Given browser opened 
When page Hallinta is browsed
Then title should be Hallinta


Scenario: Admin page shows correctly prices and saldos

Given you are at page Hallinta
When you have items in stock
Then the price and saldo of items is correctly shown