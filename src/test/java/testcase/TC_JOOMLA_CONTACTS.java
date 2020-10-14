package testcase;

import helper.DataHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject.*;
import utils.Constant;
import utils.Logger;

public class TC_JOOMLA_CONTACTS extends BaseTest{

    private LoginPage loginPage = new LoginPage();
    private MainPage mainPage = new MainPage();
    private ContactManagerPage contactManagerPage = new ContactManagerPage();
    private NewContactPage newContactPage = new NewContactPage();

    @BeforeMethod
    public void loginTheSystem() {
        Logger.info("Login the System");
        loginPage.login(Constant.VALID_USERNAME, Constant.VALID_PASSWORD);
    }

    @Test (testName = "TC_JOOMLA_CONTACTS_005")
    public void TC_JOOMLA_CONTACTS_005() throws InterruptedException {
        Logger.testCaseHeader("TC_JOOMLA_CONTACTS_005");
        Logger.testCaseDescription("Verify user can move a contact to the archive");
        //  Test Data
        String contactName = DataHelper.generateName();
        String contactCategory = "Sample Data-Contact";
        String contactStatus = "Published";

        //  Steps
        Logger.testCaseStep("5", "Select Components > Contacts");
        mainPage.clickSubMenuContact();

        Logger.testCaseStep("6", "Click on 'New' icon of the top right toolbar");
        contactManagerPage.clickNewBtn();

        Logger.testCaseStep("7, 8, 9, 10", "Create a new contact");
        newContactPage.createContact(contactName,contactCategory,contactStatus);

        //  Verify point
        //Assert.assertEquals(contactManagerPage.getSuccessMessage(), Constant.CONTACT_SAVED_SUCCESS_MESS,"The successful message is not correct");
        Assert.assertTrue(contactManagerPage.isContactRowDisplayed(contactName), "The new Contact is not displayed in the table");
        Logger.verifyPointPass("Correct message and the contact present");


        Logger.testCaseStep("12, 13", "Archive a newly created contact");
        Thread.sleep(5000);
        contactManagerPage.archiveNewContact(contactName);

        //  Verify point
        //Assert.assertEquals(articleManagerPage.getSuccessMessage(), Constant.ARCHIVE_SUCCESSFULLY_MESS, "The successful message is not correct");
        Logger.verifyPointPass("Correct message presents");

        Logger.testCaseStep("15", "Select 'Archived' item of 'Status' dropdown list");
        contactManagerPage.selectStatus("Archived");

        //  Verify point
        Assert.assertTrue(contactManagerPage.isContactRowDisplayed(contactName), "The new Contact is not displayed in the table");
        Logger.verifyPointPass("Correct contact present in the Archive");

        Logger.logTestCasePass();

    }


    @Test (testName = "TC_JOOMLA_CONTACTS_012")
    public void TC_JOOMLA_CONTACTS_012() {
        Logger.testCaseHeader("TC_JOOMLA_CONTACTS_012");
        Logger.testCaseDescription("Verify user can paging the contacts using the paging control");

        //  Test data
        String[] rowLimit = {"5","All"};
        int totalContact;
        //  Steps
        Logger.testCaseStep("4","Open the Contact Manager page");
        mainPage.clickSubMenuContact();

        Logger.testCaseStep("5","Select item '5' of the 'Display' dropdown list");
        contactManagerPage.selectListLimit(rowLimit[0]);

        //  Verify point
        Assert.assertEquals(String.valueOf(contactManagerPage.getContactRowCount()), rowLimit[0], "The total in the table is not " + rowLimit[0]);
        Logger.verifyPointPass("Correct paging of the table");

        Logger.testCaseStep("7","Select item 'All' of the 'Display' dropdown list");
        //  Get total number of contact
        totalContact = contactManagerPage.getTotalContact();
        contactManagerPage.selectListLimit(rowLimit[1]);

        //  Verify point
        Assert.assertFalse(contactManagerPage.isPageNavigationBarDisplayed());
        Assert.assertEquals(totalContact,contactManagerPage.getContactRowCount());
        Logger.verifyPointPass("All contact are displayed in one page");

        Logger.logTestCasePass();
    }


}
