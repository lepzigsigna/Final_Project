package pageobject.contact.page;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.BasePage;
import utils.Logger;

import java.util.List;

public class ContactManagerPage extends BasePage {

    /**
     * LOCATORS
     */
    private By bySearchToolBtn = By.cssSelector("div[class='btn-wrapper hidden-phone']>button");

    private String xpathRecentContactCheckBox = "//td[@class='has-context']//a[contains(text(),'%s')]/ancestor::tr/td/input";


    private By byContactRowCount = By.xpath("//input[contains(@id,'cb')]");


    private By byStatusDropdown = By.cssSelector("div#filter_published_chzn");



    private By byListLimitDropdown = By.xpath("//select[@id='list_limit']/..");
    private By byGoToPageBtn = By.xpath("//a[contains(@aria-label,'Go to page')]");


    /**
     * ELEMENTS
     */
    //private WebElement searchToolBtn() { return DriverHelper.getWebDriver().findElement(bySearchToolBtn);}
    private WebElement recentContactCheckBox(String contactName) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathRecentContactCheckBox, contactName)));
    }

    private WebElement statusDropdown() { return DriverHelper.getWebDriver().findElement(byStatusDropdown);}
    private WebElement listLimitDropdown() {
        return DriverHelper.getWebDriver().findElement(byListLimitDropdown);
    }


    private List<WebElement> contactRows() {
        return DriverHelper.getWebDriver().findElements(byContactRowCount);
    }


    /**
     * METHODS
     */

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
        clickWhenElementReady(searchToolsBtn());
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

    public boolean isContactRowDisplayed(String contactName) {
        return verifyElementExist(recentContactCheckBox(contactName));
    }

    public boolean isPageNavigationBarDisplayed() {
        return verifyElementExist(byGoToPageBtn) && verifyElementExist(goToLastPageBtn());
    }


}
