package testcase;

import helper.DriverHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import utils.Constant;
import utils.Logger;

public class BaseTest extends DriverHelper {

//    @BeforeMethod
//    @Parameters("browser")
//    public void setUpBrowser(String browser) {
//        //Choose WebDriver following the testng.xml file
//        if (browser.equalsIgnoreCase(Constant.BROWSER_CHROME)) {
//            openBrowser(DriverType.CHROME);
//        } else if (browser.equalsIgnoreCase(Constant.BROWSER_FIRE_FOX)) {
//            openBrowser(DriverType.FIREFOX);
//        } else if (browser.equalsIgnoreCase(Constant.BROWSER_EDGE)) {
//            openBrowser(DriverType.EDGE);
//        } else {
//            Logger.info("ERROR! Invalid browser type!");
//        }
//        //Navigate to the Home Page
//        navigateToURL(Constant.BASE_URL);
//    }



    @BeforeMethod
    public void setUpBrowser1() {
        openBrowser(DriverType.CHROME);
        Logger.testCaseStep("1","Open the Base URL");
        navigateToURL(Constant.BASE_URL);
    }

    @AfterMethod
    public void closeBrowser() {
        closeOneWindow();
    }

    @AfterTest
    public void cleanUpAll() {
        closeAllWindow();
    }
}
