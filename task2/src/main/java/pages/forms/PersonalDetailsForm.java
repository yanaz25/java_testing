package pages.forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PersonalDetailsForm extends Form {
    public PersonalDetailsForm() {
        super(By.className("personal-details"), "personal details");
    }
}
