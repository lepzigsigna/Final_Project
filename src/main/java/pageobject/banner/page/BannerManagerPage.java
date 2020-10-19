package pageobject.banner.page;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.other.page.BasePage;
import utils.Logger;

public class BannerManagerPage extends BasePage {
    /**
     * LOCATORS
     */
    private String xpathBannerCheckBox = "//td[@class='has-context']//a[contains(text(),'%s')]/ancestor::tr//input";
    private String xpathClientCheckbox = "//div[@class='pull-left']/a[contains(text(),'%s')]/ancestor::tr//input";
    private String xpathCategoryCheckBox = "//a[@class='hasTooltip' and contains(text(),'%s')]/ancestor::tr//input[@type='checkbox']";

    /**
     * WEB ELEMENTS
     */
    private WebElement clientCheckBox(String clientName) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathClientCheckbox, clientName)));
    }

    private WebElement categoryCheckBox(String categoryName) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathCategoryCheckBox, categoryName)));
    }

    private WebElement bannerCheckBox(String bannerName) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathBannerCheckBox, bannerName)));
    }


    /**
     * METHODS
     */
    private void clickCheckBox(String bannerName) {
        selectSortByIDDescending();
        clickWhenElementReady(bannerCheckBox(bannerName));
        Logger.info("Clicked the recent Banner's checkbox");
    }

    public void archiveTheNewBanner(String bannerName) {
        clickSearchToolsBtn();
        clickCheckBox(bannerName);
        clickArchiveBtn();
    }

    public boolean isClientDisplayed(String clientName) {
        selectSortByIDDescending();
        return isElementPresent(clientCheckBox(clientName));
    }

    public boolean isCategoryDisplayed(String categoryName) {
        selectSortByIDDescending();
        return isElementPresent(categoryCheckBox(categoryName));
    }

    public boolean isBannerDisplayed(String bannerName) {
        selectSortByIDDescending();
        return isElementPresent(bannerCheckBox(bannerName));
    }
}
