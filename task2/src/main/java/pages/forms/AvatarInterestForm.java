package pages.forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import constants.Attributes;
import org.openqa.selenium.By;
import actions.Keyboard;
import utilities.RandomUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AvatarInterestForm extends Form {
    private IButton nextFormButton = getElementFactory().getButton(By.xpath("//button[contains(., 'Next')]"), "next form");
    private ITextBox uploadButton = getElementFactory().getTextBox(By.xpath("//*[contains(@class, '_upload-button')]"), "upload image");
    private IElement unselectAllCheckBox = getElementFactory().getCheckBox(By.xpath("//*[@for = 'interest_unselectall']"), "unselect all");
    private List<IElement> interestCheckBoxes = getElementFactory().findElements(By.xpath("//*[contains(@for,'interest')]"), "interest checkbox", ElementType.CHECKBOX);
    private String selectAllAttributeFor = "interest_selectall";
    private String unselectAllAttributeFor = "interest_unselectall";

    public AvatarInterestForm() {
        super(By.xpath("//*[@class='avatar-and-interests__form']"), "Avatar and interests form");
    }

    public void clickNext() {
        nextFormButton.click();
    }

    public void uploadFile(String filePath) {
        uploadButton.click();
        Keyboard.copyPath(filePath);
        Keyboard.pastePath();
    }

    public void selectCheckBoxesByCount(int count) {
        unselectAllCheckBoxes();
        Set<Integer> indexes = new HashSet<>();
        while (indexes.size() < count) {
            int value = RandomUtils.getRandomValue(interestCheckBoxes.size());
            String interestValue = interestCheckBoxes.get(value).getAttribute(Attributes.ATTRIBUTE_FOR);
            if (!interestValue.equals(selectAllAttributeFor) && !interestValue.equals(unselectAllAttributeFor)) {
                indexes.add(value);
            }
        }
        for (int id : indexes) {
            interestCheckBoxes.get(id).click();
        }
    }

    public void unselectAllCheckBoxes() {
        unselectAllCheckBox.click();
    }
}