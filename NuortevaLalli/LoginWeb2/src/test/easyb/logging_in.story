import ohtu.*
import ohtu.authentication.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can log in with valid username/password-combination'

scenario "user can login with correct password", {
    given 'login selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("login"));       
        element.click();       
    }

    when 'a valid username and password are given', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        element.submit();
    }
 
    then 'user will be logged in to system', {
        driver.getPageSource().contains("Welcome to Ohtu Application!").shouldBe true
    }
}

scenario "user can not login with incorrect password", {
    given 'login selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("login"));       
        element.click();       
    }

    when 'a valid username and password are given', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akke");
        element = driver.findElement(By.name("login"));
        element.submit();
    }
 
    then 'user will be logged in to system', {
        driver.getPageSource().contains("Welcome to Ohtu Application!").shouldBe false
    }
    
}

scenario "nonexistent user can not login to system", {
    given 'login selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("login"));       
        element.click();       
    }

    when 'a valid username and password are given', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("peka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akke");
        element = driver.findElement(By.name("login"));
        element.submit();
    }
 
    then 'user will be logged in to system', {
        driver.getPageSource().contains("Welcome to Ohtu Application!").shouldBe false
    }
}