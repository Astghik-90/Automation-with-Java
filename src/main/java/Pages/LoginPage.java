package Pages;

import locators.LoginPageConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;
    private By usernameField = By.id(LoginPageConstants.username);
    private By passwordField = By.id(LoginPageConstants.password);
    private By loginButton = By.id(LoginPageConstants.loginButton);
    private By errorMessage = By.id(LoginPageConstants.errorMessage);

    public LoginPage(WebDriver driver){
        this.driver=driver;
    }

    public void fillUsername(String username){
        driver.findElement(usernameField).sendKeys(username);

    }

    public void fillPassword(String pass){
        driver.findElement(passwordField).sendKeys(pass);

    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    public WebElement invalidLogin(String username, String password){
        fillUsername(username);
        fillPassword(password);
        clickLoginButton();
        return driver.findElement(errorMessage);
    }


}
