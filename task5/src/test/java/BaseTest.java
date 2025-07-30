import constants.PathConstants;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import models.resourcesModels.ConfigData;
import models.resourcesModels.TestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.JsonToObjectUtil;
import utils.apiUtils.RequestUtil;

import java.io.IOException;

public abstract class BaseTest {
    ConfigData configData = JsonToObjectUtil.getObjectFromFile(PathConstants.PATH_TO_CONFIG, ConfigData.class);
    TestData testData = JsonToObjectUtil.getObjectFromFile(PathConstants.PATH_TO_TEST_DATA, TestData.class);

    @BeforeMethod()
    protected void setUp() {
        Browser browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo(configData.getUrl());
        browser.waitForPageToLoad();
    }

    @AfterMethod()
    public void tearDown() throws IOException {
        AqualityServices.getBrowser().quit();
        RequestUtil.close();
    }
}
