package com.mycompany.webkauppa.scenarios;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MaksusivullaVoiPoistaaTuotteitaScenarios extends WebScenarioBase {

    public MaksusivullaVoiPoistaaTuotteitaScenarios() {
        super(MaksusivullaVoiPoistaaTuotteitaScenarios.class);
    }

    @When("$item is removed from shoppingcart")
    public void whenWeihenstephanerIsRemovedFromShoppingcart(String item) {
        WebElement element = driver.findElement(By.id(item));

        WebElement linkElement = element.findElement(By.linkText("poista korista"));

        linkElement.click();
    }

    @Then("$item is not shown at payment page")
    public void thenIsNotShownAtPaymentPage(String item) {
        assertItemInCart(0, item);
    }
}
