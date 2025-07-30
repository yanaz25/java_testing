package pages.forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utilities.RandomUtils;

import java.util.List;

public class RegistrationForm extends Form {
    private ITextBox emailTextBox = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Your email']"), "email");
    private ITextBox domainTextBox = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Domain']"), "domain");
    private ITextBox passwordTextBox = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Choose Password']"), "password");
    private IButton openDomainListButton = getElementFactory().getButton(By.className("dropdown__field"), "open domain list");
    private ICheckBox acceptTermsCheckBox = getElementFactory().getCheckBox(By.className("checkbox__box"), "accept terms");
    private IButton nextFormButton = getElementFactory().getButton(By.xpath("//*[@class = 'button--secondary']"), "next form");
    private List<IButton> domainChoiceList;

    public RegistrationForm() {
        super(By.className("login-form"), "Registration Page");
    }

    public void typeEmail(String email) {
        emailTextBox.clearAndType(email);
    }

    public void typeDomain(String domain) {
        domainTextBox.clearAndType(domain);
    }

    public void typePassword(String password) {
        passwordTextBox.clearAndType(password);
    }

    public void clickAcceptTermsCheckbox() {
        acceptTermsCheckBox.click();
    }

    public void clickNext() {
        nextFormButton.click();
    }

    public void setDomain() {
        //click с помощью js, чтобы в jenkins тест не падал
        openDomainListButton.getJsActions().click();
        domainChoiceList = getElementFactory().findElements(By.xpath("//*[@class='dropdown__list-item']"), "domain choice", ElementType.BUTTON);
        domainChoiceList.get(RandomUtils.getRandomValue(domainChoiceList.size())).getJsActions().click();
    }
}
