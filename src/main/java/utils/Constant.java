package utils;

public class Constant {
    /**
     * ENUMS FOR MENU NAV BAR
     */
    public enum menuItem {
        Content, Components;
    }

    public enum subMenuItem {
        Articles, Categories, Banners, Contacts;
    }

    public enum bannerSubItem {
        Banners, Categories, Clients;
    }

    /**
     * Browser param to run parallel test
     */
    public static final String BROWSER_CHROME = "Chrome";
    public static final String BROWSER_FIRE_FOX = "Fire-fox";
    public static final String BROWSER_EDGE = "Edge";

    /**
     * Time out constant
     */
    public static final int PAGE_LOAD_TIME_OUT = 30;
    public static final int IMPLICIT_WAIT = 5;
    public static final int EXPLICIT_WAIT = 5;

    /**
     *  ENUMS FOR MENU NAV BAR
     */


    /**
     * MAIN PAGE
     */
    public static final String BASE_URL = "http://13.82.211.128:8080/administrator/";

    /**
     * ARTICLE MANAGER PAGE
     */
    public static final String ARTICLE_SAVED_SUCCESS_MESS = "Article saved.";
    public static final String ARCHIVE_SUCCESSFULLY_MESS = "1 article archived.";
    public static final String ARTICLE_AUTHOR = "Duy";
    public static final String ARTICLE_CATEGORY_SAVED_SUCCESS_MESS = "Category saved.";

    /**
     * CONTACT MANAGER PAGE
     */
    public static final String CONTACT_SAVED_SUCCESS_MESS = "Contact saved.";
    public static final String CONTACT__ARCHIVED_SUCCESS_MESS = "1 contact archived.";

    /**
     * BANNER MANAGER PAGE
     */
    public static final String CLIENT_SAVE_SUCCESS_MESS = "Client saved.";
    public static final String BANNER_CATEGORY_SAVE_SUCCESS_MESS = "Category saved.";
    public static final String BANNER_SAVE_SUCCESS_MESS = "Banner saved.";
    public static final String BANNER_ARCHIVED_SUCCESS_MESS = "1 banner archived.";

    /**
     * NEW BANNER PAGE
     */
    public static final String BANNER_HELP_PAGE_URL = "https://help.joomla.org/proxy?keyref=Help39:Components_Banners_Banners_Edit&lang=en";

    /**
     * NEW CLIENT PAGE
     */
    public static final String INVALID_FIELD_EMAIL_MESS = "Invalid field:  Contact Email";

    /**
     * NEW ARTICLE CATEGORY PAGE
     */
    public static final String ARTICLE_CAT_SAVE_SUCCESS_MESS = "Category saved.";

    /**
     * WEBLINK MANAGER PAGE
     */
    public static final String WEBLINK_SAVE_SUCCESS_MESS = "Weblink saved.";

    /**
     * Valid Account
     */
    public static final String VALID_USERNAME = "duy";
    public static final String VALID_PASSWORD = "duy123";


}
