package testcase;

import helper.DataHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.weblink.page.WebLinkManagerPage;
import pageobject.weblink.page.WebLinkWebLinkPage;
import utils.Constant;
import utils.Logger;

public class TO_JOOMLA_WEBLINKS extends BaseTest {

    private LoginPage loginPage = new LoginPage();
    private MainPage mainPage = new MainPage();
    private WebLinkManagerPage webLinkManagerPage = new WebLinkManagerPage();
    private WebLinkWebLinkPage webLinkPage = new WebLinkWebLinkPage();

    @BeforeMethod
    public void loginTheSystem() {
        Logger.info("Login the System");
        loginPage.login(Constant.VALID_USERNAME, Constant.VALID_PASSWORD);
    }

    @Test(testName = "TO_JOOMLA_WEBLINKS_013")
    public void TO_JOOMLA_WEBLINKS_013() {
        Logger.testCaseHeader("TO_JOOMLA_WEBLINKS_013");
        Logger.testCaseDescription("User can add image to weblink's description");

        //  TEST DATA
        String webLinkName = DataHelper.generateName();
        String webLinkURL = DataHelper.generateURL();
        String imageName = "powered_by.png";

        mainPage.clickWebLinks();
        webLinkManagerPage.clickNewBtn();

        webLinkPage.createNewWebLink(webLinkName, webLinkURL, imageName);


        Assert.assertTrue(webLinkManagerPage.isWebLinkDisplayed(webLinkName));
        Assert.assertEquals(webLinkManagerPage.getSuccessMsg(), Constant.WEBLINK_SAVE_SUCCESS_MESS, "The successful message is not correct");
        Logger.verifyPointPass("The message is correct and the weblink " + webLinkName + " is present");
    }


}
