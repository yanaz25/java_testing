package pages;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import model.TestModel;
import org.openqa.selenium.By;
import pages.util.DateUtil;
import pages.util.TestStatusUtil;

import java.util.ArrayList;
import java.util.List;

public class ProjectPage extends Form {
    private List<IElement> allTests;
    private String testNameMask = "//*[text()='%s']";
    private String testFieldMask = "/*[%d]";
    private By testListLocator = By.xpath("//*[@class='table']//tr");
    private int nameFieldIndex = 1;
    private int methodNameFieldIndex = 2;
    private int statusFieldIndex = 3;
    private int startTimeIndex = 4;
    private int endTimeIndex = 5;

    public ProjectPage() {
        super(By.id("pie"), "project");
    }

    public List<TestModel> getTests() {
        allTests = getElementFactory().findElements(testListLocator, ElementType.LABEL);
        List<TestModel> tests = new ArrayList<>();
        allTests.remove(0);
        for (IElement element : allTests) {
            tests.add(getTest(element));
        }
        return tests;
    }

    public boolean isTestDisplayed(String testName) {
        return getElementFactory().getLabel(By.xpath(String.format(testNameMask, testName)), "test")
                .state().waitForDisplayed();
    }

    public void clickTestByName(String testName) {
        getElementFactory().getLabel(By.xpath(String.format(testNameMask, testName)), "test").click();
    }

    private TestModel getTest(IElement element) {
        TestModel testModel = new TestModel();
        testModel.setName(getFieldText(element, nameFieldIndex));
        testModel.setMethodName(getFieldText(element, methodNameFieldIndex));
        testModel.setStatusId(TestStatusUtil.getStatusId(getFieldText(element, statusFieldIndex)));
        testModel.setStartTime(DateUtil.parseDate(getFieldText(element, startTimeIndex)));
        testModel.setEndTime(DateUtil.parseDate(getFieldText(element, endTimeIndex)));
        return testModel;
    }

    private String getFieldText(IElement element, int index) {
        By fieldLocator = By.xpath(String.format(testFieldMask, index));
        return element.findChildElement(fieldLocator, ElementType.LABEL).getText();
    }
}
