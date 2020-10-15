package pageobject.article.page;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.BasePage;
import utils.Logger;

public class ArticleArticlePage extends BasePage {

    /**
     * LOCATORS
     */
    private By byArticleText = By.cssSelector("#jform_articletext_ifr");


    /**
     * WEB ELEMENTS
     */
    private WebElement articleText() {
        return DriverHelper.getWebDriver().findElement(byArticleText);
    }

    /**
     * METHODS
     */
    public void enterArticleTitle(String title) {
        waitUntilVisible(titleField());
        titleField().clear();
        titleField().sendKeys(title);
        Logger.info("   Entered title: " + title);
    }

    public void selectCategory(String category) {
        clickWhenElementReady(categoryDropdown());
        clickWhenElementReady(dropdownOption(category));
        Logger.info("   Selected: " + category);
    }

    public void enterArticleContent(String content) {
        articleText().sendKeys(content);
    }

    public void createArticle(String articleTitle, String articleCategory, String content) {
        enterArticleTitle(articleTitle);
        selectCategory(articleCategory);
        enterArticleContent(content);
        clickSaveAndCloseBtn();
    }

}
