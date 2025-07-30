package utils;

import model.TestModel;
import model.resourcesModels.TestDataModel;
import modelCrud.*;
import constants.PathConstants;
import model.resourcesModels.ConfigModel;
import org.testng.ITestContext;
import utils.fileUtils.JsonToObjectUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestInfoUtil {
    private static ConfigModel configData = JsonToObjectUtil.getObjectFromFile(PathConstants.PATH_TO_CONFIG,
            ConfigModel.class);

    private static TestDataModel testData = JsonToObjectUtil.getObjectFromFile(PathConstants.PATH_TO_TEST_DATA,
            TestDataModel.class);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat(configData.getDateFormat());
    private static long minMillis = 60000;
    private static int statusIdOrigin = 1;
    private static int statusIdBound = 4;

    public static TestModel createTest(ITestContext testContext, String project) {
        TestModel testModel = new TestModel();
        AuthorCrud authorCrud = new AuthorCrud();
        ProjectCrud projectCrud = new ProjectCrud();
        TestCrud testCrud = new TestCrud();

        testModel.setMethodName(testContext.getAllTestMethods()[0].getQualifiedName());
        testModel.setStartTime(dateFormat.format(testContext.getStartDate()));
        testModel.setEndTime(dateFormat.format(new Date(testContext.getStartDate().getTime() + minMillis)));
        testModel.setStatusId(RandomUtils.getRandomValue(statusIdOrigin, statusIdBound));
        testModel.setName(testContext.getCurrentXmlTest().getName());
        testModel.setProjectId(projectCrud.selectByName(project).getId());
        testModel.setSessionId(testData.getSessionId());
        testModel.setEnv(testData.getEnv());
        testModel.setBrowser(testData.getBrowser());
        if (authorCrud.selectByLogin(testData.getAuthor().getLogin()) == null) {
            authorCrud.insertRow(testData.getAuthor());
        }
        testModel.setAuthorId(authorCrud.selectByLogin(testData.getAuthor().getLogin()).getId());
        testModel.setId(testCrud.getRowsCount() + 1);
        return testModel;
    }
}
