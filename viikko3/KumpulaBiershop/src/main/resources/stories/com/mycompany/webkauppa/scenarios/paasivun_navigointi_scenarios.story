Narrative:  By following the links from main page you can go 
            either to contact infromation or start shopping

Scenario: Frontpage shown

Given browser opened 
When browsed to mainpage
Then title should be Webkauppa


Scenario: Yhteystiedot available

Given browser opened 
When browsed to mainpage
And link yhteystiedot is clicked
Then title should be Yhteystiedot


Scenario: You can start shopping

Given browser opened 
When browsed to mainpage
And link tuotelista is clicked
Then title should be Tuotelista