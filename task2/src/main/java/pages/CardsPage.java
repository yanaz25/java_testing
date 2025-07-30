package pages;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CardsPage extends Form {
    private ILabel formIndicatorLabel = getElementFactory().getLabel(By.className("page-indicator"), "form indicator");
    private IButton hideHelpFormButton = getElementFactory().getButton(By.xpath("//button[contains(@class, 'help-form__send-to-bottom')]"), "hide help form", ElementState.DISPLAYED);
    private ILabel helpFormLabel = getElementFactory().getLabel(By.className("help-form__title"), "help form title");
    private IButton acceptCookieButton = getElementFactory().getButton(By.xpath("//button[contains(., 'Not')]"), "accept cookie", ElementState.DISPLAYED);
    private ILabel timerLabel = getElementFactory().getLabel(By.className("timer"), "timer");

    public CardsPage() {
        super(By.className("pagination--center"), "Cards Page");
    }

    public char getFormIndicator() {
        return formIndicatorLabel.getText().charAt(0);
    }

    public void hideHelpForm() {
        hideHelpFormButton.click();
    }

    public boolean isHelpFormNotDisplayed() {
        return helpFormLabel.state().waitForNotDisplayed();
    }

    public void acceptCookie() {
        acceptCookieButton.click();
    }

    public boolean isCookieNotDisplayed() {
        return acceptCookieButton.state().waitForNotDisplayed();
    }

    public String getTimerValue() {
        return timerLabel.getText();
    }
}
