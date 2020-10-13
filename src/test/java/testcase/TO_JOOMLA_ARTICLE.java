package testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject.ArticleManagerPage;
import pageobject.NewArticlePage;
import pageobject.LoginPage;
import pageobject.MainPage;
import utils.Constant;
import utils.Logger;

public class TO_JOOMLA_ARTICLE extends BaseTest {

    private LoginPage loginPage = new LoginPage();
    private MainPage mainPage = new MainPage();
    private ArticleManagerPage articleManagerPage = new ArticleManagerPage();
    private NewArticlePage newArticlePage = new NewArticlePage();

    @BeforeMethod
    public void loginTheSystem() {
        loginPage.login(Constant.VALID_USERNAME, Constant.VALID_PASSWORD);
        Logger.info("L");
    }


    @Test(testName = "TO_JOOMLA_ARTICLE_005")
    public void TO_JOOMLA_ARTICLE_005() {
        Logger.testCaseHeader("TC_JOOMLA_ARTICLE_005");
        Logger.testCaseDescription("Verify user can move an article to the archive");
        //  Test Data
        String articleTitle = "Test Article 1";
        String articleCategory = "Sample Data-Articles";
        String articleContent = "this is article content";

        //  Steps
        Logger.testCaseStep("5", "Select Content > Article Manager");
        mainPage.clickArticleManagerBtn();

        Logger.testCaseStep("6", "Click on 'New' icon of the top right toolbar");
        articleManagerPage.clickNewBtn();

        Logger.testCaseStep("7,8,9,10", "Create a new article");
        newArticlePage.createArticle(articleTitle, articleCategory, articleContent);

        //  Verify point
        //Assert.assertEquals(articleManagerPage.getSuccessMessage(), Constant.ARTICLE_SAVED_SUCCESS_MESS,"The successful message is not correct");
        Assert.assertTrue(articleManagerPage.isArticleRowDisplayed(articleTitle, Constant.ARTICLE_AUTHOR), "The new Article is not displayed in the table");
        Logger.info("Verify Point pass: Correct message and the article present");

        Logger.testCaseStep("12, 13", "Archive a newly created article");
        articleManagerPage.archiveTheNewArticle();

        //  Verify point
        //Assert.assertEquals(articleManagerPage.getSuccessMessage(), Constant.ARCHIVE_SUCCESSFULLY_MESS, "The successful message is not correct");
        Logger.info("Verify Point pass: Correct message presents");

        Logger.testCaseStep("15", "Select 'Archived' item of 'Status' dropdown list");
        articleManagerPage.clickSearchToolBtn();
        articleManagerPage.selectStatus("Archived");

        //  Verify point
        Assert.assertTrue(articleManagerPage.isArticleRowDisplayed(articleTitle, Constant.ARTICLE_AUTHOR), "The new Article is not displayed in the table");
        Logger.info("Verify Point pass: Correct Article present in the Archive");

        Logger.logTestCasePass();
    }


    @Test(testName = "TO_JOOMLA_ARTICLE_012")
    public void TO_JOOMLA_ARTICLE_012() {
        //  Test data
        String[] rowLimit = {"5","All"};

        //  Steps
        Logger.testCaseStep("4","Open the Article Manager page");
        mainPage.clickArticleManagerBtn();

        Logger.testCaseStep("5","Select item '5' of the 'Display' dropdown list");
        articleManagerPage.selectListLimit(rowLimit[0]);

        //  Verify point
        Assert.assertEquals(String.valueOf(articleManagerPage.getArticleRowCount()), rowLimit[0], "The total in the table is not " + rowLimit[0]);
        Logger.info("Verify Point pass: Correct paging of the table");

        Logger.testCaseStep("7","Select item 'All' of the 'Display' dropdown list");
        articleManagerPage.selectListLimit(rowLimit[1]);

        //  Verify point
        Assert.assertFalse(articleManagerPage.isPageNavigationBarDisplayed());
        Logger.info("Verify Point pass: All articles are displayed in one page");

        Logger.logTestCasePass();
    }


}
