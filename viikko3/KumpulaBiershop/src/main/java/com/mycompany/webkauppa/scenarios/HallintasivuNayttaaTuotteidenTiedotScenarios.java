package com.mycompany.webkauppa.scenarios;

import com.mycompany.webkauppa.sovelluslogiikka.Tuote;
import com.mycompany.webkauppa.sovelluslogiikka.Varasto;
import java.util.ArrayList;
import junit.framework.Assert;
import org.jbehave.core.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HallintasivuNayttaaTuotteidenTiedotScenarios extends WebScenarioBase {

    public HallintasivuNayttaaTuotteidenTiedotScenarios() {
        super(HallintasivuNayttaaTuotteidenTiedotScenarios.class);
    }

    @Given("browser opened")
    public void givenBrowserOpened() {
        openBrowser();
    }

    @When("page $page is browsed")
    public void whenPageIsBrowsed(String page) {
        browseToPage(page);

    }

    @Given("you are at page $page")
    public void givenYouAreAtPage(String page) {
        browseToPage(page);
    }

    
    ArrayList<Tuote> tuotteet;
    
    @When("you have items in stock")
    public void whenYouHaveItemsInStock() {
        tuotteet = new ArrayList<Tuote>();
        
        tuotteet.add( Varasto.getInstance().etsiTuote(1) ); 
        tuotteet.add( Varasto.getInstance().etsiTuote(2) );
        tuotteet.add( Varasto.getInstance().etsiTuote(3) );
    }

    @Then("the price and saldo of items is correctly shown")
    public void thenThePriceAndSaldoOfItemsIsCorrectlyShown() {
        for (Tuote tuote : tuotteet) {
            assertItemCorrectlyShown( tuote );
        }                
    }
    
}
