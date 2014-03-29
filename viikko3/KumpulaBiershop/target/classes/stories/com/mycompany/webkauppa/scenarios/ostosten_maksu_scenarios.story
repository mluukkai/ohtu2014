Narrative: Kun ostoskoriin on valittu tuotteita ja ollaan kassalla
           voidaan täyttää osoite- sekä maksutiedot on täytetty
           ja suorittaa ostos 


Scenario:  Onnistunut ostos

Given you are at payment page and have Weihenstephaner and Karjala in shoppingcart 
When name Matti address Helsinki card number 1234 are entered
And press submit
Then a confirmation about mailing and payment is shown


Scenario:  Epäonnistunut ostos -> paluu
