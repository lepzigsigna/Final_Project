package pageobject.banner.page;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.BasePage;
import utils.Logger;

public class BannerBannerPage extends BasePage {
    /**
     * LOCATORS
     */
    private By byClientDropdown = By.cssSelector("div[id='jform_cid_chzn']>a");
    private By byBannerDetailsTab = By.cssSelector("div.form-horizontal li>a[href='#otherparams']");
    /**
     * WEB ELEMENTS
     */

//    private WebElement bannerName() {
//        return DriverHelper.getWebDriver().findElement(byBannerName);
//    }


    private WebElement clientDropdown() {
        return DriverHelper.getWebDriver().findElement(byClientDropdown);
    }

    private WebElement bannerDetailsTab () {return DriverHelper.getWebDriver().findElement(byBannerDetailsTab);}


    /**
     * METHODS
     */
    public void createNewBanner(String bannerName,String categoryOption, String clientName) {
        nameField().sendKeys(bannerName);
        Logger.info("   Enter banner name: " + bannerName);
        clickWhenElementReady(categoryDropdown());
        clickWhenElementReady(dropdownOption(categoryOption));
        Logger.info("   Choose Category Dropdown: " + categoryOption);
        clickWhenElementReady(bannerDetailsTab());
        clickWhenElementReady(clientDropdown());
        clickWhenElementReady(dropdownOption(clientName));
        Logger.info("   Choose Client Dropdown: " + clientName);
        clickSaveAndCloseBtn();
    }

    public void clickHelpBtn() {
        clickWhenElementReady(helpBtn());
        Logger.info("Clicked the Help button");
    }
}
