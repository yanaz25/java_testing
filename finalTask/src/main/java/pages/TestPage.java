package pages;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import model.TestModel;
import modelCrud.AuthorCrud;
import modelCrud.ProjectCrud;
import org.openqa.selenium.By;
import pages.util.DateUtil;
import pages.util.TestStatusUtil;

public class TestPage extends Form {
    private ILabel logs = getElementFactory().getLabel(By.xpath(
            "//*[contains(.,'Logs')]/*[@class='table']"), "logs");
    private ILink screenshotLink = getElementFactory().getLink(By.xpath("//*[@class='table']//a"),
            "screenshot");
    private String infoItemMask = "//*[@class = 'list-group-item' and contains(., '%s')]" +
            "//*[@class='list-group-item-text']";
    private String projectItem = "Project name";
    private String testNameItem = "Test name";
    private String testMethodNameItem = "Test method name";
    private String statusItem = "Status";
    private String timeInfoItem = "Time info";
    private String environmentItem = "Environment";
    private String browserItem = "Browser";
    private String developerInfoItem = "Developer info";
    private int startTimeIndex = 1;
    private int endTimeIndex = 2;
    private int developerLoginIndex = 2;
    private int indexOffset = 1;
    private String indexMask = "[%d]";
    private String comaSymbol = ",";
    private String spaceSymbol = " ";

    public TestPage() {
        super(By.xpath("//*[contains(@class, 'fail-reason')]"), "test");
    }

    public TestModel getTest() {
        ProjectCrud projectCrud = new ProjectCrud();
        AuthorCrud authorCrud = new AuthorCrud();
        TestModel testModel = new TestModel();

        testModel.setProjectId(projectCrud.selectByName(getText(projectItem)).getId());
        testModel.setName(getText(testNameItem));
        testModel.setMethodName(getText(testMethodNameItem));
        testModel.setStatusId(TestStatusUtil.getStatusId(getText(statusItem)));
        testModel.setStartTime(DateUtil.parseDate(getTextByIndex(timeInfoItem, startTimeIndex)));
        testModel.setEndTime(DateUtil.parseDate(getTextByIndex(timeInfoItem, endTimeIndex)));
        testModel.setEnv(getText(environmentItem));
        testModel.setBrowser(getText(browserItem));
        String login = getDeveloperLogin(getTextByIndex(developerInfoItem, developerLoginIndex));
        testModel.setAuthorId(authorCrud.selectByLogin(login).getId());
        return testModel;
    }

    private String getText(String item) {
        return getElementFactory().getLabel(By.xpath(String.format(infoItemMask, item)), item).getText();
    }

    private String getTextByIndex(String item, int index) {
        return getElementFactory().getLabel(By.xpath(String.format(infoItemMask, item) +
                String.format(indexMask, index)), item).getText();
    }

    private String getDeveloperLogin(String text) {
        int startLoginIndex = text.indexOf(spaceSymbol) + indexOffset;
        return text.substring(startLoginIndex);
    }

    public String getLogText() {
        return logs.getText();
    }

    public String getScreenshotUrl() {
        String imageUrl = screenshotLink.getHref();
        int comaIndex = imageUrl.indexOf(comaSymbol) + indexOffset;
        return imageUrl.substring(comaIndex);
    }
}
