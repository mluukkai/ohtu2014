import ohtu.*
import ohtu.data_access.*
import ohtu.domain.*
import ohtu.authentication.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description """A new user account can be created 
              if a proper unused username 
              and a proper password are given"""

scenario "creation successful with correct username and password", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click(); 
    }
 
    when 'a valid username and password are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("testi");
        element = driver.findElement(By.name("password"));
        element.sendKeys("test1234");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("test1234");
        element = driver.findElement(By.name("add"));
        element.submit();
    }

    then 'new user is registered to system', {
      driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe true
    }
}

scenario "can login with successfully generated account", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click();
    }
 
    when 'a valid username and password are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("testi2");
        element = driver.findElement(By.name("password"));
        element.sendKeys("test1234");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("test1234");
        element = driver.findElement(By.name("add"));
        element.submit();

        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();

        element = driver.findElement(By.linkText("logout"));       
        element.click(); 

        element = driver.findElement(By.linkText("login"));       
        element.click(); 
        element = driver.findElement(By.name("username"));
        element.sendKeys("testi2");
        element = driver.findElement(By.name("password"));
        element.sendKeys("test1234");
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    then  'new credentials allow logging in to system', {
       driver.getPageSource().contains("Welcome to Ohtu Application!").shouldBe true
    }
}

scenario "creation fails with correct username and too short password", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click();
}
    when 'a valid username and too short password are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("testi3");
        element = driver.findElement(By.name("password"));
        element.sendKeys("test12");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("test12");
        element = driver.findElement(By.name("add"));
        element.submit();
}
    then 'new user is not be registered to system', {
       driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe false
}
}

scenario "creation fails with correct username and password consisting of letters", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click();
}
    when 'a valid username and password consisting of letters are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("testi4");
        element = driver.findElement(By.name("password"));
        element.sendKeys("salasana");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("salasana");
        element = driver.findElement(By.name("add"));
        element.submit();
}
    then 'new user is not be registered to system', {
       driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe false
}
}

scenario "creation fails with too short username and valid password", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click();
}
    when 'a too sort username and valid password are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("test");
        element = driver.findElement(By.name("password"));
        element.sendKeys("sala5ana");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("sala5ana");
        element = driver.findElement(By.name("add"));
        element.submit();
}
    then 'new user is not be registered to system', {
       driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe false
}
}

scenario "creation fails with already taken username and valid password", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click();
}
    when 'a already taken username and valid password are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("sala5ana");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("sala5ana");
        element = driver.findElement(By.name("add"));
        element.submit();
}
    then 'new user is not be registered to system', {
       driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe false
}
}

scenario "can not login with account that is not successfully created", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click();
}
    when 'a invalid username/password are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("testi123");
        element = driver.findElement(By.name("password"));
        element.sendKeys("test12");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("test12");
        element = driver.findElement(By.name("add"));
        element.submit();

        element = driver.findElement(By.linkText("back to home"));
        element.click();

        element = driver.findElement(By.linkText("login"));       
        element.click(); 
        element = driver.findElement(By.name("username"));
        element.sendKeys("testi123");
        element = driver.findElement(By.name("password"));
        element.sendKeys("test12");
        element = driver.findElement(By.name("login"));
        element.submit();
}
    then  'new credentials do not allow logging in to system', {
        driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe false
}
}