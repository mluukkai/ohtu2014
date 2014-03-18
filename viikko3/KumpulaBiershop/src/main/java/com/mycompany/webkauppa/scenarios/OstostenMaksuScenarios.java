package com.mycompany.webkauppa.scenarios;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import junit.framework.Assert;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class OstostenMaksuScenarios extends WebScenarioBase {

    public OstostenMaksuScenarios() {
        super(OstostenMaksuScenarios.class);
    }

    @Given("you are at payment page and have $item1 and $item2 in shoppingcart")
    public void givenYouAreAtPaymentPageAndHaveInShoppingcart(String item1, String item2) {
        areInTuotelista();
        addItemToCart(item1);
        addItemToCart(item2);
        clickLinkWithText("maksa ostokset");
    }
    
    String NAME;
    String ADDRESS;
    String CARD;

    @When("name $name address $address card number $number are entered")
    public void whenNameAddressCardNumberAre(String name, String address, String card) {
        NAME = name;
        ADDRESS = address;
        CARD = card;

        WebElement element = driver.findElement(By.id("maksutiedot"));

        WebElement inputElement = element.findElement(By.name("nimi"));
        inputElement.sendKeys(NAME);

        inputElement = element.findElement(By.name("osoite"));
        inputElement.sendKeys(ADDRESS);


        inputElement = element.findElement(By.name("luottokorttinumero"));
        inputElement.sendKeys(CARD);
    }
    int SUM;

    @When("press submit")
    public void whenPressSubmit() {
        WebElement element = driver.findElement(By.id("maksutiedot"));
        WebElement inputElement = element.findElement(By.name("suorita maksu"));

        element = driver.findElement(By.id("korissa"));
        SUM = extractPrice(element.getText());

        inputElement.submit();
    }

    @Then("a confirmation about mailing and payment is shown")
    public void thenAConfirmationAboutMailingAndPaymentIsShown() {
        String content = driver.getPageSource();

        Assert.assertTrue(content.contains("kortiltasi on veloitettu " + SUM + " euroa"));
        Assert.assertTrue(content.contains(ADDRESS));
    }

    // helper
    public static int extractPrice(String inputStr) throws NumberFormatException {
        String patternStr = "(?i)[^\\d]*(\\d+).*";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(inputStr);
        matcher.matches();
        return Integer.parseInt(matcher.group(1));
    }
}
