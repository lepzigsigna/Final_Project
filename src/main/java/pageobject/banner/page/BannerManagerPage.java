package pageobject.banner.page;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.BasePage;
import utils.Logger;

public class BannerManagerPage extends BasePage {
    /**
     * LOCATORS
     */
    private By byNewBtn = By.cssSelector("#toolbar-new>button");
    private By byArchiveBtn = By.cssSelector("#toolbar-archive>button");
    private By bySearchToolsBtn = By.cssSelector("div[class='btn-wrapper hidden-phone']>button");
    private By bySearchTextBox = By.cssSelector("input#filter_search");
    private By bySearchBtn = By.cssSelector("div[class='btn-wrapper input-append']>button");

    private String byBannerCheckBox = "//td[@class='has-context']//a[contains(text(),'%s')]/ancestor::tr//input";
    private String byClientCheckbox = "//div[@class='pull-left']/a[contains(text(),'%s')]/ancestor::tr//input";
    private String byCategoryCheckBox = "//a[@class='hasTooltip' and contains(text(),'%s')]/ancestor::tr//input[@type='checkbox']";

    private By bySuccessMessage = By.cssSelector("div[class='alert alert-success']>div");
    private By byStatusDropdown = By.xpath("//select[@id='filter_published']/../div");
    private String xpathDropdownOption = "//ul[@class='chzn-results']/li[contains(.,'%s')]";


    /**
     * WEB ELEMENTS
     */
    private WebElement newBtn() {
        return DriverHelper.getWebDriver().findElement(byNewBtn);
    }

    private WebElement archiveBtn() {
        return DriverHelper.getWebDriver().findElement(byArchiveBtn);
    }

    private WebElement searchToolsBtn() {
        return DriverHelper.getWebDriver().findElement(bySearchToolsBtn);
    }

    private WebElement searchTextBox() { return DriverHelper.getWebDriver().findElement(bySearchTextBox);}

    private WebElement searchBtn() { return DriverHelper.getWebDriver().findElement(bySearchBtn);}

    private WebElement clientCheckBox(String clientName) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(byClientCheckbox,clientName)));
    }

    private WebElement categoryCheckBox (String categoryName) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(byCategoryCheckBox,categoryName)));
    }

    private WebElement bannerCheckBox(String bannerName) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(byBannerCheckBox, bannerName)));
    }

    private WebElement successMessage() {
        return DriverHelper.getWebDriver().findElement(bySuccessMessage);
    }


    private WebElement statusDropdown() {
        return DriverHelper.getWebDriver().findElement(byStatusDropdown);
    }


    private WebElement dropdownOption(String option) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathDropdownOption, option)));
    }


    /**
     * METHODS
     */
    public void clickNewBtn() {
        clickWhenElementReady(newBtn());
        Logger.info("Clicked the New button");
    }

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
        waitUntilVisible(searchTextBox());
        searchTextBox().sendKeys(keywords);
        Logger.info("   Entered in to search: " + keywords);

    }

    public void archiveTheNewBanner(String bannerName) {
        clickSearchToolBtn();
        clickCheckBox(bannerName);
        clickArchiveBtn();
    }

    public void selectStatus(String status) {
        clickWhenElementReady(searchToolsBtn());
        clickWhenElementReady(statusDropdown());
        clickWhenElementReady(dropdownOption(status));
        Logger.info("   Selected status: " + status);
    }


    public String getSuccessMessage() {
        return getTextOf(successMessage());
    }

    public boolean isClientDisplayed (String clientName) {
        return checkIfElementExist(clientCheckBox(clientName));
    }

    public boolean isCategoryDisplayed (String categoryName) {
        return checkIfElementExist(categoryCheckBox(categoryName));
    }

    public boolean isBannerDisplayed(String bannerName) {
        return checkIfElementExist(bannerCheckBox(bannerName));
    }




}
