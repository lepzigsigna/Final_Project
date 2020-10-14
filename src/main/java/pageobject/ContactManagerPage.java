package pageobject;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Logger;

import java.util.List;

public class ContactManagerPage extends BasePage {

    /**
     * LOCATORS
     */
    private By byNewBtn = By.cssSelector("#toolbar-new>button");
    private By byArchiveBtn = By.cssSelector("#toolbar-archive>button");
    private By bySearchToolBtn = By.cssSelector("div[class='btn-wrapper hidden-phone']>button");
    private By bySuccessMessage = By.cssSelector("div[class='alert alert-success']>div");

    private String xpathRecentContactCheckBox = "//td[@class='has-context']//a[contains(text(),'%s')]/ancestor::tr/td/input";


    private By byContactRowCount = By.xpath("//input[contains(@id,'cb')]");
    private By byStatusDropdown = By.xpath("//select[@id='filter_published']/../div");
    private By byListLimitDropdown = By.xpath("//select[@id='list_limit']/..");
    private String xpathDropdownOption = "//ul[@class='chzn-results']/li[contains(.,'%s')]";
    private By byGoToPageBtn = By.xpath("//a[contains(@aria-label,'Go to page')]");
    private By byGoToLastPageBtn = By.cssSelector("a[aria-label='Go to end page']");


    /**
     * ELEMENTS
     */
    private WebElement newBtn() {
        return DriverHelper.getWebDriver().findElement(byNewBtn);
    }
    private WebElement archiveBtn () { return DriverHelper.getWebDriver().findElement(byArchiveBtn);}
    private WebElement searchToolBtn() { return DriverHelper.getWebDriver().findElement(bySearchToolBtn);}
    private WebElement successMessage() { return DriverHelper.getWebDriver().findElement(bySuccessMessage);}
    private WebElement recentContactCheckBox(String contactName) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathRecentContactCheckBox, contactName)));
    }

    private WebElement statusDropdown() { return DriverHelper.getWebDriver().findElement(byStatusDropdown);}
    private WebElement listLimitDropdown() {
        return DriverHelper.getWebDriver().findElement(byListLimitDropdown);
    }

    private WebElement dropdownOption(String option) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathDropdownOption, option)));
    }

    private List<WebElement> contactRows() {
        return DriverHelper.getWebDriver().findElements(byContactRowCount);
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
        clickWhenElementReady(newBtn());
    }

    public void clickArchiveBtn() {
        clickWhenElementReady(archiveBtn());
    }

    public void selectNewContact(String contactName) {
        clickWhenElementReady(recentContactCheckBox(contactName));
    }

    public void archiveNewContact(String contactName) {
        selectNewContact(contactName);
        clickArchiveBtn();
    }

    public void selectStatus (String status) {
        clickWhenElementReady(searchToolBtn());
        clickWhenElementReady(statusDropdown());
        clickWhenElementReady(dropdownOption(status));
    }

    public void selectListLimit(String limit) {
        clickWhenElementReady(listLimitDropdown());
        clickWhenElementReady(dropdownOption(limit));
        Logger.info("   Selected list limit: " + limit);

    }

    public int getTotalContact() {
        int listLimit;
        int total;
        int totalPage;
        listLimit = getContactRowCount();
        scrollToDownToElement(goToLastPageBtn());
        clickWhenElementReady(goToLastPageBtn());
        totalPage = Integer.parseInt(goToPageBtn().get(goToPageBtn().size() - 1).getText().trim());
        total = getContactRowCount() + listLimit * totalPage;
        return total;
    }


    public int getContactRowCount() {
        return contactRows().size();
    }

    public String getSuccessMessage() {
        return getTextOf(successMessage());
    }


    public boolean isContactRowDisplayed(String contactName) {
        return checkIfElementExist(recentContactCheckBox(contactName));
    }


    public boolean isPageNavigationBarDisplayed() {
        return checkIfElementExist(byGoToPageBtn) && checkIfElementExist(goToLastPageBtn());
    }


}
