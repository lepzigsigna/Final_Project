package helper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import utils.Constant;
import utils.Logger;

import java.util.concurrent.TimeUnit;

public class DriverHelper {

    protected static WebDriver driver;

    public enum DriverType {
        CHROME, FIREFOX, EDGE
    }

    public void openBrowser(DriverType type) {
        switch (type) {
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                Logger.info("------------------------------ OPEN FIREFOX BROWSER -----------------------");
                DriverHelper.driver = new FirefoxDriver();
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                Logger.info("------------------------------ OPEN EDGE BROWSER --------------------------");
                DriverHelper.driver = new EdgeDriver();
                break;
            case CHROME:
                WebDriverManager.chromedriver().setup();
                Logger.info("------------------------------ OPEN CHROME BROWSER----------------------");
                driver = new ChromeDriver();
                break;
            default:
                throw new IllegalStateException("Unexpected Browser Type: " + type);
        }
        setBrowserProperties();
    }

    public static WebDriver getWebDriver() {
        return driver;
    }

    public void navigateToURL(String URL) {
        driver.navigate().to(URL);
        Logger.info("Navigated to URL: " + URL);
    }

    public static void setBrowserProperties() {
        driver.manage().timeouts().pageLoadTimeout(Constant.PAGE_LOAD_TIME_OUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Constant.IMPLICIT_WAIT,TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void closeOneWindow() {
        if (driver != null) {
            driver.close();
            driver = null;
            Logger.info("------------------------------ CLOSE BROWSER ---------------------------------\n\n");
        }
    }

    public void closeAllWindow() {
        if (driver != null) {
            driver.quit();
            Logger.info("------------------------------ CLOSE ALL ACTIVE BROWSER ------------------------------\n\n");
        }
    }

    public static String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public static String getCurrentTitle() {
        return driver.getTitle();
    }

    public static void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public static void dismissAlert() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public static void hoverMouseOver(WebElement element) {
        Actions actions = new Actions(DriverHelper.getWebDriver());
        actions.moveToElement(element).perform();
    }

    public static void scrollToDownToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverHelper.getWebDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }



}
