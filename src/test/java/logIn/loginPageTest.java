package logIn;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


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

        Assert.assertTrue(driver.findElement(By.id("flash")).getText().contains("secure"));
    }

    @AfterMethod
    public void recordFailure (ITestResult result){
        if(ITestResult.FAILURE == result.getStatus())
        {
            var camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try{
                Random rn =new Random();
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                System.out.println("lllllll"+LocalDateTime.now());
                Files.move(screenshot, new File("resources/screenshots/" + result.getName() + timeStamp + ".png"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }



}
