package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class FeedPage extends Form {
    private IButton myProfileButton = getElementFactory().getButton(By.id("l_pr"), "my page");

    public FeedPage() {
        super(By.id("feed_rmenu"), "feed page");
    }

    public void clickMyProfileButton() {
        myProfileButton.click();
    }
}
