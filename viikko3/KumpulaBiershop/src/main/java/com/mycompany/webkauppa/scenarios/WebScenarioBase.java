package com.mycompany.webkauppa.scenarios;

import com.mycompany.webkauppa.sovelluslogiikka.Tuote;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.jbehave.core.annotations.*;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebScenarioBase extends ScenariosBase {

    public WebScenarioBase(Class stepDefinitionClass) {
        super(stepDefinitionClass);
    }
    
    static String BASE_URL = "http://localhost:8080";
    static String URL = BASE_URL + "/";    
    
    WebDriver driver;

    public void openBrowser() {
        driver = new HtmlUnitDriver();;
    }

    public void areInTuotelista() {
        openBrowser();
        browseAddress(URL);
        clickLinkWithText("tuotelista");
    }   
    
    public void browseToPage(String url) {
        driver = new HtmlUnitDriver();
        driver.get(URL + url);
    }
    
    public void browseToPageDebug(String url) {
        driver = new FirefoxDriver();
        driver.get(URL + url);
    }    

    public void clickLinkWithText(String link) {
        WebElement linkElement = driver.findElement(By.linkText(link));
        linkElement.click();
    }

    public void addItemToCart(String item) {
        WebElement element = driver.findElement(By.id(item));
        WebElement linkElement = element.findElement(By.linkText("lisaa koriin"));
        linkElement.click();
    }

    // steps    
    @Then("the items $item1 and $item2 are shown at payment page")
    public void thenTheItemsAreShownAtPaymentPage(String item1, String item2) {
        assertItemInCart(1, item1);
        assertItemInCart(1, item2);
    }

    @Then("$count times $item is shown at payment page")
    public void thenShownAtPaymentPage(int count, String item) {
        assertItemInCart(count, item);
    }

    @Given("you have $item1 and $item2 in shoppingcart")
    public void givenYouHaveAndInShoppingcart(String item1, String item2) {
        areInTuotelista();
        addItemToCart(item1);
        addItemToCart(item2);
    }

    @When("url $url is browsed")
    public void browseAddress(String url) {
        driver.get(URL);
    }

    @Then("title should be $expected")
    public void thenTheTitleShouldBe(String expected) {
        Assert.assertEquals(expected, driver.getTitle());
    }

    @When("link $link is clicked")
    public void whenLinkIsClicked(String link) {
        clickLinkWithText(link);
    }

    // asserts
    public void assertItemInCart(int count, String item) {
        WebElement element = driver.findElement(By.className("ostoskori"));

        if (count == 0) {
            Assert.assertFalse(element.getText().contains(item));
        } else {
            Assert.assertTrue(element.getText().contains(count + " x " + item));
        }
    }

    public void assertItemCorrectlyShown(Tuote tuote) {
        WebElement element = driver.findElement(By.id(""+tuote.getId()));
        WebElement nameElement = element.findElement(By.id("nimi"));
        
        Assert.assertTrue(nameElement.getText().contains( tuote.getNimi() ) );       
        WebElement priceElement = element.findElement(By.id("hinta"));
        Assert.assertTrue(priceElement.getText().contains( ""+tuote.getHinta() ) );
        WebElement saldoElement = element.findElement(By.id("saldo"));
        Assert.assertTrue(saldoElement.getText().contains( ""+tuote.getSaldo() ) );
    }
}
