package testcase;

import helper.DataHelper;
import helper.DriverHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject.article.page.ArticleCategoryPage;
import pageobject.article.page.ArticleManagerPage;
import pageobject.other.page.LoginPage;
import pageobject.other.page.MainPage;
import utils.Constant;
import utils.Logger;

public class TC_JOOMLA_CATEGORY_MANAGER extends BaseTest {
    private LoginPage loginPage = new LoginPage();
    private MainPage mainPage = new MainPage();
    private ArticleManagerPage articleManagerPage = new ArticleManagerPage();
    private ArticleCategoryPage articleCategoryPage = new ArticleCategoryPage();

    @BeforeMethod
    public void loginTheSystem() {
        Logger.info("Login the System");
        loginPage.login(Constant.VALID_USERNAME, Constant.VALID_PASSWORD);
    }


    @Test(testName = "TC_JOOMLA_CATEGORY_MANAGER_006")
    public void TC_JOOMLA_CATEGORY_MANAGER_006() {
        Logger.testCaseHeader("TC_JOOMLA_CATEGORY_MANAGER_006");
        Logger.testCaseDescription("Verify that user can send a category to trash");

        //  Test data
        String categoryName = DataHelper.generateCategoryName();


        //  Steps
        Logger.testCaseStep("4 -5 ", "Open the New Category page");
        mainPage.clickSubMenuItem(Constant.menuItem.Content, Constant.subMenuItem.Categories);
        articleManagerPage.clickNewBtn();

        Logger.testCaseStep("6- 7", "Create and save a new category");
        articleCategoryPage.createCategory(categoryName);

        //  Verify Point
        Assert.assertEquals(articleManagerPage.getSuccessMsg(), Constant.ARTICLE_CATEGORY_SAVED_SUCCESS_MESS,
                "The success message is not the same as the expected one");
        Logger.verifyPointPass("The success message is correct");

        Logger.testCaseStep("9 - 11", "Send the new category to Trash");
        articleManagerPage.moveCategoryToTrash(categoryName);

        //   Verify Point
        articleManagerPage.selectStatus("Trashed");
        Assert.assertTrue(articleManagerPage.isCategoryRowDisplayed(categoryName));
        Logger.verifyPointPass("The correct category is displayed in Trashed section");

        Logger.logTestCasePass();
    }

    @Test(testName = "TC_JOOMLA_CATEGORY_MANAGER_013")
    public void TC_JOOMLA_CATEGORY_MANAGER_013() {
        Logger.testCaseHeader("TC_JOOMLA_CATEGORY_MANAGER_013");
        Logger.testCaseDescription("Verify that user can create a new category by using 'Save as Copy' button");

        //  Test data
        String categoryName = DataHelper.generateCategoryName();
        String categoryNameEdited = categoryName + "_edited";
        String editPageTitle;

        //  Steps
        Logger.testCaseStep("4 -5 ", "Open the New Category page");
        mainPage.clickSubMenuItem(Constant.menuItem.Content, Constant.subMenuItem.Categories);
        articleManagerPage.clickNewBtn();

        Logger.testCaseStep("6- 7", "Create and save a new category");
        articleCategoryPage.saveCategory(categoryName);
        editPageTitle = DriverHelper.getCurrentTitle().substring(0, 23);

        //  Verify Point
        Assert.assertEquals(articleCategoryPage.getSuccessMsg(), Constant.ARTICLE_CAT_SAVE_SUCCESS_MESS,
                "The success message is not the same as the expected one");
        Assert.assertEquals(editPageTitle, Constant.ARTICLE_CAT_EDIT_PAGE_TITLE);
        Logger.verifyPointPass("The success message is correct");


        Logger.testCaseStep("9 - 10", "Create a copy of this new category");
        articleCategoryPage.saveCategoryAsCopy(categoryNameEdited);
        articleCategoryPage.clickCloseBtn();

        //   Verify Point
        Assert.assertTrue(articleManagerPage.isCategoryRowDisplayed(categoryName), "The new category is not present");
        Assert.assertTrue(articleManagerPage.isCategoryRowDisplayed(categoryNameEdited), "The edited category is not present");
        Logger.verifyPointPass("Both categories present on the page");

        Logger.logTestCasePass();
    }
}
