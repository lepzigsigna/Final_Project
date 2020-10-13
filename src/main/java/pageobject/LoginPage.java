package pageobject;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Logger;

public class LoginPage extends BasePage {

    /**
     * LOCATORS
     */
    private By byUsernameField = By.cssSelector("input[name='username']");
    private By byPasswordField = By.cssSelector("input[name='passwd']");
    private By byLoginBtn = By.cssSelector("div.btn-group>button");

    /**
     * WEB ELEMENTS
     */
    private WebElement usernameField() {
        return DriverHelper.getWebDriver().findElement(byUsernameField);
    }

    private WebElement passwordField() {
        return DriverHelper.getWebDriver().findElement(byPasswordField);
    }

    private WebElement loginBtn() {
        return DriverHelper.getWebDriver().findElement(byLoginBtn);
    }


    /**
     *  METHODS
     */

    public void login(String username, String password) {
        usernameField().clear();
        usernameField().sendKeys(username);
        Logger.info("   Entered username: " + username);
        passwordField().sendKeys(password);
        Logger.info("   Entered password: " + password);
        loginBtn().click();
        Logger.info("   Clicked Login button");
    }














}
