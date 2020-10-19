package pageobject.contact.page;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.other.page.BasePage;
import utils.Logger;

public class ContactContactPage extends BasePage {

    /**
     * LOCATORS
     */
    private By byStatusDropdown = By.cssSelector("div#jform_published_chzn");
    private String xpathStatusOption = "//ul[@class='chzn-results']/li[text()='%s']";

    /**
     * WEB ELEMENTS
     */
    private WebElement statusDropdown() {
        return DriverHelper.getWebDriver().findElement(byStatusDropdown);
    }

    private WebElement dropdownOption(String option) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathStatusOption, option)));
    }

    /**
     * METHODS
     */
    private void enterContactName(String contactName) {
        enterNameField(contactName);
    }

    private void selectContactCategory(String category) {
        clickWhenElementReady(statusDropdown());
        clickWhenElementReady(dropdownOption(category));
        Logger.info("   Selected: " + category);
    }

    public void createContact(String contactName, String category, String status) {
        enterContactName(contactName);
        selectCategory(category);
        selectContactCategory(status);
        clickSaveAndCloseBtn();
    }

}
