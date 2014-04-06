package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {
    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:8090");
        System.out.println("<<<<<<<<<<<<< Aloitussivu >>>>>>>>>>>>>>");
        System.out.println( driver.getPageSource() );
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click(); 
        
        System.out.println("<<<<<<<<<<<<<<< Loginin-sivu >>>>>>>>>>>>>>");
        
        System.out.println( driver.getPageSource() );
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("login"));
        element.submit();
        
        System.out.println("<<<<<<<<<<<<<<<< Väärällä salasanalla >>>>>>>>>>>>>>>>");
        System.out.println( driver.getPageSource() );
        
        element = driver.findElement(By.name("username"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        element.submit();
        
        System.out.println("<<<<<<<<<<<<<<<< Väärällä loginilla >>>>>>>>>>>>>>>>>");
        System.out.println(driver.getPageSource());
        
        element = driver.findElement(By.linkText("back to home"));
        element.click();
        
        System.out.println("<<<<<<<<<<<<<<<< Back to home >>>>>>>>>>>>>>>");
        System.out.println(driver.getPageSource());
        
        element = driver.findElement(By.linkText("register new user"));
        element.click();
        
        System.out.println("<<<<<<<<<<<<<<<< Register new user >>>>>>>>>>>>>");
        System.out.println(driver.getPageSource());
        
        element = driver.findElement(By.name("username"));
        element.sendKeys("nyyrikki");
        element = driver.findElement(By.name("password"));
        element.sendKeys("nyyrikki1");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("nyyrikki1");
        element = driver.findElement(By.name("add"));
        element.click();
        
        System.out.println("<<<<<<<<<<<<<<< New user >>>>>>>>>>>>>>>>>>");
        System.out.println(driver.getPageSource());
        
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        
        System.out.println("<<<<<<<<<<<<<< Application mainpage >>>>>>>>>>>>");
        System.out.println(driver.getPageSource());
        
        element = driver.findElement(By.linkText("logout"));
        element.click();
        
        System.out.println("<<<<<<<<<<<<<<<< Uloskirjauksen jälkeen >>>>>>>>>>>>>");
        System.out.println(driver.getPageSource());
    }
}
