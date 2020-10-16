package pageobject.weblink.page;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.other.page.BasePage;

public class WebLinkManagerPage extends BasePage {
    /**
     *  LOCATORS
     */
    private String xpathWebLinkRow = "//td[@class='nowrap has-context']//a[contains(text(),'%s')]/ancestor::tr";

    /**
     *  ELEMENTS
     */
    private WebElement webLinkRow(String weblinkName) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathWebLinkRow,weblinkName)));
    }


    /**
     *  METHODS
     */
    public boolean isWebLinkDisplayed(String webLinkName) {
        selectSortByIDDescending();
        return isElementPresent(webLinkRow(webLinkName));
    }





}
