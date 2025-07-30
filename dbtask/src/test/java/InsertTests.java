import constants.PathConstants;
import model.*;
import modelCrud.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ConnectionUtils;
import utils.JsonToObjectUtil;
import utils.RandomUtil;
import utils.TestInfoUtil;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InsertTests {
    private ConfigModel configData = JsonToObjectUtil.getObjectFromFile(PathConstants.PATH_TO_CONFIG,
            ConfigModel.class);
    private TestDataModel testData = JsonToObjectUtil.getObjectFromFile(PathConstants.PATH_TO_TEST_DATA,
            TestDataModel.class);
    AuthorCrud authorCrud;
    ProjectCrud projectCrud;
    TestCrud testCrud;

    @BeforeClass
    public void setUp() {
        testCrud = new TestCrud();
        authorCrud = new AuthorCrud();
        projectCrud = new ProjectCrud();

        if (authorCrud.selectByLogin(configData.getAuthor().getLogin()) == null) {
            AuthorModel authorModel = configData.getAuthor();
            authorCrud.insertRow(authorModel);
        }
        if (projectCrud.selectByName(configData.getProject().getName()) == null) {
            ProjectModel projectModel = configData.getProject();
            projectCrud.insertRows(projectModel);
        }
    }

    @Test()
    public void evenNumberTest() {
        int value = RandomUtil.getRandomValue(testData.getRandomValueOrigin(), testData.getRandomValueBound());
        assertTrue(value % 2 == 0, "Число нечетное!");
    }

    @AfterMethod()
    public void insertTestInfo(ITestResult result) {
        TestModel myTestModel = TestInfoUtil.createTest(result);
        testCrud.insertRow(myTestModel);
        TestModel testModelFromDB = testCrud.selectById(testCrud.getRowsCount());
        assertEquals(myTestModel, testModelFromDB);
    }

    @AfterClass()
    public void tearDown() {
        ConnectionUtils.closeConnection();
    }
}
