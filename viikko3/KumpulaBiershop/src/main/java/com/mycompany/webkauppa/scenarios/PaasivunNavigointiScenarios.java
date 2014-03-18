package com.mycompany.webkauppa.scenarios;

import javax.validation.constraints.AssertTrue;
import junit.framework.Assert;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class PaasivunNavigointiScenarios extends WebScenarioBase {

    public PaasivunNavigointiScenarios() {
        super(PaasivunNavigointiScenarios.class);
    }

    @Given("browser opened")
    public void givenBrowserOpened() {
        openBrowser();
        
    }

    @When("browsed to mainpage")
    public void whenBrowsedToMainpage() {
        browseAddress(URL);
    }
}