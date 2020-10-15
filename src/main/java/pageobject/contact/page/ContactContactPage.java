package pageobject.contact.page;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.BasePage;
import utils.Logger;

public class ContactContactPage extends BasePage {

    /**
     * LOCATORS
     */
    private By byStatusDropdown = By.xpath("//div[@class='controls']/select[@id='jform_published']/..");


    /**
     * WEB ELEMENTS
     */

    private WebElement statusDropdown() { return DriverHelper.getWebDriver().findElement(byStatusDropdown); }


    /**
     * METHODS
     */

    public void enterContactName(String contactName) {
        waitUntilVisible(nameField());
        nameField().clear();
        nameField().sendKeys(contactName);
        Logger.info("   Entered contact name: " + contactName);
    }

    public void selectCategory(String category) {
        clickWhenElementReady(categoryDropdown());
        clickWhenElementReady(dropdownOption(category));
        Logger.info("   Selected: " + category);
    }

    public void selectStatus (String status) {
        clickWhenElementReady(statusDropdown());
        clickWhenElementReady(dropdownOption(status));
        Logger.info("   Selected: " + status);
    }

    public void createContact(String contactName, String category, String status) {
        enterContactName(contactName);
        selectCategory(category);
        selectStatus(status);
        clickSaveAndCloseBtn();
    }

}
