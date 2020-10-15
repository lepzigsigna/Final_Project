package pageobject.banner.page;

import pageobject.BasePage;
import utils.Logger;

public class BannerCategoryPage extends BasePage {
    /**
     * LOCATORS
     */

    /**
     * WEB ELEMENTS
     */


    /**
     * METHODS
     */
    public void createNewCategory(String categoryName) {
        titleField().sendKeys(categoryName);
        Logger.info("   Enter Category: " + categoryName);
        clickWhenElementReady(saveAndCloseBtn());
    }
}
