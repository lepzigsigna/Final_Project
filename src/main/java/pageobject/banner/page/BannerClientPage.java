package pageobject.banner.page;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.other.page.BasePage;
import utils.Logger;

public class BannerClientPage extends BasePage {
    /**
     * LOCATORS
     */
    private By byContactName = By.cssSelector("#jform_contact");
    private By byContactEmail = By.cssSelector("#jform_email");
    private By byInvalidFieldMsg = By.cssSelector("div[class='alert alert-error alert-danger']>div");

    /**
     * WEB ELEMENTS
     */
    private WebElement contactName() {
        return DriverHelper.getWebDriver().findElement(byContactName);
    }

    private WebElement contactEmail() {
        return DriverHelper.getWebDriver().findElement(byContactEmail);
    }

    private WebElement invalidFieldMsg() {
        return DriverHelper.getWebDriver().findElement(byInvalidFieldMsg);
    }

    /**
     * METHODS
     */
    public void createNewClient(String clientName, String contactName, String contactEmail) {
        enterNameField(clientName);
        Logger.info("   Enter client name: " + clientName);
        contactName().sendKeys(contactName);
        Logger.info("   Enter contact name: " + contactName);
        contactEmail().sendKeys(contactEmail);
        Logger.info("   Enter contact email: " + contactEmail);
        clickSaveAndCloseBtn();
    }

    public boolean validStatusOfContactEmailField() {
        boolean result = false;
        if (getAttributeValueOf(contactEmail(), "aria-invalid").equalsIgnoreCase("false")) {
            result = true;
        }
        return result;
    }

    public String getInvalidFieldMsg() {
        return getTextOf(invalidFieldMsg());
    }

    public String getcolorOfEmailField() {
        return getColorCodeOf(contactEmail());
    }
}
