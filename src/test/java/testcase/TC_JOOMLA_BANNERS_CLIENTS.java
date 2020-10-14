package testcase;

import helper.DataHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.banner.page.BannerManagerPage;
import pageobject.banner.page.NewClientPage;
import utils.Constant;
import utils.Logger;

public class TC_JOOMLA_BANNERS_CLIENTS extends BaseTest {
    private LoginPage loginPage = new LoginPage();
    private MainPage mainPage = new MainPage();
    private BannerManagerPage bannerManagerPage = new BannerManagerPage();
    private NewClientPage newClientPage = new NewClientPage();

    @BeforeMethod
    public void loginTheSystem() {
        Logger.info("Login the System");
        loginPage.login(Constant.VALID_USERNAME, Constant.VALID_PASSWORD);
    }


    @Test(testName = "TC_JOOMLA_BANNERS_CLIENTS_001")
    public void TC_JOOMLA_BANNERS_CLIENTS_001() {
        Logger.testCaseHeader("TC_JOOMLA_BANNERS_CLIENTS_001");
        Logger.testCaseDescription("Verify that user can create a new client");

        //  Test data
        String clientName = DataHelper.generateName();
        String contactName = DataHelper.generateName();
        String contactEmail = DataHelper.generateEmail();

        //  Steps
        Logger.testCaseStep("4 - 5", "Open the New Client page");
        mainPage.clickSubMenuBannerItem("Clients");
        bannerManagerPage.clickNewBtn();

        Logger.testCaseStep("6 - 9", "Create a new Client");
        newClientPage.createNewClient(clientName, contactName, contactEmail);

        //Verify point
        Assert.assertEquals(bannerManagerPage.getSuccessMessage(), Constant.CLIENT_SAVE_SUCCESS_MESS);
        Assert.assertTrue(bannerManagerPage.isClientDisplayed(clientName));
        Logger.verifyPointPass("Correct message and client '" + clientName + "' present");

        Logger.logTestCasePass();
    }

    @Test(testName = "TC_JOOMLA_BANNERS_CLIENTS_008")
    public void TC_JOOMLA_BANNERS_CLIENTS_008() {
        Logger.testCaseHeader("TC_JOOMLA_BANNERS_CLIENTS_008");
        Logger.testCaseDescription("Verify that user can search a client  by using filter textbox");

        //  Test data
        String clientName = DataHelper.generateName();
        String contactName = DataHelper.generateName();
        String contactEmail = DataHelper.generateEmail();

        //  Steps
        Logger.testCaseStep("4 - 5", "Open the New Client page");
        mainPage.clickSubMenuBannerItem("Clients");
        bannerManagerPage.clickNewBtn();

        Logger.testCaseStep("6 - 9", "Create a new Client");
        newClientPage.createNewClient(clientName, contactName, contactEmail);

        //Verify point
        Assert.assertEquals(bannerManagerPage.getSuccessMessage(), Constant.CLIENT_SAVE_SUCCESS_MESS);
        Assert.assertTrue(bannerManagerPage.isClientDisplayed(clientName));
        Logger.verifyPointPass("Correct message and client '" + clientName + "' present");

        Logger.testCaseStep("11 - 12", "Perform search for the newly created client");
        bannerManagerPage.performSearch(clientName);

        //  Verify Point
        Assert.assertTrue(bannerManagerPage.isClientDisplayed(clientName));
        Logger.verifyPointPass("The client '" + clientName + "' presents");

        Logger.logTestCasePass();
    }

    @Test (testName = "TC_JOOMLA_BANNERS_CLIENTS_015")
    public void TC_JOOMLA_BANNERS_CLIENTS_015 () {
        Logger.testCaseHeader("TC_JOOMLA_BANNERS_CLIENTS_015");
        Logger.testCaseDescription("Verify that user cannot create a new client after entering invalid email address");

        //  Test data
        String clientName = DataHelper.generateName();
        String contactName = DataHelper.generateName();
        String contactEmail = "123123123";

        //  Steps
        Logger.testCaseStep("4 - 5", "Open the New Client page");
        mainPage.clickSubMenuBannerItem("Clients");
        bannerManagerPage.clickNewBtn();

        Logger.testCaseStep("6 - 9", "Create a new Client");
        newClientPage.createNewClient(clientName, contactName, contactEmail);
        newClientPage.getcolor();
        //  Verify Point
        Assert.assertFalse(newClientPage.validStatusOfContactEmailField());
        //Finding a way to verify the color code of the Contact Email field
        Assert.assertEquals(newClientPage.getInvalidFieldMessage(),Constant.INVALID_FIELD_EMAIL_MESS);
        Logger.verifyPointPass("The Contact mail box changed to red");


    }
}
