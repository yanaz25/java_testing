package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import constants.PathConstants;
import models.resourcesModels.ConfigData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.JsonToObjectUtil;

import java.time.Duration;

public class MyProfilePage extends Form {
    ConfigData configData = JsonToObjectUtil.getObjectFromFile(PathConstants.PATH_TO_CONFIG, ConfigData.class);
    private Duration wait = Duration.ofSeconds(configData.getWait());
    private String postTextMask = "//*[contains(@id,'%s')]/*[@data-testid='wall_post_text']";
    private String postAuthorMask = "//*[contains(@data-post-id,'%s')]//*[@class='author']";
    private String postMask = "//*[contains(@data-post-id,'%s') and contains(@class, 'post')]";
    private String commentAuthorMask = "//*[contains(@id,'%s')]//*[@class='reply_author']//*[contains(@class,'author')]";
    private String commentMessageMask = "//*[contains(@id,'%s')]//*[@class='reply_text']";
    private String likeMask = "//*[contains(@data-reaction-target-object, '%s')]";
    private ILabel showNextComments = getElementFactory().getLabel(By.className("replies_next_main"),
            "next comments", ElementState.DISPLAYED);
    private String commentAuthorAttribute = "data-from-id";

    public MyProfilePage() {
        super(By.id("react_rootprofile"), "my profile");
    }

    public String getPostTextById(int postId) {
        ILabel postText = getElementFactory().getLabel(By.xpath(String.format(postTextMask, postId)), "post text",
                ElementState.DISPLAYED);
        return postText.getText();
    }

    public boolean isTextEdited(int postId, String message) {
        By postTextLocator = By.xpath(String.format(postTextMask, postId));
        return AqualityServices.getConditionalWait().waitFor(ExpectedConditions.textToBe(postTextLocator, message));
    }

    public String getPostAuthorName(int postId) {
        ILabel post = getElementFactory().getLabel(By.xpath(String.format(postAuthorMask, postId)), "name");
        return post.getText();
    }

    public long getCommentAuthorId(int commentId) {
        By commentLocator = By.xpath(String.format(commentAuthorMask, commentId));
        if (!getElementFactory().getLabel(commentLocator, "comment").state().waitForDisplayed(wait)) {
            clickShowNextComments();
        }
        long authorId = Long.parseLong(getElementFactory().getLabel(commentLocator, "comment")
                .getAttribute(commentAuthorAttribute));
        return authorId;
    }

    public String getCommentMessage(int commentId) {
        By commentLocator = By.xpath(String.format(commentMessageMask, commentId));
        if (!getElementFactory().getLabel(commentLocator, "comment").state().waitForDisplayed(wait)) {
            clickShowNextComments();
        }
        return getElementFactory().getLabel(commentLocator, "comment").getText();
    }

    public void likePostById(int id) {
        getElementFactory().getButton(By.xpath(String.format(likeMask, id)), "like").click();
    }

    public boolean isPostDisplayed(int postId) {
        return getElementFactory().getLabel(By.xpath(String.format(postMask, postId)), "new post")
                .state().waitForDisplayed(wait);
    }

    public boolean isPostNotDisplayed(int postId) {
        return getElementFactory().getLabel(By.xpath(String.format(postMask, postId)), "new post")
                .state().waitForNotDisplayed();
    }

    public void clickShowNextComments() {
        showNextComments.click();
    }
}
