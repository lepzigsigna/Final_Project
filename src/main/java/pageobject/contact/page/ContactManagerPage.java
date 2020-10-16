package pageobject.contact.page;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.other.page.BasePage;

import java.util.List;

public class ContactManagerPage extends BasePage {

    /**
     * LOCATORS
     */

    private String xpathRecentContactCheckBox = "//td[@class='has-context']//a[contains(text(),'%s')]/ancestor::tr/td/input";
    private By byContactRowCount = By.xpath("//input[contains(@id,'cb')]");
    private By byGoToPageBtn = By.xpath("//a[contains(@aria-label,'Go to page')]");


    /**
     * ELEMENTS
     */
    private WebElement recentContactCheckBox(String contactName) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathRecentContactCheckBox, contactName)));
    }

    private List<WebElement> contactRows() {
        return DriverHelper.getWebDriver().findElements(byContactRowCount);
    }


    /**
     * METHODS
     */

    public void selectNewContact(String contactName) {
        clickWhenElementReady(recentContactCheckBox(contactName));
    }

    public void archiveNewContact(String contactName) {
        selectNewContact(contactName);
        clickArchiveBtn();
    }


    public int getTotalContact() {
        int listLimit;
        int total;
        int totalPage;
        listLimit = getContactRowCount();
        DriverHelper.scrollToDownToElement(goToLastPageBtn());
        clickWhenElementReady(goToLastPageBtn());
        totalPage = Integer.parseInt(goToPageBtn().get(goToPageBtn().size() - 1).getText().trim());
        total = getContactRowCount() + listLimit * totalPage;
        return total;
    }

    public int getContactRowCount() {
        return contactRows().size();
    }

    public boolean isContactRowDisplayed(String contactName) {
        selectSortByIDDescending();
        return isElementPresent(recentContactCheckBox(contactName));
    }

    public boolean isPageNavigationBarDisplayed() {
        return isElementPresent(byGoToPageBtn) && isElementPresent(goToLastPageBtn());
    }


}
