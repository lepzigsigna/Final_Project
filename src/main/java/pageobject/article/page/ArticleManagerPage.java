package pageobject.article.page;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pageobject.other.page.BasePage;
import utils.Logger;

import java.util.List;

public class ArticleManagerPage extends BasePage {

    /**
     * LOCATORS
     */
    private String xpathArticleCheckbox = "//tbody/tr[contains(@class,'row') and td[@class='has-context' and contains(.,'%s')] and td[contains(.,'%s')]]//input";
    private String xpathCategoryCheckBox = "//td/a[contains(text(),'%s')]/ancestor::tr//input[@type='checkbox']";
    private By byArticleRowCount = By.xpath("//input[contains(@id,'cb')]");
    private String xpathArticleRow = "//tbody/tr[contains(@class,'row') and td[@class='has-context' and contains(.,'%s')] and td[contains(.,'%s')]]";
    private By byGoToPageBtn = By.xpath("//a[contains(@aria-label,'Go to page')]");

    /**
     * WEB ELEMENTS
     */
    private WebElement articleCheckbox(String articleTitle, String author) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathArticleCheckbox, articleTitle, author)));
    }

    private WebElement categoryCheckBox(String categoryName) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathCategoryCheckBox, categoryName)));
    }

    private WebElement articleRow(String articleTitle, String author) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathArticleRow, articleTitle, author)));
    }

    private List<WebElement> articleRows() {
        return DriverHelper.getWebDriver().findElements(byArticleRowCount);
    }

    private List<WebElement> goToPageBtn() {
        return DriverHelper.getWebDriver().findElements(byGoToPageBtn);
    }

    /**
     * METHODS
     */
    public void clickCheckBox(String articleTitle, String author) {
        clickWhenElementReady(articleCheckbox(articleTitle, author));
        Logger.info("Clicked the Article's checkbox");
    }

    public void moveArticleToArchive(String articleTitle, String author) {
        clickCheckBox(articleTitle, author);
        clickArchiveBtn();
    }

    public int getArticleRowCount() {
        return articleRows().size();
    }

    /***
     * First calculate the Paging of the page
     * Second calculate the (Total page - 1) (line 75)
     * Then calculate the total item, it will be equal to the (Total page - 1)*(Num of item on 1 page) + (item on the last page)
     */
    public int getTotalArticle() {
        int listLimit;
        int total;
        int totalPage;
        listLimit = getArticleRowCount();
        clickGoToLastPageBtn();
        //  Has to used this method instead of goToPageBtn().size() since the maximum size of the goToPageBtn list is always 10
        totalPage = Integer.parseInt(goToPageBtn().get(goToPageBtn().size() - 1).getText().trim());
        total = getArticleRowCount() + listLimit * totalPage;
        return total;
    }

    public void moveCategoryToTrash(String categoryName) {
        selectSortByIDDescending();
        clickWhenElementReady(categoryCheckBox(categoryName));
        clickTrashBtn();
    }

    public boolean isArticleRowDisplayed(String articleTitle, String author) {
        selectSortByIDDescending();
        return isElementPresent(articleRow(articleTitle, author));
    }

    public boolean isCategoryRowDisplayed(String categoryName) {
        selectSortByIDDescending();
        return isElementPresent(categoryCheckBox(categoryName));
    }

    public boolean isPageNavigationBarDisplayed() {
        return isElementPresent(byGoToPageBtn);
    }

}
