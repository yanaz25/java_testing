import constants.PathConstants;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import model.resourcesModels.ConfigModel;
import model.resourcesModels.TestDataModel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.bdUtils.ConnectionUtil;
import utils.fileUtils.JsonToObjectUtil;
import utils.apiUtils.RequestUtil;

import java.io.IOException;

public abstract class BaseTest {
    protected ConfigModel configData = JsonToObjectUtil.getObjectFromFile(PathConstants.PATH_TO_CONFIG,
            ConfigModel.class);
    protected TestDataModel testData = JsonToObjectUtil.getObjectFromFile(PathConstants.PATH_TO_TEST_DATA,
            TestDataModel.class);
    protected String url = String.format(configData.getUrl(), configData.getUser(), configData.getPassword());

    @BeforeMethod
    protected void setUp() {
        Browser browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo(url);
        browser.waitForPageToLoad();
        ConnectionUtil.getConnection();
    }

    @AfterMethod
    public void tearDown() throws IOException {
        AqualityServices.getBrowser().quit();
        RequestUtil.close();
        ConnectionUtil.closeConnection();
    }
}
