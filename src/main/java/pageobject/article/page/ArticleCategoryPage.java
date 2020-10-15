package pageobject.article.page;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.BasePage;
import utils.Logger;

public class ArticleCategoryPage extends BasePage {

    /**
     * LOCATORS
     */
    private By byStatusDropdown = By.xpath("//div[@class='controls']/select[@id='jform_published']/..");

    /**
     * WEB ELEMENTS
     */
    private WebElement statusDropdown() {
        return DriverHelper.getWebDriver().findElement(byStatusDropdown);
    }

    /**
     * METHODS
     */

    public void enterCategoryName(String contactName) {
        waitUntilVisible(titleField());
        titleField().clear();
        titleField().sendKeys(contactName);
        Logger.info("   Entered contact name: " + contactName);
    }

    public void selectStatus(String status) {
        clickWhenElementReady(statusDropdown());
        clickWhenElementReady(dropdownOption(status));
        Logger.info("   Selected: " + status);
    }

    public void createCategory(String categoryName) {
        enterCategoryName(categoryName);
        clickSaveAndCloseBtn();
    }

    public void saveCategory(String categoryName) {
        enterCategoryName(categoryName);
        clickWhenElementReady(saveBtn());
    }

    public void saveCategoryAsCopy(String categoryName) {
        enterCategoryName(categoryName);
        clickWhenElementReady(saveAsCopyBtn());
        clickWhenElementReady(closeBtn());
    }

}
