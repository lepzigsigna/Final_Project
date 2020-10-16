package pageobject.other.page;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Logger;

public class MainPage extends BasePage {
    /**
     * LOCATORS
     */
    private By byMenuComponent = By.xpath("//li[@class='dropdown']/a[.='Components ']");
    private By bySubMenuBanner = By.xpath("//li[@class='dropdown open']//a[@class='dropdown-toggle menu-banners' and .='Banners']");
    private By bySubMenuWebLink = By.xpath("//li[@class='dropdown open']//a[@class='dropdown-toggle menu-weblinks' and .='Web Links']");
    private String xpathSubMenuBannerItem = "//ul[@id='nav-empty']/li/a[text()='%s']";


    /**
     * WEB ELEMENTS
     */
    private WebElement menuComponent() {
        return DriverHelper.getWebDriver().findElement(byMenuComponent);
    }

    private WebElement subMenuBanner() {
        return DriverHelper.getWebDriver().findElement(bySubMenuBanner);
    }

    private WebElement subMenuWebLink() {
        return DriverHelper.getWebDriver().findElement(bySubMenuWebLink);
    }

    private WebElement subMenuBannerItem(String item) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathSubMenuBannerItem, item)));
    }

    /**
     * METHODS
     */
    public void OpenWebLinksPage() {
        clickWhenElementReady(menuComponent());
        clickWhenElementReady(subMenuWebLink());
    }

    public void clickSubMenuBannerItem(String item) {
        clickWhenElementReady(menuComponent());
        DriverHelper.hoverMouseOver(subMenuBanner());
        clickWhenElementReady(subMenuBannerItem(item));
        Logger.info("Open the " + item + " page");
    }
}
