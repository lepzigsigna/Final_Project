package pageobject.banner.page;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.BasePage;
import utils.Logger;

public class NewBannerPage extends BasePage {
    /**
     * LOCATORS
     */
    private By byBannerName = By.cssSelector("#jform_name");
    private By bySaveAndCloseBtn = By.cssSelector("#toolbar-save>button");
    private By byCategoryDropdown = By.xpath("//div[@class='controls']/select[@id='jform_catid']/..");
    private By byClientDropdown = By.cssSelector("div[id='jform_cid_chzn']>a");
    private String xpathDropdownOption = "//ul[@class='chzn-results']/li[contains(.,'%s')]";
    private By byBannerDetailsTab = By.cssSelector("div.form-horizontal li>a[href='#otherparams']");

    private By byHelpBtn = By.cssSelector("#toolbar-help>button");

    /**
     * WEB ELEMENTS
     */

    private WebElement bannerName() {
        return DriverHelper.getWebDriver().findElement(byBannerName);
    }

    private WebElement saveAndCloseBtn() {
        return DriverHelper.getWebDriver().findElement(bySaveAndCloseBtn);
    }

    private WebElement categoryDropdown() {
        return DriverHelper.getWebDriver().findElement(byCategoryDropdown);
    }

    private WebElement clientDropdown() {
        return DriverHelper.getWebDriver().findElement(byClientDropdown);
    }

    private WebElement dropdownOption(String option) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathDropdownOption,option)));
    }

    private WebElement bannerDetailsTab () {return DriverHelper.getWebDriver().findElement(byBannerDetailsTab);}

    private WebElement helpButton() { return DriverHelper.getWebDriver().findElement(byHelpBtn);}

    /**
     * METHODS
     */

    public void clickSaveAndCloseBtn() {
        saveAndCloseBtn().click();
        Logger.info("Click the Save and Close Button");
    }

    public void createNewBanner(String bannerName,String categoryOption, String clientName) {
        bannerName().sendKeys(bannerName);
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
        clickWhenElementReady(helpButton());
        Logger.info("Clicked the Help button");
    }

}
