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

    private List<WebElement> goToPageBtn() {
        return DriverHelper.getWebDriver().findElements(byGoToPageBtn);
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

    /***
     * First calculate the Paging of the page
     * Second calculate the (Total page - 1) (line 60)
     * Then calculate the total item, it will be equal to the (Total page - 1)*(Num of item on 1 page) + (item on the last page)
     */
    public int getTotalContact() {
        int listLimit;
        int total;
        int totalPage;
        listLimit = getContactRowCount();
        clickGoToLastPageBtn();
        //  Has to used this method instead of goToPageBtn().size() since the maximum size of the goToPageBtn list is always 10
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

}
