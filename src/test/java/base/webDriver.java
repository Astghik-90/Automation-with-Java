package base;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.WritableByteChannel;
import java.time.Duration;
import java.util.List;

import static java.lang.Double.parseDouble;

public class webDriver {
    public static WebDriver driver;

    @BeforeClass
    public static void initWebDriver() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/ayvaz/Downloads/chromedriver.exe");
        driver = new ChromeDriver();
//        DesiredCapabilities capability = new DesiredCapabilities();
//        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
//        capability.setBrowserName("chrome");
//        capability.setPlatform(Platform.WINDOWS);
    }

    @Test
    public void testSearchResult() {
        driver.get("http://automationpractice.com/index.php");
        WebElement searchField = driver.findElement(By.id("search_query_top"));
        searchField.sendKeys("dress");
        WebElement searchButton = driver.findElement(By.className("button-search"));
        searchButton.click();
        WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("heading-counter")));
        WebElement searchResultCounter = driver.findElement(By.className("heading-counter"));
        String counterText = searchResultCounter.getText();
        String[] arr = counterText.split(" ");
        Assert.assertTrue(arr[0].equals("7"),"Wrong amount of search counter.");

    }

    @Test
    public void searchProductVerification() {
        driver.get("http://automationpractice.com/index.php?controller=search&orderby=position&orderway=desc&search_query=dress&submit_search=");
        List<WebElement> productList = driver.findElements(By.xpath("//li[contains(@class, \"ajax_block_product\")]/div/div[2]/h5"));

        boolean allSearchResultsContainsDress = true;

        for(WebElement wb: productList){
            String productName = wb.getText();
            System.out.println(productName);
            if(!productName.toLowerCase().contains("dress")){
                allSearchResultsContainsDress = false;
                break;
            }
        }

        Assert.assertTrue(allSearchResultsContainsDress);

    }

    @Test
    @Step("Step 1")
    public void sortingTest(){
        driver.get("http://automationpractice.com/index.php?controller=search&orderby=position&orderway=desc&search_query=dress&submit_search=");
        WebElement ddMenu = driver.findElement(By.id("selectProductSort"));
        Select selectObj = new Select(ddMenu);
        selectObj.selectByValue("price:desc");
//        WebElement reAsc = driver.findElement(By.cssSelector("option[value='reference:asc']"));
//        reAsc.click();
        WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("heading-counter")));
        List<WebElement> productPriceList = driver.findElements(By.xpath("//li[contains(@class, \"ajax_block_product\")]/div/div[2]//span[@class=\"price product-price\"]"));
        boolean isPriceDesc = true;

        for(int i=0; i<productPriceList.size()-1; i++){
            double price_1 = parseDouble(productPriceList.get(i).getText().substring(1));
            double price_2 = parseDouble(productPriceList.get(i+1).getText().substring(1));
            System.out.println(price_1 + " " + price_2);
            if(price_1 < price_2){
                isPriceDesc = false;
                break;
            }

        }
        Assert.assertTrue(isPriceDesc);

    }

    //Alert test
    @Test
    public void alertTest() throws InterruptedException {
        driver.get("https://demo.guru99.com/test/delete_customer.php");
        WebElement inputField = driver.findElement(By.xpath("//input[@name='cusid']"));
        inputField.sendKeys("test");
        WebElement submitButton = driver.findElement(By.xpath("//input[@name='submit']"));
        submitButton.click();
        Thread.sleep(3000);
        driver.switchTo().alert();
        System.out.println(driver.switchTo().alert().getText());
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        System.out.println(driver.switchTo().alert().getText());

    }

    @AfterMethod
    public static void report(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            Allure.addAttachment("",
                    new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
    }
        @AfterClass
        public static void closeBrowser () {
            driver.quit();
        }

    }




//    @BeforeClass
//    public static void initWebDriver() {
//        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
//        driver = new ChromeDriver();
//
//    }
//
//    @Test
//    public void logInPageNavigation() {
//        driver.get("https://www.hackerrank.com/");
//        WebElement logInButton = driver.findElement(By.cssSelector("#menu-item-2887>a"));
//        logInButton.click();
//        WebElement logInButtonForDevelopers = driver.findElement(By.cssSelector("a[href=\"https://www.hackerrank.com/login\"]"));
//        logInButtonForDevelopers.click();
//        WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.presenceOfElementLocated(By.className("auth-category-title")));
//        WebElement categoryTitle = driver.findElement(By.className("auth-category-title"));
//        String title = categoryTitle.getText();
//        System.out.println(title);
//        Assert.assertEquals(title, "For Developers");
//    }
//
//    @Test
//    public void logInTest() {
//        driver.get("https://www.hackerrank.com/auth/login");
//        WebElement emailField = driver.findElement(By.id("input-1"));
//        emailField.sendKeys("astgh1990@gmail.com");
//        WebElement passwordField = driver.findElement(By.id("input-2"));
//        passwordField.sendKeys("Astghik.1990");
//        WebElement logInButton = driver.findElement(By.xpath("//*[@id=\"tab-1-content-1\"]/div[1]/form/div[4]/button/div/span"));
//        logInButton.click();
//        WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.presenceOfElementLocated(By.className("username")));
//        String userName = driver.findElement(By.className("username")).getText();
//
//        Assert.assertEquals(userName, "astgh1990");
//
//    }
//
//    @AfterClass
//    public static void closeBrowser() {
//        WebElement username = driver.findElement(By.className("username"));
//        username.click();
//        WebElement logOut = driver.findElement(By.className("logout-button"));
//        logOut.click();
//        WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#menu-item-2887>a")));
//        driver.quit();
//    }


