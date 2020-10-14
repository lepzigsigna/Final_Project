package pageobject;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constant;


public class BasePage {

    public boolean checkIfElementExist(By by) throws NoSuchElementException {
        boolean result = true;
        WebElement webElement;
        try {
            webElement = DriverHelper.getWebDriver().findElement(by);
        } catch (NoSuchElementException e) {
            //e.printStackTrace();
            result = false;
        }
        return result;
    }

    public boolean checkIfElementExist(WebElement element) throws NoSuchElementException {
        boolean result = true;
        try {
            if (element.isDisplayed()) result = true;
        } catch (NoSuchElementException e) {
            //e.printStackTrace();
            result = false;
        }
        return result;
    }

    public void hoverMouseOver(WebElement element) {
        Actions actions = new Actions(DriverHelper.getWebDriver());
        actions.moveToElement(element).perform();
    }

    public static void scrollToDownToElement(WebElement element) {
        //_driver = driver;
        JavascriptExecutor js = (JavascriptExecutor) DriverHelper.getWebDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public String getTextOf(WebElement element) {
        return element.getText().trim();
    }

    public String getAttributeValueOf(WebElement element, String attribute) {
        return element.getAttribute(attribute).trim();
    }

    public void getColorCodeOf(WebElement element) {
        System.out.println(element.getCssValue("background-color"));
        System.out.println(element.getCssValue("color"));
        System.out.println(element.getCssValue("border-color"));
        System.out.println(element.getCssValue("border"));
        System.out.println(element.getCssValue("border-top-color"));
        System.out.println(element.getCssValue("border-bottom-color"));
    }


    public void clickWhenElementReady(WebElement element) {
        waitUntilVisible(element);
        waitUntilClickable(element);
        //Logger.info("Waited for element!");
        element.click();
    }

    public void waitUntilVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverHelper.getWebDriver(), Constant.EXPLICIT_WAIT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public void waitUntilClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverHelper.getWebDriver(), Constant.EXPLICIT_WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
