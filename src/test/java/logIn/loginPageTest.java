package logIn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class loginPageTest {
    public static WebDriver driver;

    @BeforeClass
    public static void initWebDriver() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void positiveTest(){
        driver.get("http://the-internet.herokuapp.com/login");
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("tomsmith");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("SuperSecretPassword!");
        WebElement loginButton = driver.findElement(By.className("fa-sign-in"));
        loginButton.click();

        Assert.assertTrue(driver.findElement(By.id("flash")).getText().contains("You logged into a secure area!"));

    }


    @Test
    public void negativeTest(){
        driver.get("http://the-internet.herokuapp.com/login");
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("asd");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("111!");
        WebElement loginButton = driver.findElement(By.className("fa-sign-in"));
        loginButton.click();

        Assert.assertTrue(driver.findElement(By.id("flash")).getText().contains("Your username is invalid!"));
    }



}
