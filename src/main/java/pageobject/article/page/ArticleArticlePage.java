package pageobject.article.page;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.other.page.BasePage;

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
    private void enterArticleTitle(String title) {
        enterTitleField(title);
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
