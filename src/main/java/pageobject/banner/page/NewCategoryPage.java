package pageobject.banner.page;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.BasePage;
import utils.Logger;

public class NewCategoryPage extends BasePage {
    /**
     * LOCATORS
     */
    private By byCategoryName = By.cssSelector("#jform_title");
    private By bySaveAndCloseBtn = By.cssSelector("#toolbar-save>button");

    /**
     * WEB ELEMENTS
     */

    private WebElement categoryName() {
        return DriverHelper.getWebDriver().findElement(byCategoryName);
    }

    private WebElement saveAndCloseBtn() {
        return DriverHelper.getWebDriver().findElement(bySaveAndCloseBtn);
    }

    /**
     * METHODS
     */

    public void clickSaveAndCloseBtn() {
        saveAndCloseBtn().click();
        Logger.info("Click the Save and Close Button");
    }

    public void createNewCategory(String categoryName) {
        categoryName().sendKeys(categoryName);
        Logger.info("   Enter Category: " + categoryName);
        clickSaveAndCloseBtn();
    }
}
