Narrative: The contents of cart is shown at checkout page


Scenario: The content of cart with two different items is shown in payment page 

Given you have Weihenstephaner and Karjala in shoppingcart 
When link maksa ostokset is clicked
Then 1 times Weihenstephaner is shown at payment page
And 1 times Karjala is shown at payment page


Scenario: The content of cart with two same items is shown in payment page 

Given you have Weihenstephaner and Weihenstephaner in shoppingcart 
When link maksa ostokset is clicked
Then 2 times Weihenstephaner is shown at payment page