package pageobject.weblink.page;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.other.page.BasePage;
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

    /**
     * METHODS
     */
    public void insertImage(String imageName) {
        DriverHelper.getWebDriver().switchTo().frame(insertIframe());
        DriverHelper.getWebDriver().switchTo().frame(imageIframe());
        clickWhenElementReady(image(imageName));
        Logger.info(" + Choose image name: " + imageName);

        DriverHelper.getWebDriver().switchTo().defaultContent();
        DriverHelper.getWebDriver().switchTo().frame(insertIframe());
        clickWhenElementReady(insertBtn());
        Logger.info(" + Click Insert button");
        DriverHelper.getWebDriver().switchTo().defaultContent();
    }

    public void createNewWebLink(String title, String URL, String imageName) {
        enterTitleField(title);
        URLField().sendKeys(URL);
        clickWhenElementReady(imageBtn());
        Logger.info(" + Click image button");
        insertImage(imageName);
        clickSaveAndCloseBtn();
    }


}
