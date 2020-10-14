package pageobject;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Logger;

public class NewContactPage extends BasePage {

    /**
     * LOCATORS
     */
    private By byTitleField = By.cssSelector("#jform_name");
    private By byCategoryDropdown = By.xpath("//div[@class='controls']/select[@id='jform_catid']/..");
    private By byStatusDropdown = By.xpath("//div[@class='controls']/select[@id='jform_published']/..");
    private String xpathDropdownOption = "//ul[@class='chzn-results']/li[contains(.,'%s')]";
    private By bySaveAndCloseBtn = By.cssSelector("#toolbar-save>button");


    /**
     * WEB ELEMENTS
     */

    private WebElement titleField() {
        return DriverHelper.getWebDriver().findElement(byTitleField);
    }

    private WebElement saveAndCloseBtn() {
        return DriverHelper.getWebDriver().findElement(bySaveAndCloseBtn);
    }

    private WebElement categoryDropdown() {
        return DriverHelper.getWebDriver().findElement(byCategoryDropdown);
    }

    private WebElement statusDropdown() { return DriverHelper.getWebDriver().findElement(byStatusDropdown); }

    private WebElement dropdownOption(String option) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathDropdownOption, option)));
    }

    /**
     * METHODS
     */

    public void enterContactName(String contactName) {
        waitUntilVisible(titleField());
        titleField().clear();
        titleField().sendKeys(contactName);
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

    public void clickSaveAndCloseBtn() {
        saveAndCloseBtn().click();
        Logger.info("Click the Save and Close Button");
    }

    public void createContact(String contactName, String category, String status) {
        enterContactName(contactName);
        selectCategory(category);
        selectStatus(status);
        clickSaveAndCloseBtn();
    }

}
