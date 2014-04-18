
package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {
    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:8090");
        System.out.println( driver.getPageSource() );
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click(); 
        
        System.out.println("==");
        
        System.out.println( driver.getPageSource() );
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        element.submit();
        
        System.out.println("==");
        System.out.println( driver.getPageSource() );
        
        /* epäonnistunut kirjautuminen: oikea käyttäjätunnus, väärä salasana */
        driver.get("http://localhost:8090");
        login_test(element, driver, "pekka", "abcd1234");
        System.out.println( driver.getPageSource());
        
        /* epäonnistunut kirjautuminen: ei-olemassaoleva käyttäjätunnus */
        driver.get("http://localhost:8090");
        login_test(element, driver, "Natasha", "abcd1234");
        System.out.println(driver.getPageSource());
        
        /* uuden käyttäjätunnuksen luominen */
        driver.get("http://localhost:8090");
        create_user_test(element, driver, "TesterAGII", "abcd1234");
        
        /* uuden käyttäjätunnuksen luomisen jälkeen tapahtuva ulkoskirjautuminen sovelluksesta */
        System.out.println(driver.getPageSource());
        log_out_new_user(element, driver);
        
    }
    
    private static void login_test(WebElement element, WebDriver driver, String user, String pass)
    {
        element = driver.findElement(By.linkText("login"));       
        element.click(); 
        
        System.out.println("==");
        
        System.out.println( driver.getPageSource() );
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        element.submit();
        
        System.out.println("==");
        System.out.println(driver.getPageSource() );
    }
    
    private static void create_user_test(WebElement element, WebDriver driver, String user, String pass)
    {
        element = driver.findElement(By.linkText("register new user"));
        element.click();
        
        element = driver.findElement(By.name("username"));
        element.sendKeys(user);
        
        element = driver.findElement(By.name("password"));
        element.sendKeys(pass);
        
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(pass);
        
        element = driver.findElement(By.name("add"));
        element.submit();
    }
    
    private static void log_out_new_user(WebElement element, WebDriver driver)
    {
        System.out.println(driver.getPageSource());
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        
        element = driver.findElement(By.linkText("logout"));
        element.click();
    }
}
