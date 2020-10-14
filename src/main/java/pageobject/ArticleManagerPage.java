package pageobject;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Logger;

import java.util.List;

public class ArticleManagerPage extends BasePage {

    /**
     * LOCATORS
     */
    private By byNewBtn = By.cssSelector("#toolbar-new>button");
    private By byArchiveBtn = By.cssSelector("#toolbar-archive>button");
    private By bySearchToolsBtn = By.cssSelector("div[class='btn-wrapper hidden-phone']>button");
    private By byRecentArticleCheckbox = By.xpath("//tbody/tr/td[@class='small hidden-phone']/a[contains(text(),'Duy')]/../preceding-sibling::td//input");
    private By bySuccessMessage = By.cssSelector("div[class='alert alert-success']>div");
    private By byArticleRowCount = By.xpath("//input[contains(@id,'cb')]");
    private By byStatusDropdown = By.xpath("//select[@id='filter_published']/../div");
    private By byListLimitDropdown = By.xpath("//select[@id='list_limit']/..");
    private String xpathDropdownOption = "//ul[@class='chzn-results']/li[contains(.,'%s')]";
    private String xpathArticleRow = "//input[@id='cb0']/ancestor::tbody/tr[td[@class='has-context' and contains(.,'%s')] and td[@class='small hidden-phone' and contains(.,'%s')]]";
    private By byGoToPageBtn = By.xpath("//a[contains(@aria-label,'Go to page')]");
    private By byGoToLastPageBtn = By.cssSelector("a[aria-label='Go to end page']");

    /**
     * WEB ELEMENTS
     */
    private WebElement newBtn() {
        return DriverHelper.getWebDriver().findElement(byNewBtn);
    }

    private WebElement archiveBtn() {
        return DriverHelper.getWebDriver().findElement(byArchiveBtn);
    }

    private WebElement searchToolsBtn() {
        return DriverHelper.getWebDriver().findElement(bySearchToolsBtn);
    }


    private WebElement recentArticleCheckbox() {
        return DriverHelper.getWebDriver().findElement(byRecentArticleCheckbox);
    }

    private WebElement successMessage() {
        return DriverHelper.getWebDriver().findElement(bySuccessMessage);
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

    private WebElement dropdownOption(String option) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathDropdownOption, option)));
    }

    private List<WebElement> articleRows() {
        return DriverHelper.getWebDriver().findElements(byArticleRowCount);
    }

    private List<WebElement> goToPageBtn() {
        return DriverHelper.getWebDriver().findElements(byGoToPageBtn);
    }

    private WebElement goToLastPageBtn() {
        return DriverHelper.getWebDriver().findElement(byGoToLastPageBtn);
    }

    /**
     * METHODS
     */
    public void clickNewBtn() {
        waitUntilVisible(newBtn());
        newBtn().click();
        Logger.info("Clicked the New button");
    }

    public void clickArchiveBtn() {
        archiveBtn().click();
        Logger.info("Clicked the Archive button");
    }

    public void clickSearchToolBtn() {
        clickWhenElementReady(searchToolsBtn());
        searchToolsBtn().click();
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
        clickWhenElementReady(searchToolsBtn());
        clickWhenElementReady(statusDropdown());
        clickWhenElementReady(dropdownOption(status));
        Logger.info("   Selected status: " + status);
    }

    public void selectListLimit(String limit) {
        clickWhenElementReady(listLimitDropdown());
        clickWhenElementReady(dropdownOption(limit));
        Logger.info("   Selected list limit: " + limit);

    }

    public String getSuccessMessage() {
        return getTextOf(successMessage());
    }

    public int getArticleRowCount() {
        return articleRows().size();
    }

    public int getTotalArticle() {
        int listLimit;
        int total;
        int totalPage;
        listLimit = getArticleRowCount();
        //System.out.println("List limit is: " + listLimit);
        scrollToDownToElement(goToLastPageBtn());
        clickWhenElementReady(goToLastPageBtn());
        totalPage = Integer.parseInt(goToPageBtn().get(goToPageBtn().size() - 1).getText().trim());
        //System.out.println("Total Page is: " + totalPage + " + 1");
        total = getArticleRowCount() + listLimit * totalPage;
        //System.out.println("Total article: " + total);
        return total;
    }


    public boolean isArticleRowDisplayed(String articleTitle, String author) {
        return checkIfElementExist(articleRow(articleTitle, author));
    }

    public boolean isPageNavigationBarDisplayed() {
        return checkIfElementExist(byGoToPageBtn) && checkIfElementExist(goToLastPageBtn());
    }


}
