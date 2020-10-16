package pageobject.other.page;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constant;
import utils.Logger;

import java.util.List;


public class BasePage {

    /**
     * COMMON LOCATORS
     */
    //Navigation bar locators
    private String xpathMenuItem = "//li[@class='dropdown']/a[contains(text(),'%s')]";
    private String xpathSubMenuItem = "//li[@class='dropdown-submenu']/a[text()='%s']";

    //Manager page locators
    private By byNewBtn = By.cssSelector("#toolbar-new>button");
    private By byArchiveBtn = By.cssSelector("#toolbar-archive>button");
    private By byTrashBtn = By.cssSelector("#toolbar-trash>button");
    private By bySearchToolsBtn = By.cssSelector("div[class='btn-wrapper hidden-phone']>button");
    private By bySuccessMsg = By.cssSelector("div.alert-success .alert-message");
    private By bySearchField = By.cssSelector("input#filter_search");
    private By byStatusDropdown = By.cssSelector("div#filter_published_chzn");
    private By bySortDropdown = By.cssSelector("div#list_fullordering_chzn");
    private By byListLimitDropdown = By.xpath("//select[@id='list_limit']/..");
    private By bySearchBtn = By.cssSelector("div[class='btn-wrapper input-append']>button");
    private String xpathDropdownOption = "//ul[@class='chzn-results']/li[text()='%s']";
    private By byGoToPageBtn = By.xpath("//a[contains(@aria-label,'Go to page')]");
    private By byGoToNextPageBtn = By.cssSelector("a[aria-label='Go to next page']");
    private By byGoToLastPageBtn = By.cssSelector("a[aria-label='Go to end page']");

    //  New content page locators
    private By byNameField = By.cssSelector("input#jform_name");
    private By byTitleField = By.cssSelector("input#jform_title");
    private By byCategoryDropdown = By.xpath("//div[@class='controls']/select[@id='jform_catid']/..");
    private By bySaveBtn = By.cssSelector("#toolbar-apply");
    private By bySaveAsCopyBtn = By.cssSelector("#toolbar-save-copy");
    private By bySaveAndCloseBtn = By.cssSelector("#toolbar-save");
    private By byHelpBtn = By.cssSelector("#toolbar-help");
    private By byCloseBtn = By.cssSelector("#toolbar-cancel");

    //  Pop-up Browser page locator
    private By byPopUpPageHeader = By.xpath("//a[@id='Top']/following-sibling::h1");

    /**
     * COMMON WEB ELEMENTS
     */
    //  Main page Elements
    private WebElement menuItem(String item) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathMenuItem, item)));
    }

    private WebElement subMenuItem(String subItem) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathSubMenuItem, subItem)));
    }

    //  Manager page elements
    private WebElement newBtn() {
        return DriverHelper.getWebDriver().findElement(byNewBtn);
    }

    private WebElement archiveBtn() {
        return DriverHelper.getWebDriver().findElement(byArchiveBtn);
    }

    private WebElement trashBtn() {
        return DriverHelper.getWebDriver().findElement(byTrashBtn);
    }

    private WebElement searchToolsBtn() {
        return DriverHelper.getWebDriver().findElement(bySearchToolsBtn);
    }

    private WebElement statusDropdown() {
        return DriverHelper.getWebDriver().findElement(byStatusDropdown);
    }

    private WebElement sortDropdown() {
        return DriverHelper.getWebDriver().findElement(bySortDropdown);
    }

    private WebElement listLimitDropdown() {
        return DriverHelper.getWebDriver().findElement(byListLimitDropdown);
    }

    private WebElement successMsg() {
        return DriverHelper.getWebDriver().findElement(bySuccessMsg);
    }

    private WebElement searchField() {
        return DriverHelper.getWebDriver().findElement(bySearchField);
    }

    private WebElement searchBtn() {
        return DriverHelper.getWebDriver().findElement(bySearchBtn);
    }

    private WebElement dropdownOption(String option) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathDropdownOption, option)));
    }

    protected List<WebElement> goToPageBtn() {
        return DriverHelper.getWebDriver().findElements(byGoToPageBtn);
    }

    private WebElement goToNextPageBtn() {
        return DriverHelper.getWebDriver().findElement(byGoToNextPageBtn);
    }

    protected WebElement goToLastPageBtn() {
        return DriverHelper.getWebDriver().findElement(byGoToLastPageBtn);
    }

    // New content page elements
    private WebElement nameField() {
        return DriverHelper.getWebDriver().findElement(byNameField);
    }

    private WebElement titleField() {
        return DriverHelper.getWebDriver().findElement(byTitleField);
    }

    private WebElement categoryDropdown() {
        return DriverHelper.getWebDriver().findElement(byCategoryDropdown);
    }

    private WebElement saveBtn() {
        return DriverHelper.getWebDriver().findElement(bySaveBtn);
    }

    private WebElement saveAsCopyBtn() {
        return DriverHelper.getWebDriver().findElement(bySaveAsCopyBtn);
    }

    private WebElement saveAndCloseBtn() {
        return DriverHelper.getWebDriver().findElement(bySaveAndCloseBtn);
    }

    private WebElement helpBtn() {
        return DriverHelper.getWebDriver().findElement(byHelpBtn);
    }

    private WebElement closeBtn() {
        return DriverHelper.getWebDriver().findElement(byCloseBtn);
    }

    //  Pop-up Browser page locator
    private WebElement popUpPageHeader() {
        return DriverHelper.getWebDriver().findElement(byPopUpPageHeader);
    }

    /**
     * COMMON METHODS
     */
    public void clickSubMenuItem(Constant.menuItem menuItem, Constant.subMenuItem subMenuItem) {
        clickWhenElementReady(menuItem(menuItem.toString()));
        clickWhenElementReady(subMenuItem(subMenuItem.toString()));
        Logger.info("Clicked item " + subMenuItem);
    }

    public void clickNewBtn() {
        clickWhenElementReady(newBtn());
        Logger.info("Clicked the New button");
    }

    public void clickArchiveBtn() {
        clickWhenElementReady(archiveBtn());
        Logger.info("Clicked the Archive button");

    }

    public void clickTrashBtn() {
        clickWhenElementReady(trashBtn());
    }

    public void clickSaveBtn() {
        clickWhenElementReady(saveBtn());
        Logger.info("Click the Save Button");
    }

    public void clickSaveAsCopyBtn() {
        clickWhenElementReady(saveAsCopyBtn());
        Logger.info("Click the Save As Copy Button");
    }

    public void clickSaveAndCloseBtn() {
        clickWhenElementReady(saveAndCloseBtn());
        Logger.info("Click the Save and Close Button");
    }

    public void clickHelpBtn() {
        clickWhenElementReady(helpBtn());
        Logger.info("Clicked the Help button");
    }

    public void clickCloseBtn() {
        clickWhenElementReady(closeBtn());
    }

    public void clickSearchToolsBtn() {
        clickWhenElementReady(searchToolsBtn());
    }

    public void enterNameField(String name) {
        nameField().sendKeys(name);
        Logger.info("   Entered name: " + name);

    }

    public void enterTitleField(String title) {
        titleField().clear();
        titleField().sendKeys(title);
        Logger.info("   Entered title: " + title);
    }


    public void selectSortOption(String status) {
        clickWhenElementReady(sortDropdown());
        clickWhenElementReady(dropdownOption(status));
        Logger.info("Selected: " + status);
    }

    public void selectSortByIDDescending() {
        clickWhenElementReady(sortDropdown());
        clickWhenElementReady(dropdownOption("ID descending"));
        Logger.info("Selected: " + "ID descending");
    }

    public void selectListLimit(String limit) {
        clickWhenElementReady(listLimitDropdown());
        clickWhenElementReady(dropdownOption(limit));
        Logger.info("   Selected list limit: " + limit);

    }

    public void selectStatus(String status) {
        clickWhenElementReady(searchToolsBtn());
        clickWhenElementReady(statusDropdown());
        clickWhenElementReady(dropdownOption(status));
        Logger.info("   Selected: " + status);
    }

    public void selectCategory(String category) {
        clickWhenElementReady(categoryDropdown());
        clickWhenElementReady(dropdownOption(category));
        Logger.info("   Selected: " + category);
    }

    public void performSearch(String keyword) {
        waitUntilVisible(searchField());
        searchField().sendKeys(keyword);
        clickWhenElementReady(searchBtn());
        Logger.info("   Entered into search: " + keyword);
    }

    public String getSuccessMsg() {
        return getTextOf(successMsg());
    }

    public void waitPopUpPageHeader() {
        waitUntilVisible(popUpPageHeader());
    }

    public void chooseNewlyCreatedContent(WebElement element) throws NoSuchElementException {
        while (true) {
            try {
                if (isElementPresent(element) == false) {
                } else {
                    clickWhenElementReady(element);
                    break;
                }
            } catch (NoSuchElementException e) {
                if (!isElementPresent(goToNextPageBtn())) {
                    Logger.info("Reached the last page. Stop looking");
                    break;
                }
                Logger.info("   The new Content is not on this page. Go to the next page");
                DriverHelper.scrollToDownToElement(goToNextPageBtn());
                clickWhenElementReady(goToNextPageBtn());
            }
        }
    }

    public boolean isElementPresent(By by) throws NoSuchElementException {
        boolean result = true;
        WebElement webElement;
        try {
            webElement = DriverHelper.getWebDriver().findElement(by);
        } catch (NoSuchElementException e) {
            result = false;
        }
        return result;
    }

    public boolean isElementPresent(WebElement element) throws NoSuchElementException {
        boolean result = true;
        try {
            if (element.isDisplayed()) result = true;
        } catch (NoSuchElementException e) {
            result = false;
        }
        return result;
    }

    public String getTextOf(WebElement element) {
        return element.getText().trim();
    }

    public String getAttributeValueOf(WebElement element, String attribute) {
        return element.getAttribute(attribute).trim();
    }

    public String getColorCodeOf(WebElement element) {
        String borderColor = element.getCssValue("border-color");
        String hex = Color.fromString(borderColor).asHex();
        return hex;
    }

    public void clickWhenElementReady(WebElement element) {
        waitUntilVisible(element);
        waitUntilClickable(element);
        element.click();
    }

    public void waitUntilVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverHelper.getWebDriver(), Constant.EXPLICIT_WAIT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public void waitUntilClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverHelper.getWebDriver(), Constant.EXPLICIT_WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void insertImage(WebElement imgBtn, WebElement insertIframe, WebElement imgIframe, WebElement image, WebElement insertBtn) {
        clickWhenElementReady(imgBtn);
        Logger.info("   Click the image button");

        waitUntilVisible(insertIframe);
        DriverHelper.getWebDriver().switchTo().frame(insertIframe);

        waitUntilVisible(imgIframe);
        DriverHelper.getWebDriver().switchTo().frame(imgIframe);

        clickWhenElementReady(image);
        Logger.info("   Choose image");

        DriverHelper.getWebDriver().switchTo().defaultContent();
        DriverHelper.getWebDriver().switchTo().frame(insertIframe);
        clickWhenElementReady(insertBtn);
        Logger.info("   Click the Insert button");
        DriverHelper.getWebDriver().switchTo().defaultContent();
    }


}
