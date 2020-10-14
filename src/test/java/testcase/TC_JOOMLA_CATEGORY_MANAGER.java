package testcase;

import helper.DriverHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import utils.Constant;
import utils.Logger;

public class TC_JOOMLA_CATEGORY_MANAGER extends BaseTest {
    private LoginPage loginPage = new LoginPage();
    private MainPage mainPage = new MainPage();


    @BeforeMethod
    public void loginTheSystem() {
        Logger.info("Login the System");
        loginPage.login(Constant.VALID_USERNAME, Constant.VALID_PASSWORD);
    }


    @Test(testName = "TC_JOOMLA_CATEGORY_MANAGER_006")
    public void TC_JOOMLA_CATEGORY_MANAGER_006() {
        Logger.testCaseHeader("TC_JOOMLA_CATEGORY_MANAGER_006");
        Logger.testCaseDescription("Verify that user can send a category to trash");

        mainPage.clickSubMenuItem("Content","Categories");
        System.out.println(DriverHelper.getCurrentURL());

    }
}
