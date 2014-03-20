Narrative: When listed items are added to shoppingcart, the number of items 
           and total price of cart are shown

Scenario: Price and itemcount is shown when an item is added to shopping cart

Given you are in tuotelista 
When item Weihenstephaner is added to shoppingcart
Then the number of items in cart should be 1 
And the total price of cart should correspond the price of item Weihenstephaner


Scenario: Price and itemcount is shown when two different items are added to shopping cart

Given you are in tuotelista 
When item Weihenstephaner is added to shoppingcart
And item Karjala is added to shoppingcart
Then the number of items in cart should be 2 
And the total price of cart should correspond the price of items Weihenstephaner and Karjala


Scenario: Price and itemcount is shown when two same items are added to shopping cart

Given you are in tuotelista 
When item Weihenstephaner is added to shoppingcart
And item Karjala is added to shoppingcart
Then the number of items in cart should be 2 
And the total price of cart should correspond the price of items Weihenstephaner and Karjala
