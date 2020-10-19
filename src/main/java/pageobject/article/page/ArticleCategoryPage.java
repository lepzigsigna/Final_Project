package pageobject.article.page;

import pageobject.other.page.BasePage;

public class ArticleCategoryPage extends BasePage {

    /**
     * LOCATORS
     */

    /**
     * WEB ELEMENTS
     */

    /**
     * METHODS
     */
    private void enterCategoryName(String title) {
        enterTitleField(title);
    }

    public void createCategory(String categoryName) {
        enterCategoryName(categoryName);
        clickSaveAndCloseBtn();
    }

    public void saveCategory(String categoryName) {
        enterCategoryName(categoryName);
        clickSaveBtn();
    }

    public void saveCategoryAsCopy(String categoryName) {
        enterCategoryName(categoryName);
        clickSaveAsCopyBtn();
    }

}
