package pageobject.banner.page;

import pageobject.other.page.BasePage;

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
        enterTitleField(categoryName);
        clickSaveAndCloseBtn();
    }
}
