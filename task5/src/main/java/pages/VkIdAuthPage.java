package pages;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class VkIdAuthPage extends Form {
    private ITextBox passwordTextBox = getElementFactory().getTextBox(By.name("password"), "password",
            ElementState.DISPLAYED);
    private IButton continueButton = getElementFactory().getButton(By.className("vkuiButton__in"), "continue");

    public VkIdAuthPage() {
        super(By.name("password"), "vk id authorization page");
    }

    public void enterPassword(String password) {
        passwordTextBox.type(password);
    }

    public void clickContinue() {
        continueButton.click();
    }
}
