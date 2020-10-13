package pageobject;

import helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constant;
import utils.Logger;

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


    public String getTextOf(WebElement element) {
        return element.getText().trim();
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
