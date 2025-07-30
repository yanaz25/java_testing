package pages;

import aquality.selenium.elements.interfaces.ILink;
import org.openqa.selenium.By;
import aquality.selenium.forms.Form;

public class HomePage extends Form {
    private ILink nextPageLink = getElementFactory().getLink(By.className("start__link"), "to the next page");

    public HomePage() {
        super(By.className("start__button"), "Home page");
    }

    public void clickNextPageLink() {
        nextPageLink.click();
    }
}
