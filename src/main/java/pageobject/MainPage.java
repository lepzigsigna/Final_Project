package pageobject;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Constant;
import utils.Logger;

public class MainPage extends BasePage {
    /**
     * LOCATORS
     */
    private By byMenuComponent = By.xpath("//li[@class='dropdown']/a[contains(.,'Components')]");
    private By bySubMenuBanner = By.xpath("//li[@class='dropdown-submenu']/a[contains(.,'Banners')]");
    private String xpathSubMenuBannerItem = "//ul[@id='nav-empty']//a[contains(text(),'%s')]";

    /**
     * WEB ELEMENTS
     */
    private WebElement menuComponent() {
        return DriverHelper.getWebDriver().findElement(byMenuComponent);
    }

    private WebElement subMenuBanner() {
        return DriverHelper.getWebDriver().findElement(bySubMenuBanner);
    }

    private WebElement subMenuBannerItem(String item) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathSubMenuBannerItem, item)));
    }

    /**
     * METHODS
     */
    public void clickWebLinks() {
        clickWhenElementReady(menuItem("Components"));
        clickWhenElementReady(subMenuItem("Web Links"));
    }


    public void clickSubMenuBannerItem(String item) {
        clickWhenElementReady(menuComponent());
        hoverMouseOver(subMenuBanner());
        clickWhenElementReady(subMenuBannerItem(item));
        Logger.info("Open the " + item + " page");
    }
}
