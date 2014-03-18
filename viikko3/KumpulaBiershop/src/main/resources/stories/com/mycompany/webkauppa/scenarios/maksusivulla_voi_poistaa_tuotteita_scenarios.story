Narrative: Items can be removed from cart at checkout page

Scenario:  Count of one item can be reduced 

Given you have Weihenstephaner and Weihenstephaner in shoppingcart 
When link maksa ostokset is clicked
And Weihenstephaner is removed from shoppingcart
Then 1 times Weihenstephaner is shown at payment page


Scenario:  If count of item drops to zero, item is removed from list 

Given you have Weihenstephaner and Karjala in shoppingcart 
When link maksa ostokset is clicked
And Weihenstephaner is removed from shoppingcart
Then Weihenstephaner is not shown at payment page


Scenario:  ostoskorin tyhjennys