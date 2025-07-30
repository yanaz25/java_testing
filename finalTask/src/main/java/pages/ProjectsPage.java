package pages;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.*;
import utils.BrowserUtils;

import java.util.ArrayList;
import java.util.List;

public class ProjectsPage extends Form {
    private ILabel footer = getElementFactory().getLabel(By.className("footer"), "version");
    private IButton addProjectButton = getElementFactory().getButton(
            By.xpath("//*[@data-target='#addProject']"), "add project");
    private ITextBox newProjectName = getElementFactory().getTextBox(By.xpath("//*[@id='projectName']"),
            "project name", ElementState.DISPLAYED);
    private IButton saveProjectButton = getElementFactory().getButton(By.xpath("//*[@type='submit']"),
            "save project");
    private ILabel text = getElementFactory().getLabel(By.className("alert-success"), "success alert");
    private String allProjectsClassName = "list-group-item";
    private List<ILabel> allProjectsNames;
    private String projectLocatorMask = "//*[@class='list-group-item' and text()='%s']";
    private String numRegex = "\\D+";
    private int frameIndex = 0;
    private String closePopUpScript = "closePopUp();";

    public ProjectsPage() {
        super(By.xpath("//*[@data-target='#addProject']"), "projects page");
    }

    public int getVersion() {
        String footerText = footer.getText();
        return Integer.parseInt(footerText.replaceAll(numRegex, ""));
    }

    public void clickProject(String project) {
        IButton projectButton = getElementFactory().getButton(By.xpath(String.format(projectLocatorMask, project)),
                project);
        projectButton.click();
    }

    public void clickAddProject() {
        addProjectButton.click();
    }

    public void typeProjectName(String projectName) {
        BrowserUtils.switchToFrame(frameIndex);
        newProjectName.sendKeys(projectName);
    }

    public void clickSaveProject() {
        saveProjectButton.click();
    }

    public void closeAddProject() {
        BrowserUtils.switchToDefault();
        BrowserUtils.executeScript(closePopUpScript);
    }

    public boolean isAddProjectClosed() {
        BrowserUtils.switchToFrame(frameIndex);
        boolean isClosed = newProjectName.state().waitForNotDisplayed();
        BrowserUtils.switchToDefault();
        return isClosed;
    }

    public String getAlertText() {
        text.state().waitForDisplayed();
        return text.getText();
    }

    public List<String> getProjectsName() {
        allProjectsNames = getElementFactory().findElements(By.className(allProjectsClassName), ElementType.LABEL);
        List<String> projectNames = new ArrayList<>();
        for (ILabel project : allProjectsNames) {
            projectNames.add(project.getText());
        }
        return projectNames;
    }
}
