package testcase;

import helper.DataHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject.article.page.ArticleManagerPage;
import pageobject.article.page.ArticleArticlePage;
import pageobject.other.page.LoginPage;
import pageobject.other.page.MainPage;
import utils.Constant;
import utils.Logger;

public class TO_JOOMLA_ARTICLE extends BaseTest {

    private LoginPage loginPage = new LoginPage();
    private MainPage mainPage = new MainPage();
    private ArticleManagerPage articleManagerPage = new ArticleManagerPage();
    private ArticleArticlePage newArticlePage = new ArticleArticlePage();

    @BeforeMethod
    public void loginTheSystem() {
        Logger.info("Login the System");
        loginPage.login(Constant.VALID_USERNAME, Constant.VALID_PASSWORD);
    }


    @Test(testName = "TO_JOOMLA_ARTICLE_005")
    public void TO_JOOMLA_ARTICLE_005() {
        Logger.testCaseHeader("TC_JOOMLA_ARTICLE_005");
        Logger.testCaseDescription("Verify user can move an article to the archive");
        //  Test Data
        String author = "Duy";
        String articleTitle = DataHelper.generateArticleTitle();
        String articleCategory = "Sample Data-Articles";
        String articleContent = DataHelper.generateArticleContent();

        //  Steps
        Logger.testCaseStep("5", "Select Content > Article Manager");
        mainPage.clickSubMenuItem(Constant.menuItem.Content, Constant.subMenuItem.Articles);

        Logger.testCaseStep("6", "Click on 'New' icon of the top right toolbar");
        articleManagerPage.clickNewBtn();

        Logger.testCaseStep("7 - 10", "Create a new article");
        newArticlePage.createArticle(articleTitle, articleCategory, articleContent);

        //  Verify point
        Assert.assertEquals(articleManagerPage.getSuccessMsg(), Constant.ARTICLE_SAVED_SUCCESS_MESS, "The successful message is not correct");
        Assert.assertTrue(articleManagerPage.isArticleRowDisplayed(articleTitle, Constant.ARTICLE_AUTHOR), "The new Article is not displayed in the table");
        Logger.verifyPointPass("Correct message and the article present");

        Logger.testCaseStep("12 - 13", "Archive a newly created article");
        articleManagerPage.moveArticleToArchive(articleTitle, author);

        //  Verify point
        Assert.assertEquals(articleManagerPage.getSuccessMsg(), Constant.ARCHIVE_SUCCESSFULLY_MESS, "The successful message is not correct");
        Logger.verifyPointPass("Correct message presents");

        Logger.testCaseStep("15", "Select 'Archived' item of 'Status' dropdown list");
        articleManagerPage.selectStatus("Archived");

        //  Verify point
        Assert.assertTrue(articleManagerPage.isArticleRowDisplayed(articleTitle, Constant.ARTICLE_AUTHOR), "The new Article is not displayed in the table");
        Logger.verifyPointPass("Correct Article present in the Archive");
    }

    @Test(testName = "TO_JOOMLA_ARTICLE_012")
    public void TO_JOOMLA_ARTICLE_012() {
        Logger.testCaseHeader("TC_JOOMLA_ARTICLE_012");
        Logger.testCaseDescription("Verify user can paging the articles using the paging control");

        //  Test data
        String[] rowLimit = {"5", "All"};
        int totalArticle;
        //  Steps
        Logger.testCaseStep("4", "Open the Article Manager page");
        mainPage.clickSubMenuItem(Constant.menuItem.Content, Constant.subMenuItem.Articles);

        Logger.testCaseStep("5", "Select item '5' of the 'Display' dropdown list");
        articleManagerPage.selectListLimit(rowLimit[0]);

        //  Verify point
        Assert.assertEquals(String.valueOf(articleManagerPage.getArticleRowCount()), rowLimit[0], "The total in the table is not " + rowLimit[0]);
        Logger.verifyPointPass("Correct paging of the table");

        Logger.testCaseStep("7", "Select item 'All' of the 'Display' dropdown list");
        //  Get total number of article
        totalArticle = articleManagerPage.getTotalArticle();
        articleManagerPage.selectListLimit(rowLimit[1]);

        //  Verify point
        Assert.assertFalse(articleManagerPage.isPageNavigationBarDisplayed());
        Assert.assertEquals(totalArticle, articleManagerPage.getArticleRowCount());
        Logger.verifyPointPass("All articles are displayed in one page");
    }
}
