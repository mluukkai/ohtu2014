package com.mycompany.webkauppa.scenarios;

import junit.framework.Assert;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HallintasivullaVoiMuuttaaTuotteenHintaaJaSaldoaScenarios extends WebScenarioBase {

    public HallintasivullaVoiMuuttaaTuotteenHintaaJaSaldoaScenarios() {
        super(HallintasivullaVoiMuuttaaTuotteenHintaaJaSaldoaScenarios.class);
    }

    @Given("you are at page $page")
    public void givenYouAreAtPage(String page) {
        browseToPage(page);
    }

    @When("you change price of $item to $price")
    public void whenYouChangePriceTo(String item, int price) {
        WebElement element = driver.findElement(By.id(item));

        WebElement inputti = element.findElement(By.name("hinta"));

        inputti.clear();
        inputti.sendKeys("" + price);

        WebElement submitElement = element.findElement(By.name("toimenpide"));
        submitElement.submit();
    }

    @Then("the price of $item is shown to be $price")
    public void thenThePriceOfIsShownToBe(String item, int price)  {
        WebElement element = driver.findElement(By.id(item));
        WebElement subElement = element.findElement(By.id("hinta"));
        Assert.assertEquals(""+price, subElement.getText());        
    }
}
