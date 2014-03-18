package com.mycompany.webkauppa.scenarios;

import com.mycompany.webkauppa.sovelluslogiikka.Tuote;
import com.mycompany.webkauppa.sovelluslogiikka.Varasto;
import junit.framework.Assert;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HallintasivullaVoiLisataUudenTuotteenScenarios extends WebScenarioBase {

    public HallintasivullaVoiLisataUudenTuotteenScenarios() {
        super(HallintasivullaVoiLisataUudenTuotteenScenarios.class);
    }

    @Given("you are at page $page")
    public void givenYouAreAtPage(String page) {
        browseToPage(page);
    }

    @When("you add $item with price $price and saldo $saldo")
    public void whenYouAddiWithPriceAndSaldo(String item, int price, int saldo) {
        WebElement element = driver.findElement(By.id("uudenLisays"));

        WebElement inputElement = element.findElement(By.name("nimi"));
        inputElement.sendKeys(item);

        inputElement = element.findElement(By.name("hinta"));
        inputElement.sendKeys("" + price);

        inputElement = element.findElement(By.name("saldo"));
        inputElement.sendKeys("" + saldo);

        WebElement submitElement = element.findElement(By.name("toimenpide"));

        submitElement.submit();
    }

    @Then("the $item with price $price and saldo $saldo is in item list")
    public void thenTheWithPriceAndSaldoIsInItemList(String item, int price, int saldo) {
        WebElement element = driver.findElement(By.id(item));
        
        WebElement subElement = element.findElement(By.id("hinta"));
        Assert.assertEquals(""+price, subElement.getText());
        
        subElement = element.findElement(By.id("saldo"));
        Assert.assertEquals(""+saldo, subElement.getText());       
    }

}
