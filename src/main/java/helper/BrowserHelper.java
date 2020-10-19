package helper;

import pageobject.other.page.BasePage;

import java.util.Set;

public class BrowserHelper {
    private static BasePage basePage = new BasePage();

    public static int getNumOfActiveWindow() {
        Set<String> winHandles = DriverHelper.getWebDriver().getWindowHandles();
        return winHandles.size();
    }

    public static String getPopupWindowURL() {
        String URL = "";
        //Store the main window
        String mainWindow = DriverHelper.getWebDriver().getWindowHandle();

        // Get all Opened Window
        for (String windowHandle : DriverHelper.getWebDriver().getWindowHandles()) {

            if (!windowHandle.equals(mainWindow)) {
                DriverHelper.getWebDriver().switchTo().window(windowHandle);
                basePage.waitPopUpPageHeader();
                URL = DriverHelper.getCurrentURL();
                DriverHelper.getWebDriver().close();
            }
            DriverHelper.getWebDriver().switchTo().window(mainWindow);
        }
        return URL;
    }


}
