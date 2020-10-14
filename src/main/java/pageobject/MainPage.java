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
    private By byArticleManagerBtn = By.xpath("//a/span[text()='Articles']");
    private By byMenuComponent = By.xpath("//li[@class='dropdown']/a[contains(.,'Components')]");
    private By bySubMenuContact = By.xpath("//li[@class='dropdown-submenu']/a[contains(.,'Contact')]");
    private By bySubMenuBanner = By.xpath("//li[@class='dropdown-submenu']/a[contains(.,'Banners')]");
    private String bySubMenuBannerItem = "//ul[@id='nav-empty']//a[contains(text(),'%s')]";

    /**
     * WEB ELEMENTS
     */
    private WebElement articleManagerBtn() {
        return DriverHelper.getWebDriver().findElement(byArticleManagerBtn);
    }

    private WebElement menuComponent() {
        return DriverHelper.getWebDriver().findElement(byMenuComponent);
    }

    private WebElement subMenuContact() {
        return DriverHelper.getWebDriver().findElement(bySubMenuContact);
    }

    private WebElement subMenuBanner() {
        return DriverHelper.getWebDriver().findElement(bySubMenuBanner);
    }

    private WebElement subMenuBannerItem(String item) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(bySubMenuBannerItem, item)));
    }

    /**
     * METHODS
     */
    public void clickArticleManagerBtn() {
        clickWhenElementReady(articleManagerBtn());
        Logger.info("Open the Article Manager page");
    }

    public void clickSubMenuContact() {
        clickWhenElementReady(menuComponent());
        clickWhenElementReady(subMenuContact());
        Logger.info("Open the Contact page");
    }



    public void clickSubMenuBannerItem(String item) {
        clickWhenElementReady(menuComponent());
        hoverMouseOver(subMenuBanner());
        clickWhenElementReady(subMenuBannerItem(item));
        Logger.info("Open the " + item + " page");
    }

}
