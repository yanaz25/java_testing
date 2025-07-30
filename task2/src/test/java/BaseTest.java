import constants.TestConstants;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    @BeforeMethod
    protected void beforeMethod() {
        Browser browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo(TestConstants.DEFAULT_URL);
        browser.waitForPageToLoad();
    }

    @AfterMethod
    public void afterTest() {
        AqualityServices.getBrowser().quit();
    }
}