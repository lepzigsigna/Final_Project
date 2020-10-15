package pageobject.weblink.page;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.BasePage;
import utils.Logger;

public class WebLinkWebLinkPage extends BasePage {

    /**
     * LOCATORS
     */
    private By byURLField = By.cssSelector("#jform_url");
    private By byImgBtn = By.cssSelector("div[aria-label='Image']>button");
    private String xpathImage = "//img[contains(@src,'powered_by.png')]/../..";
    private By byInsertBtn = By.cssSelector("button.button-save-selected");

    private By byImageIframe = By.cssSelector("iframe#imageframe");
    private By byInsertIframe = By.cssSelector("iframe[tabindex='-1']");
    private By byParentIframe = By.cssSelector("iframe#jform_description_ifr");

    /**
     * ELEMENTS
     */
    private WebElement URLField() {
        return DriverHelper.getWebDriver().findElement(byURLField);
    }

    private WebElement imageBtn() {
        return DriverHelper.getWebDriver().findElement(byImgBtn);
    }

    private WebElement image(String imageName) {
        return DriverHelper.getWebDriver().findElement(By.xpath(String.format(xpathImage, imageName)));
    }

    private WebElement insertBtn() {
        return DriverHelper.getWebDriver().findElement(byInsertBtn);
    }

    private WebElement imageIframe() {
        return DriverHelper.getWebDriver().findElement(byImageIframe);
    }
    private WebElement insertIframe() {
        return DriverHelper.getWebDriver().findElement(byInsertIframe);
    }
    private WebElement parentIframe() {
        return DriverHelper.getWebDriver().findElement(byParentIframe);
    }

    /**
     * METHODS
     */
    public void insertImage(String imageName) {
        Logger.info(" + Click image button");
        clickWhenElementReady(imageBtn());

        Logger.info(" + Choose image");
        DriverHelper.getWebDriver().switchTo().frame(parentIframe());
        DriverHelper.getWebDriver().switchTo().frame(insertIframe());
        DriverHelper.getWebDriver().switchTo().frame(imageIframe());
        clickWhenElementReady(image(imageName));

        Logger.info(" + Click Insert button");
        DriverHelper.getWebDriver().switchTo().defaultContent();
        DriverHelper.getWebDriver().switchTo().frame(parentIframe());
        clickWhenElementReady(insertBtn());
        DriverHelper.getWebDriver().switchTo().defaultContent();
    }


    public void createNewWebLink(String title, String URL, String imageName) {
        titleField().sendKeys(title);
        URLField().sendKeys(URL);
        insertImage(imageName);
        clickWhenElementReady(saveAndCloseBtn());
    }


}
