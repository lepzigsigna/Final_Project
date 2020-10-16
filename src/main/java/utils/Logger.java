package utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

public class Logger {
    public static org.apache.log4j.Logger logger = LogManager.getLogger("Test");

    public Logger() {
        DOMConfigurator.configure("log4j.xml");
    }

    public static void info(String infoMsg) {
        logger.info(infoMsg);
    }

    public static void testCaseHeader(String testCaseNo) {
        logger.info("***************************************************************************");
        logger.info("------------------------------ Test case " + testCaseNo + " -----------------------------");
    }

    public static void testCaseDescription(String Description) {
        logger.info("DESCRIPTION: " + Description);
    }

    public static void testCaseStep(String stepCount, String stepDescription) {
        logger.info("[STEP " + stepCount + "]: " + stepDescription);
    }

    public static void logTestCasePass() {
        logger.info("------------------------------ Test Result --------------------------------");
        logger.info("----------------TEST CASE PASSED! All actual results are correct!----------");
    }

    public static void logTestCaseFail() {
        logger.info("------------------------------ Test Result --------------------------------");
        logger.info("------TEST CASE FAILED! Actual result(s) does not match expected ones!-----");
    }

    public static void verifyPointPass(String passMsg) {
        logger.info("VERIFY POINT PASSED: " + passMsg);
    }

}
