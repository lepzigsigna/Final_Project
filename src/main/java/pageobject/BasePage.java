package pageobject;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
    private By bySearchBtn = By.cssSelector("div[class='btn-wrapper input-append']>button");
    //private String xpathDropdownOption = "//ul[@class='chzn-results']/li[contains(.,'%s')]";
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


    /**
     * COMMON WEB ELEMENTS
     */
    //  Main page Elements
    protected WebElement menuItem(String item) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathMenuItem, item)));
    }

    protected WebElement subMenuItem(String subItem) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathSubMenuItem, subItem)));
    }

    //  Manager page elements
    protected WebElement newBtn() {
        return DriverHelper.getWebDriver().findElement(byNewBtn);
    }
    protected WebElement archiveBtn() {
        return DriverHelper.getWebDriver().findElement(byArchiveBtn);
    }
    protected WebElement trashBtn() { return DriverHelper.getWebDriver().findElement(byTrashBtn); }
    protected WebElement searchToolsBtn() { return DriverHelper.getWebDriver().findElement(bySearchToolsBtn); }
    protected WebElement successMsg() { return DriverHelper.getWebDriver().findElement(bySuccessMsg); }
    protected WebElement searchField() { return DriverHelper.getWebDriver().findElement(bySearchField); }
    protected WebElement searchBtn() { return DriverHelper.getWebDriver().findElement(bySearchBtn); }
    protected WebElement dropdownOption(String option) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathDropdownOption, option))); }

    protected List<WebElement> goToPageBtn() {
        return DriverHelper.getWebDriver().findElements(byGoToPageBtn);
    }
    protected WebElement goToNextPageBtn() {
        return DriverHelper.getWebDriver().findElement(byGoToNextPageBtn);
    }
    protected WebElement goToLastPageBtn() {
        return DriverHelper.getWebDriver().findElement(byGoToLastPageBtn);
    }

    // New content page elements
    protected WebElement nameField() { return DriverHelper.getWebDriver().findElement(byNameField); }
    protected WebElement titleField() { return DriverHelper.getWebDriver().findElement(byTitleField); }
    protected WebElement categoryDropdown() {
        return DriverHelper.getWebDriver().findElement(byCategoryDropdown);
    }
    protected WebElement saveBtn() { return DriverHelper.getWebDriver().findElement(bySaveBtn); }
    protected WebElement saveAsCopyBtn() {return DriverHelper.getWebDriver().findElement(bySaveAsCopyBtn); }
    protected WebElement saveAndCloseBtn() { return DriverHelper.getWebDriver().findElement(bySaveAndCloseBtn); }

    protected WebElement helpBtn() { return DriverHelper.getWebDriver().findElement(byHelpBtn); }
    protected WebElement closeBtn() {return DriverHelper.getWebDriver().findElement(byCloseBtn);}

    /**
     * COMMON METHODS
     */

    public void clickSubMenuItem(Constant.menuItem menuItem, Constant.subMenuItem subMenuItem) {
        clickWhenElementReady(menuItem(menuItem.toString()));
        clickWhenElementReady(subMenuItem(subMenuItem.toString()));
        Logger.info("Clicked item " + subMenuItem);
    }


    public void selectOptionOfStatusDrd(WebElement statusDropdown, String status) {
        clickWhenElementReady(searchToolsBtn());
        clickWhenElementReady(statusDropdown);
        clickWhenElementReady(dropdownOption(status));
        Logger.info("   Selected status: " + status);
    }

    public void clickNewBtn() {
        clickWhenElementReady(newBtn());
        Logger.info("Clicked the New button");
    }

    public void clickSaveAndCloseBtn() {
        clickWhenElementReady(saveAndCloseBtn());
        Logger.info("Click the Save and Close Button");
    }

    public String getSuccessMsg() {
        return getTextOf(successMsg());
    }

    public void chooseNewlyCreatedContent(WebElement element) throws NoSuchElementException {
        while (true) {
            try {
                if (verifyElementExist(element) == false) {
                } else {
                    clickWhenElementReady(element);
                    break;
                }
            } catch (NoSuchElementException e) {
                if (!verifyElementExist(goToNextPageBtn())) {
                    Logger.info("Reached the last page. Stop looking");
                    break;
                }
                Logger.info("   The new Content is not on this page. Go to the next page");
                scrollToDownToElement(goToNextPageBtn());
                clickWhenElementReady(goToNextPageBtn());
            }
        }
    }


    public boolean verifyElementExist(By by) throws NoSuchElementException {
        boolean result = true;
        WebElement webElement;
        try {
            webElement = DriverHelper.getWebDriver().findElement(by);
        } catch (NoSuchElementException e) {
            result = false;
        }
        return result;
    }

    public boolean verifyElementExist(WebElement element) throws NoSuchElementException {
        boolean result = true;
        try {
            if (element.isDisplayed()) result = true;
        } catch (NoSuchElementException e) {
            result = false;
        }
        return result;
    }

    public void hoverMouseOver(WebElement element) {
        Actions actions = new Actions(DriverHelper.getWebDriver());
        actions.moveToElement(element).perform();
    }

    public static void scrollToDownToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverHelper.getWebDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public String getTextOf(WebElement element) {
        return element.getText().trim();
    }

    public String getAttributeValueOf(WebElement element, String attribute) {
        return element.getAttribute(attribute).trim();
    }

    public void getColorCodeOf(WebElement element) {
        System.out.println(element.getCssValue("background-color"));
        System.out.println(element.getCssValue("color"));
        System.out.println(element.getCssValue("border-color"));
        System.out.println(element.getCssValue("border"));
        System.out.println(element.getCssValue("border:"));
        System.out.println(element.getCssValue("border-top-color"));
        System.out.println(element.getCssValue("border-bottom-color"));
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
}
