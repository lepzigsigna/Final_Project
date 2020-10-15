package pageobject.banner.page;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pageobject.BasePage;
import utils.Logger;

public class BannerManagerPage extends BasePage {
    /**
     * LOCATORS
     */
    private String xpathBannerCheckBox = "//td[@class='has-context']//a[contains(text(),'%s')]/ancestor::tr//input";
    private String xpathClientCheckbox = "//div[@class='pull-left']/a[contains(text(),'%s')]/ancestor::tr//input";
    private String xpathCategoryCheckBox = "//a[@class='hasTooltip' and contains(text(),'%s')]/ancestor::tr//input[@type='checkbox']";

    private By byStatusDropdown = By.cssSelector("div#filter_published_chzn");


    //private String xpathDropdownOption = "//ul[@class='chzn-results']/li[contains(.,'%s')]";


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

    private WebElement statusDropdown() {
        return DriverHelper.getWebDriver().findElement(byStatusDropdown);
    }


    /**
     * METHODS
     */
    public void clickArchiveBtn() {
        clickWhenElementReady(archiveBtn());
        Logger.info("Clicked the Archive button");
    }

    public void clickSearchToolBtn() {
        clickWhenElementReady(searchToolsBtn());
    }

    public void clickCheckBox(String bannerName) {
        clickWhenElementReady(bannerCheckBox(bannerName));
        Logger.info("Clicked the recent Banner's checkbox");
    }

    public void clickSearchBtn() {
        clickWhenElementReady(searchBtn());
        Logger.info("   Click the Search button");
    }

    public void performSearch(String keywords) {
        waitUntilVisible(searchField());
        searchField().sendKeys(keywords);
        clickSearchBtn();
        Logger.info("   Entered into search: " + keywords);

    }

    public void archiveTheNewBanner(String bannerName) {
        clickSearchToolBtn();
        clickCheckBox(bannerName);
        clickArchiveBtn();
    }

    public void selectStatus(String status) {
        selectOptionOfStatusDrd(statusDropdown(),status);
        Logger.info("   Selected status: " + status);
    }

    public boolean isClientDisplayed(String clientName) {
        return verifyElementExist(clientCheckBox(clientName));
    }

    public boolean isCategoryDisplayed(String categoryName) {
        return verifyElementExist(categoryCheckBox(categoryName));
    }

    public boolean isBannerDisplayed(String bannerName) {
        return verifyElementExist(bannerCheckBox(bannerName));
    }

    public void chooseNewlyCreatedCategory (String categoryName) throws NoSuchElementException {
        chooseNewlyCreatedContent(categoryCheckBox(categoryName));
    }

}
