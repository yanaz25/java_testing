import model.*;
import modelCrud.AuthorCrud;
import modelCrud.ProjectCrud;
import modelCrud.TestCrud;
import constants.PathConstants;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.*;

import java.util.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UpdateTests {
    private TestDataModel testData = JsonToObjectUtil.getObjectFromFile(PathConstants.PATH_TO_TEST_DATA,
            TestDataModel.class);
    private ConfigModel configData = JsonToObjectUtil.getObjectFromFile(PathConstants.PATH_TO_CONFIG,
            ConfigModel.class);
    private int startIndex;
    private int currentIndex;
    private int endIndex;
    private int testsCount;
    AuthorCrud authorCrud = new AuthorCrud();
    ProjectCrud projectCrud = new ProjectCrud();
    TestCrud testCrud = new TestCrud();;

    @BeforeClass
    public void setUp() {
        if (authorCrud.selectByLogin(configData.getAuthor().getLogin()) == null) {
            AuthorModel authorModel = configData.getAuthor();
            authorCrud.insertRow(authorModel);
        }
        if (projectCrud.selectByName(configData.getProject().getName()) == null) {
            ProjectModel projectModel = configData.getProject();
            projectCrud.insertRows(projectModel);
        }
    }

    @BeforeClass()
    public void copyTests() {
        testsCount = RandomUtil.getRandomValue(testData.getRandomValueOrigin(), testData.getMaxNumOfTests() + 1);
        Set<Integer> indexes = new HashSet<>();

        while (indexes.size() < testsCount) {
            indexes.add(RandomUtil.generateIdTwoSameNum(testData.getRandomValueOrigin(), testCrud.getRowsCount()));
        }

        startIndex = testCrud.getRowsCount() + 1;
        currentIndex = startIndex;

        for (int id : indexes) {
            TestModel testModel = testCrud.selectById(id);
            testModel.setId(testCrud.getRowsCount() + 1);

            testModel.setProjectId(projectCrud.selectByName(configData.getProject().getName()).getId());
            testModel.setAuthorId(authorCrud.selectByLogin(configData.getAuthor().getLogin()).getId());
            testCrud.insertRow(testModel);
        }
        endIndex = testCrud.getRowsCount();
    }

    @DataProvider(name = "dataForUpdate")
    public Object[][] dataForUpdate() {
        Object[][] data = new Object[testsCount][1];
        for (int i = 0; i < testsCount; i++) {
            data[i][0] = 1;
        }
        return data;
    }

    @Test(dataProvider = "dataForUpdate")
    public void unevenNumberTest(int count) {
        int value = RandomUtil.getRandomValue(testData.getRandomValueOrigin(), testData.getRandomValueBound());
        assertTrue(value % 2 != 0, "Число четное!");
    }

    @AfterMethod()
    public void updateTestsInfo(ITestResult result) {
        TestModel testModel = TestInfoUtil.createTest(result);
        testModel.setId(currentIndex);
        testCrud.updateRow(testModel);

        TestModel testModel2 = testCrud.selectById(currentIndex);
        assertEquals(testModel, testModel2);
    }

    @AfterClass()
    public void tearDown() {
        testCrud.deleteRowsBetweenId(startIndex, endIndex);
        ConnectionUtils.closeConnection();
    }
}
