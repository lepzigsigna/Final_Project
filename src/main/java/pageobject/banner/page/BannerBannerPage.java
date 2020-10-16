package pageobject.banner.page;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.other.page.BasePage;
import utils.Logger;

public class BannerBannerPage extends BasePage {
    /**
     * LOCATORS
     */
    private By byClientDropdown = By.cssSelector("div[id='jform_cid_chzn']>a");
    private By byBannerDetailsTab = By.cssSelector("div.form-horizontal li>a[href='#otherparams']");
    private String xpathDropdownOption = "//ul[@class='chzn-results']/li[text()='%s']";

    /**
     * WEB ELEMENTS
     */


    private WebElement dropdownOption(String option) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathDropdownOption, option))); }

    private WebElement clientDropdown() {
        return DriverHelper.getWebDriver().findElement(byClientDropdown);
    }

    private WebElement bannerDetailsTab () {return DriverHelper.getWebDriver().findElement(byBannerDetailsTab);}


    /**
     * METHODS
     */
    public void createNewBanner(String bannerName,String categoryOption, String clientName) {
        enterNameField(bannerName);
        Logger.info("   Enter banner name: " + bannerName);
        selectCategory(categoryOption);
        clickWhenElementReady(bannerDetailsTab());
        clickWhenElementReady(clientDropdown());
        clickWhenElementReady(dropdownOption(clientName));
        Logger.info("   Choose Client Dropdown: " + clientName);
        clickSaveAndCloseBtn();
    }

}
