package helper;

public class NewWindowHelper {
    public static boolean verifyPopupWindowURL(String URL) {
        boolean result = false;
        //Store the main window
        String mainWindow = DriverHelper.getWebDriver().getWindowHandle();

        // Get all Opened Window
        for (String windowHandle : DriverHelper.getWebDriver().getWindowHandles()) {

            if (!windowHandle.equals(mainWindow)) {
                DriverHelper.getWebDriver().switchTo().window(windowHandle);
                if (DriverHelper.getWebDriver().getCurrentUrl().equalsIgnoreCase(URL)) {
                    result = true;
                }
                DriverHelper.getWebDriver().close();
            }
            DriverHelper.getWebDriver().switchTo().window(mainWindow);
        }
        return result;
    }
}
