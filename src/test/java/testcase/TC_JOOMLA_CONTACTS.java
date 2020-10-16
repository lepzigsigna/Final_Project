package testcase;

import helper.DataHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject.contact.page.ContactContactPage;
import pageobject.contact.page.ContactManagerPage;
import pageobject.other.page.LoginPage;
import pageobject.other.page.MainPage;
import utils.Constant;
import utils.Logger;

public class TC_JOOMLA_CONTACTS extends BaseTest{

    private LoginPage loginPage = new LoginPage();
    private MainPage mainPage = new MainPage();
    private ContactManagerPage contactManagerPage = new ContactManagerPage();
    private ContactContactPage contactContactPage = new ContactContactPage();

    @BeforeMethod
    public void loginTheSystem() {
        Logger.testCaseStep("2 - 4", "Login the system");
        loginPage.login(Constant.VALID_USERNAME, Constant.VALID_PASSWORD);
    }

    @Test (testName = "TC_JOOMLA_CONTACTS_005")
    public void TC_JOOMLA_CONTACTS_005()   {
        Logger.testCaseHeader("TC_JOOMLA_CONTACTS_005");
        Logger.testCaseDescription("Verify user can move a contact to the archive");
        //  Test Data
        String contactName = DataHelper.generateName();
        String contactCategory = "Sample Data-Contact";
        String contactStatus = "Published";

        //  Steps
        Logger.testCaseStep("5", "Select Components > Contacts");
        mainPage.clickSubMenuItem(Constant.menuItem.Components, Constant.subMenuItem.Contacts);

        Logger.testCaseStep("6", "Click on 'New' icon of the top right toolbar");
        contactManagerPage.clickNewBtn();

        Logger.testCaseStep("7, 8, 9, 10", "Create a new contact");
        contactContactPage.createContact(contactName,contactCategory,contactStatus);

        //  Verify point
        Assert.assertEquals(contactManagerPage.getSuccessMsg(), Constant.CONTACT_SAVED_SUCCESS_MESS,"The successful message is not correct");
        Assert.assertTrue(contactManagerPage.isContactRowDisplayed(contactName), "The new Contact is not displayed in the table");
        Logger.verifyPointPass("Correct message and the contact present");


        Logger.testCaseStep("12, 13", "Archive a newly created contact");
        contactManagerPage.archiveNewContact(contactName);

        //  Verify point
        Assert.assertEquals(contactManagerPage.getSuccessMsg(), Constant.CONTACT__ARCHIVED_SUCCESS_MESS, "The successful message is not correct");
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
        mainPage.clickSubMenuItem(Constant.menuItem.Components, Constant.subMenuItem.Contacts);

        Logger.testCaseStep("5","Select item '5' of the 'Display' dropdown list");
        contactManagerPage.selectListLimit(rowLimit[0]);

        //  Verify point
        Assert.assertEquals(String.valueOf(contactManagerPage.getContactRowCount()), rowLimit[0],
                "The total in the table is not " + rowLimit[0]);
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
