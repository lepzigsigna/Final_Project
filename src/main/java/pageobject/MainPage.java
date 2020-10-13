package pageobject;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Logger;

public class MainPage extends BasePage {

    /**
     * LOCATORS
     */
    private By byArticleManagerBtn = By.xpath("//a/span[text()='Articles']");


    /**
     * WEB ELEMENTS
     */
    private WebElement articleManagerBtn() {
        return DriverHelper.getWebDriver().findElement(byArticleManagerBtn);
    }


    /**
     * METHODS
     */
    public void clickArticleManagerBtn() {
        clickWhenElementReady(articleManagerBtn());
        Logger.info("Clicked button Article Manager");
    }


}
