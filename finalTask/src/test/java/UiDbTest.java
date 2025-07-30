import static aquality.selenium.browser.AqualityServices.getLogger;
import constants.ContentTypeConstants;
import constants.PathConstants;
import model.AttachmentModel;
import model.LogModel;
import model.TestModel;
import modelCrud.*;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import pages.ProjectPage;
import pages.ProjectsPage;
import pages.TestPage;
import utils.*;
import utils.apiUtils.ApiUtil;
import utils.bdUtils.BlobUtil;
import utils.fileUtils.FileReaderUtil;
import utils.fileUtils.ImageUtil;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

public class UiDbTest extends BaseTest {
    private int boundForExceptionMark = 2;

    @Test
    public void uiDbTest(ITestContext testContext) {
        getLogger().info("1. Запросом к апи получить токен cогласно номеру варианта " + testData.getVariant());
        ApiUtil apiUtil = new ApiUtil();
        String token = apiUtil.getToken();
        assertFalse(token.isEmpty(), "token isn't generated");

        getLogger().info("2.1. Перейти на сайт. Пройти необходимую авторизацию.");
        ProjectsPage projectsPage = new ProjectsPage();
        assertTrue(projectsPage.state().waitForDisplayed(), projectsPage.getName() + " isn't displayed");

        getLogger().info("2.2. С помощью cookie передать сгенерированный на шаге 1 токен. Обновить страницу.");
        BrowserUtils.sendCookie(configData.getCookieName(), token);
        BrowserUtils.refreshPage();
        assertEquals(projectsPage.getVersion(), testData.getVariant(), "wrong variant");

        getLogger().info("3.1. Перейти на страницу проекта Nexage.");
        projectsPage.clickProject(testData.getProject());
        ProjectPage projectPage = new ProjectPage();
        assertTrue(projectPage.state().waitForDisplayed(), projectPage.getName() + " isn't displayed");

        List<TestModel> allTestsFromUI = projectPage.getTests();
        List<TestModel> sortedAllTestsFromUI = allTestsFromUI.stream().sorted().toList();
        assertEquals(allTestsFromUI, sortedAllTestsFromUI, "tests are not sorted");

        getLogger().info("3.2. Запросом к БД получить список тестов проекта.");
        TestCrud testCrud = new TestCrud();
        ProjectCrud projectCrud = new ProjectCrud();
        int projectId = projectCrud.selectByName(testData.getProject()).getId();
        List<TestModel> allTestsFromBD = testCrud.getLatestTestsByProject(projectId,
                allTestsFromUI.size());
        assertTrue(TestModel.areEqualLists(allTestsFromUI, allTestsFromBD), "lists are not equal");

        getLogger().info("4.1. Вернуться на предыдущую страницу в браузере(страница проектов).");
        BrowserUtils.goBack();
        assertTrue(projectsPage.state().waitForDisplayed(), projectsPage.getName() + " isn't displayed");

        getLogger().info("4.2. Нажать на +Add. Ввести название проекта, и сохранить.");
        projectsPage.clickAddProject();
        String projectName = RandomUtils.generateRandomString(testData.getTextLength());
        projectsPage.typeProjectName(projectName);
        projectsPage.clickSaveProject();
        assertEquals(projectsPage.getAlertText(), String.format(testData.getSaveProjectText(), projectName),
                "project isn't saved");

        getLogger().info("4.3. Закрыть алерт с информацией о сохранённом проекте. " +
                "Для закрытия окна добавления проекта вызвать js-метод closePopUp(). Обновить страницу");
        projectsPage.closeAddProject();
        BrowserUtils.refreshPage();
        assertTrue(projectsPage.isAddProjectClosed(), "alert isn't closed");
        assertTrue(projectsPage.getProjectsName().contains(projectName), "the new project didn't appear");

        getLogger().info("5.1. Перейти на страницу созданного проекта.");
        projectsPage.clickProject(projectName);
        byte[] screenshot = BrowserUtils.getScreenshot();

        getLogger().info("5.2. Добавить тест через БД.");
        TestModel testModel = TestInfoUtil.createTest(testContext, projectName);
        testCrud.insertRow(testModel);
        assertTrue(projectPage.isTestDisplayed(testModel.getName()));

        getLogger().info("5.3. Добавить скриншот страницы.");
        AttachmentModel screenAttachment = new AttachmentModel(testModel.getId());
        AttachmentCrud attachmentCrud = new AttachmentCrud();
        screenAttachment.setContentType(ContentTypeConstants.IMAGE_PNG_TYPE);
        screenAttachment.setContent(BlobUtil.getBlobFromBytes(screenshot));
        attachmentCrud.insert(screenAttachment);

        getLogger().info("5.4. Добавить лог теста.");
        LogModel logModel = new LogModel(testModel.getId());
        LogCrud logCrud = new LogCrud();
        String testLog = FileReaderUtil.readFile(PathConstants.PATH_TO_LOG);
        logModel.setContent(testLog);
        logModel.setIsException(RandomUtils.getRandomValue(0, boundForExceptionMark));
        logCrud.insert(logModel);

        getLogger().info("5. Перейти на страницу созданного теста.");
        projectPage.clickTestByName(testModel.getName());
        TestPage testPage = new TestPage();
        TestModel testFromUI = testPage.getTest();
        assertTrue(testModel.areEqualTests(testFromUI));

        String logFromUI = testPage.getLogText();
        assertEquals(testLog, logFromUI);
        byte[] screenshotFromUI = ImageUtil.getScreenshotBytes(testPage.getScreenshotUrl());
        assertTrue(ImageUtil.areImagesEqual(screenshot, screenshotFromUI), "images are not equal");
    }
}
