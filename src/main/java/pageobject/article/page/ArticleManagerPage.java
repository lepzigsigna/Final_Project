package pageobject.article.page;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pageobject.BasePage;
import utils.Logger;

import java.util.List;

public class ArticleManagerPage extends BasePage {

    /**
     * LOCATORS
     */
    //Fix this to dynamic xpath contains a article name//
    private By byRecentArticleCheckbox = By.xpath("//tbody/tr/td[@class='small hidden-phone']/a[contains(text(),'Duy')]/../preceding-sibling::td//input");

    private String xpathCategoryCheckBox = "//td/a[contains(text(),'%s')]/ancestor::tr//input[@type='checkbox']";
    private By byArticleRowCount = By.xpath("//input[contains(@id,'cb')]");

    private By byStatusDropdown = By.cssSelector("div#filter_published_chzn");

    private By byListLimitDropdown = By.xpath("//select[@id='list_limit']/..");
    private String xpathArticleRow = "//input[@id='cb0']/ancestor::tbody/tr[td[@class='has-context' and contains(.,'%s')] and td[@class='small hidden-phone' and contains(.,'%s')]]";
    private By byGoToPageBtn = By.xpath("//a[contains(@aria-label,'Go to page')]");

    /**
     * WEB ELEMENTS
     */
    private WebElement recentArticleCheckbox() {
        return DriverHelper.getWebDriver().findElement(byRecentArticleCheckbox);
    }

    private WebElement categoryCheckBox(String categoryName) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathCategoryCheckBox, categoryName)));
    }

    private WebElement articleRow(String articleTitle, String author) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathArticleRow, articleTitle, author)));
    }
    private WebElement statusDropdown() {
        return DriverHelper.getWebDriver().findElement(byStatusDropdown);
    }
    private WebElement listLimitDropdown() {
        return DriverHelper.getWebDriver().findElement(byListLimitDropdown);
    }
    private List<WebElement> articleRows() {
        return DriverHelper.getWebDriver().findElements(byArticleRowCount);
    }

    /**
     * METHODS
     */

    public void clickArchiveBtn() {
        archiveBtn().click();
        Logger.info("Clicked the Archive button");
    }

    public void clickTrashBtn() {
        clickWhenElementReady(trashBtn());
    }

    public void clickSearchToolBtn() {
        clickWhenElementReady(searchToolsBtn());
    }

    public void clickCheckBox() {
        clickWhenElementReady(recentArticleCheckbox());
        Logger.info("Clicked the recent Article's checkbox");
    }

    public void archiveTheNewArticle() {
        clickCheckBox();
        clickArchiveBtn();
    }

    public void selectStatus(String status) {
        clickSearchToolBtn();
        clickWhenElementReady(statusDropdown());
        clickWhenElementReady(dropdownOption(status));
        Logger.info("   Selected status: " + status);
    }

    public void selectListLimit(String limit) {
        clickWhenElementReady(listLimitDropdown());
        clickWhenElementReady(dropdownOption(limit));
        Logger.info("   Selected list limit: " + limit);

    }

    public int getArticleRowCount() {
        return articleRows().size();
    }

    public int getTotalArticle() {
        int listLimit;
        int total;
        int totalPage;
        listLimit = getArticleRowCount();
        scrollToDownToElement(goToLastPageBtn());
        clickWhenElementReady(goToLastPageBtn());
        totalPage = Integer.parseInt(goToPageBtn().get(goToPageBtn().size() - 1).getText().trim());
        total = getArticleRowCount() + listLimit * totalPage;
        return total;
    }

    public void chooseNewlyCreatedCategory(String categoryName) throws NoSuchElementException {
        chooseNewlyCreatedContent(categoryCheckBox(categoryName));
    }


    public boolean isArticleRowDisplayed(String articleTitle, String author) {
        return verifyElementExist(articleRow(articleTitle, author));
    }

    public boolean isCategoryRowDisplayed(String categoryName) {
        return verifyElementExist(categoryCheckBox(categoryName));
    }

    public boolean isPageNavigationBarDisplayed() {
        return verifyElementExist(byGoToPageBtn) && verifyElementExist(goToLastPageBtn());
    }

}
