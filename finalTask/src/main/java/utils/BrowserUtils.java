package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.openqa.selenium.Cookie;

public class BrowserUtils {
    private static Browser browser = AqualityServices.getBrowser();
    private static String cookiePath = "/";

    public static void goBack() {
        browser.goBack();
    }

    public static byte[] getScreenshot() {
        return browser.getScreenshot();
    }

    public static void sendCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value, cookiePath);
        browser.getDriver().manage().addCookie(cookie);
    }

    public static void refreshPage() {
        browser.refresh();
    }

    public static void switchToFrame(int index) {
        browser.getDriver().switchTo().frame(index);
    }

    public static void switchToDefault() {
        browser.getDriver().switchTo().defaultContent();
    }

    public static void executeScript(String name) {
        browser.executeScript(name);
    }
}
