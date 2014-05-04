package com.mycompany.webkauppa.scenarios;

import com.mycompany.webkauppa.sovelluslogiikka.Tuote;
import com.mycompany.webkauppa.sovelluslogiikka.Varasto;
import java.util.regex.*;
import org.jbehave.core.annotations.*;
import org.openqa.selenium.*;

import junit.framework.Assert;

public class TuotteenLisaysOstoskoriinScenarios extends WebScenarioBase {
    
    public TuotteenLisaysOstoskoriinScenarios() {
        super(TuotteenLisaysOstoskoriinScenarios.class);
    }
    
    @Given("you are in tuotelista")
    public void givenYouAreInTuotelista() {
        openBrowser();
        browseAddress(URL);
        clickLinkWithText("tuotelista");
    }
    
    @When("item $item is added to shoppingcart")
    public void whenItemWeihenstephanerIsAddedToShoppingcart(String item) {
        addItemToCart(item);
    }
    
    @Then("the number of items in cart should be $num")
    public void thenTheNumberOfItemsInCartShouldBe(int num) {
        AssertShoppingcartItemCount(num);
    }
    
    @Then("the total price of cart should correspond the price of item $item")
    public void thenTheTotalPriceOfCartShouldCorrespondThePriceOfItem(String item) {
        int hinta = etsiTuotteenHinta(item);
        AssertShoppingcartPrice(hinta);
    }
    
    @Then("the total price of cart should correspond the price of items $item1 and $item2")
    public void thenTheTotalPriceOfCartShouldCorrespondThePriceOfItemsAnd(String item1, String item2) {
        int hinta1 = etsiTuotteenHinta(item1);
        int hinta2 = etsiTuotteenHinta(item2);
        AssertShoppingcartPrice(hinta1 + hinta2);
    }

    // helpers
    public void AssertShoppingcartItemCount(int num) {
        String expected = "Ostoskorissa tuotteita " + num;
        String atPage = driver.findElement(By.id("korissa")).getText();
        
        Assert.assertTrue(atPage.contains(expected));
    }
    
    public void AssertShoppingcartPrice(int hinta) {
        String expected = "ja niiden yhteenlaskettu hinta on " + hinta + " euroa.";
        String atPage = driver.findElement(By.id("korissa")).getText();
        
        Assert.assertTrue(atPage.contains(expected));
    }
    
    public static int extractPrice(String inputStr) throws NumberFormatException {
        String patternStr = "(?i)[^\\d]*(\\d+).*";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(inputStr);
        matcher.matches();
        return Integer.parseInt(matcher.group(1));
    }
    
    private int etsiTuotteenHinta(String item) {
        Varasto varasto = Varasto.getInstance();
        
        for (Tuote tuote : varasto.tuotteidenLista()) {
            if (tuote.getNimi().equals(item)) {
                return tuote.getHinta();
            }
        }
        
        return -1;
    }
}
