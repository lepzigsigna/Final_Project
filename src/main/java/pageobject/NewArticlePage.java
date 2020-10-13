package pageobject;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Logger;

public class NewArticlePage extends BasePage {

    /**
     * LOCATORS
     */
    private By byTitleField = By.cssSelector("#jform_title");
    private By byCategoryDropdown = By.xpath("//div[@class='controls']/select[@id='jform_catid']/..");
    private String xpathCategoryOption = "//ul[@class='chzn-results']/li[contains(.,'%s')]";
    private By byArticleText = By.cssSelector("#jform_articletext_ifr");
    private By bySaveAndCloseBtn = By.cssSelector("#toolbar-save>button");


    /**
     * WEB ELEMENTS
     */

    private WebElement titleField() {
        return DriverHelper.getWebDriver().findElement(byTitleField);
    }

    private WebElement articleText() {
        return DriverHelper.getWebDriver().findElement(byArticleText);
    }

    private WebElement saveAndCloseBtn() {
        return DriverHelper.getWebDriver().findElement(bySaveAndCloseBtn);
    }

    private WebElement categoryDropdown() {
        return DriverHelper.getWebDriver().findElement(byCategoryDropdown);
    }

    private WebElement categoryOption(String option) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathCategoryOption, option)));
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
        clickWhenElementReady(categoryOption(category));
        Logger.info("   Selected: " + category);
    }

    public void enterArticleContent(String content) {
        articleText().sendKeys(content);
    }

    public void clickSaveAndCloseBtn() {
        saveAndCloseBtn().click();
        Logger.info("Click the Save and Close Button");
    }

    public void createArticle(String articleTitle, String articleCategory, String content) {
        enterArticleTitle(articleTitle);
        selectCategory(articleCategory);
        enterArticleContent(content);
        clickSaveAndCloseBtn();
    }


}
