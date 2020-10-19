package testcase;

import helper.DataHelper;
import helper.BrowserHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject.other.page.LoginPage;
import pageobject.other.page.MainPage;
import pageobject.banner.page.BannerManagerPage;
import pageobject.banner.page.BannerBannerPage;
import pageobject.banner.page.BannerCategoryPage;
import pageobject.banner.page.BannerClientPage;
import utils.Constant;
import utils.Logger;

public class TC_JOOMLA_BANNERS_BANNERS extends BaseTest {
    private LoginPage loginPage = new LoginPage();
    private MainPage mainPage = new MainPage();
    private BannerManagerPage bannerManagerPage = new BannerManagerPage();
    private BannerClientPage newClientPage = new BannerClientPage();
    private BannerCategoryPage newCategoryPage = new BannerCategoryPage();
    private BannerBannerPage newBannerPage = new BannerBannerPage();

    @BeforeMethod
    public void loginTheSystem() {
        Logger.info("Login the System");
        loginPage.login(Constant.VALID_USERNAME, Constant.VALID_PASSWORD);
    }


    @Test(testName = "TC_JOOMLA_BANNERS_BANNERS_005")
    public void TC_JOOMLA_BANNERS_BANNERS_005() {
        Logger.testCaseHeader("TC_JOOMLA_BANNERS_BANNERS_005");
        Logger.testCaseDescription("Verify that user can archive a banner");

        //  Test data
        String clientName = DataHelper.generateName();
        String contactName = DataHelper.generateName();
        String contactEmail = DataHelper.generateEmail();
        String categoryName = DataHelper.generateCategoryName();
        String bannerName = DataHelper.generateName();

        //  Steps
        Logger.testCaseStep("5 - 6", "Open the New Client page");
        mainPage.clickSubMenuBannerItem("Clients");
        bannerManagerPage.clickNewBtn();

        Logger.testCaseStep("8 - 11", "Create a new client");
        newClientPage.createNewClient(clientName, contactName, contactEmail);

        //  Verify point
        Assert.assertEquals(bannerManagerPage.getSuccessMsg(), Constant.CLIENT_SAVE_SUCCESS_MESS);
        Assert.assertTrue(bannerManagerPage.isClientDisplayed(clientName));
        Logger.verifyPointPass("Correct message and client '" + clientName + "' present");

        Logger.testCaseStep("13 - 14", "Open the New Category page");
        mainPage.clickSubMenuBannerItem("Categories");
        bannerManagerPage.clickNewBtn();

        Logger.testCaseStep("16 -17", "Create a new Category");
        newCategoryPage.createNewCategory(categoryName);

        //  Verify point
        Assert.assertEquals(bannerManagerPage.getSuccessMsg(), Constant.ARTICLE_CATEGORY_SAVED_SUCCESS_MESS);
        Assert.assertTrue(bannerManagerPage.isCategoryDisplayed(categoryName));
        Logger.verifyPointPass("Correct message and category '" + categoryName + "' present");

        Logger.testCaseStep("19 - 20", "Open the New Banner page");
        mainPage.clickSubMenuBannerItem("Banners");
        bannerManagerPage.clickNewBtn();

        Logger.testCaseStep("22 - 25", "Create a new Banner");
        newBannerPage.createNewBanner(bannerName, categoryName, clientName);

        //  Verify Point
        Assert.assertEquals(bannerManagerPage.getSuccessMsg(), Constant.BANNER_SAVE_SUCCESS_MESS);
        Assert.assertTrue(bannerManagerPage.isBannerDisplayed(bannerName));
        Logger.verifyPointPass("Correct message and the banner with name '" + bannerName + "' presents");

        Logger.testCaseStep("27 - 28", "Archive the newly created banner");
        bannerManagerPage.archiveTheNewBanner(bannerName);

        //   Verify Point
        Assert.assertEquals(bannerManagerPage.getSuccessMsg(), Constant.BANNER_ARCHIVED_SUCCESS_MESS);
        Logger.verifyPointPass("Correct message is displayed");

        Logger.testCaseStep("30", "Open the 'Archived banners' section");
        bannerManagerPage.selectStatus("Archived");

        //   Verify Point
        Assert.assertTrue(bannerManagerPage.isBannerDisplayed(bannerName));
        Logger.verifyPointPass("Correct banner with name '" + bannerName + "' present");
    }

    @Test(testName = "TC_JOOMLA_BANNERS_BANNERS_012")
    public void TC_JOOMLA_BANNERS_BANNERS_012() {
        Logger.testCaseHeader("TC_JOOMLA_BANNERS_BANNERS_012");
        Logger.testCaseDescription("User can browse 'New Banner help' page in 'New banner' page");

        Logger.testCaseStep("5", "Open the Banner manager page");
        mainPage.clickSubMenuBannerItem("Banners");

        Logger.testCaseStep("6", "Open the New Banner page");
        bannerManagerPage.clickNewBtn();

        Logger.testCaseStep("7", "Click the Help button");
        newBannerPage.clickHelpBtn();

        //  Verify Point
        Assert.assertEquals(BrowserHelper.getNumOfActiveWindow(), Constant.BANNER_HELP_WINDOW_COUNT, "The total amount of active windows is not 2");
        Assert.assertEquals(BrowserHelper.getPopupWindowURL(), Constant.BANNER_HELP_PAGE_URL, "The popup page URL is not correct");
        Logger.verifyPointPass("The correct 'New Banner Help' page appeared");
    }
}
