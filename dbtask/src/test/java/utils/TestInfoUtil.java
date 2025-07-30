package utils;

import model.TestModel;
import modelCrud.*;
import constants.PathConstants;
import model.ConfigModel;
import org.testng.ITestResult;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class TestInfoUtil {
    private static ConfigModel configData = JsonToObjectUtil.getObjectFromFile(PathConstants.PATH_TO_CONFIG,
            ConfigModel.class);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat(configData.getDateFormat());

    public static TestModel createTest(ITestResult result) {
        TestModel testModel = new TestModel();
        AuthorCrud authorCrud = new AuthorCrud();
        ProjectCrud projectCrud = new ProjectCrud();
        TestCrud testCrud = new TestCrud();

        testModel.setMethodName(result.getMethod().getQualifiedName());
        testModel.setStartTime(dateFormat.format(new Date(result.getStartMillis())));
        testModel.setEndTime(dateFormat.format(new Date(result.getEndMillis())));
        testModel.setStatusId(StatusInfoUtil.getStatusId(result));
        testModel.setName(result.getTestContext().getCurrentXmlTest().getName());
        testModel.setProjectId(projectCrud.selectByName(configData.getProject().getName()).getId());
        testModel.setSessionId(configData.getSessionId());
        testModel.setEnv(configData.getEnv());
        testModel.setBrowser(configData.getBrowser());
        testModel.setAuthorId(authorCrud.selectByLogin(configData.getAuthor().getLogin()).getId());
        testModel.setId(testCrud.getRowsCount() + 1);
        return testModel;
    }
}
