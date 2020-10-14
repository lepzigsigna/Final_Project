package pageobject.banner.page;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.BasePage;
import utils.Logger;

public class NewClientPage extends BasePage {
    /**
     * LOCATORS
     */
    private By byClientName = By.cssSelector("#jform_name");
    private By byContactName = By.cssSelector("#jform_contact");
    private By byContactEmail = By.cssSelector("#jform_email");
    private By bySaveAndCloseBtn = By.cssSelector("#toolbar-save>button");


    /**
     * WEB ELEMENTS
     */

    private WebElement clientName() { return DriverHelper.getWebDriver().findElement(byClientName); }

    private WebElement contactName() { return DriverHelper.getWebDriver().findElement(byContactName); }

    private WebElement contactEmail() { return DriverHelper.getWebDriver().findElement(byContactEmail); }

    private WebElement saveAndCloseBtn() { return DriverHelper.getWebDriver().findElement(bySaveAndCloseBtn); }


    /**
     * METHODS
     */

    public void clickSaveAndCloseBtn() {
        saveAndCloseBtn().click();
        Logger.info("Click the Save and Close Button");
    }

    public void createNewClient (String clientName, String contactName, String contactEmail) {
        clientName().sendKeys(clientName);
        Logger.info("   Enter client name:" + clientName);
        contactName().sendKeys(contactName);
        Logger.info("   Enter contact name:" + contactName);
        contactEmail().sendKeys(contactEmail);
        Logger.info("   Enter contact email:" + contactEmail);
        clickSaveAndCloseBtn();
    }

}
