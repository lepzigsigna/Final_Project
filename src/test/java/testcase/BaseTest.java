package testcase;

import helper.DriverHelper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import utils.Constant;
import utils.Logger;

public class BaseTest extends DriverHelper {

    @BeforeMethod
    @Parameters("browser")
    public void setUpBrowser(String browser) {
        //Choose WebDriver following the testng.xml file
        if (browser.equalsIgnoreCase(Constant.BROWSER_CHROME)) {
            openBrowser(DriverType.CHROME);
        } else if (browser.equalsIgnoreCase(Constant.BROWSER_FIRE_FOX)) {
            openBrowser(DriverType.FIREFOX);
        } else if (browser.equalsIgnoreCase(Constant.BROWSER_EDGE)) {
            openBrowser(DriverType.EDGE);
        } else {
            Logger.info("ERROR! Invalid browser type!");
        }
        //Navigate to the Home Page
        navigateToURL(Constant.BASE_URL);
    }

    @AfterMethod
    public void closeBrowser(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.SUCCESS) {
                Logger.logTestCasePassed();
            } else if (result.getStatus() == ITestResult.FAILURE) {
                Logger.logTestCaseFailed();
            } else if (result.getStatus() == ITestResult.SKIP) {
                Logger.logTestCaseSkipped();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeOneWindow();
    }


    @AfterTest
    public void cleanUpAll() {
        closeAllWindow();
    }
}
