package pageobject.article.page;

import pageobject.other.page.BasePage;

public class ArticleCategoryPage extends BasePage {

    /**
     * LOCATORS
     */
//    private By byStatusDropdown = By.xpath("//div[@class='controls']/select[@id='jform_published']/..");

    /**
     * WEB ELEMENTS
     */
//    private WebElement statusDropdown() {
//        return DriverHelper.getWebDriver().findElement(byStatusDropdown);
//    }

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
