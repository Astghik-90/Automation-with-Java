package base;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    private WebDriver driver;

    public void setDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\WorkSpace\\Automation-with-Java\\.idea\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/");
//        driver.manage().window().maximize();
//        driver.manage().window().fullscreen();
        driver.manage().window().setSize(new Dimension(360, 740));
        System.out.println(driver.getTitle());
//        driver.quit();
    }

    public static void main(String[] args) {
        BaseTest test = new BaseTest();
        test.setDriver();
    }
}
