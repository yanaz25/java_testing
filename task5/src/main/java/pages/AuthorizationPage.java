package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AuthorizationPage extends Form {
    private ITextBox loginTextBox = getElementFactory().getTextBox(By.id("index_email"), "login");
    private IButton submitButton = getElementFactory().getButton(By.xpath("//button[@type='submit']"),
            "enter");

    public AuthorizationPage() {
        super(By.id("index_login"), "authorization page");
    }

    public void enterLogin(String login) {
        loginTextBox.type(login);
    }

    public void clickSubmitButton() {
        submitButton.click();
    }
}
