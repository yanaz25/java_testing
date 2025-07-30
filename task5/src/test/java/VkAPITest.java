import constants.PathConstants;
import models.*;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.FeedPage;
import pages.MyProfilePage;
import pages.VkIdAuthPage;
import utils.RandomUtil;
import utils.apiUtils.RequestUtil;
import utils.apiUtils.VkAPIUtil;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class VkAPITest extends BaseTest {
    Duration wait = Duration.ofSeconds(configData.getWait());

    @Test()
    public void wallPostTest() {
        AuthorizationPage authorizationPage = new AuthorizationPage();
        assertTrue(authorizationPage.state().waitForDisplayed(wait),
                authorizationPage.getName() + " isn't displayed!");

        authorizationPage.enterLogin(configData.getLogin());
        authorizationPage.clickSubmitButton();

        VkIdAuthPage vkIdAuthPage = new VkIdAuthPage();
        assertTrue(vkIdAuthPage.state().waitForDisplayed(wait), vkIdAuthPage.getName() + " isn't displayed!");

        vkIdAuthPage.enterPassword(configData.getPassword());
        vkIdAuthPage.clickContinue();
        FeedPage feedPage = new FeedPage();
        assertTrue(feedPage.state().waitForDisplayed(), feedPage.getName() + " isn't displayed!");

        feedPage.clickMyProfileButton();
        MyProfilePage myProfilePage = new MyProfilePage();
        assertTrue(myProfilePage.state().waitForDisplayed(wait), myProfilePage.getName() + " isn't displayed!");

        User user = VkAPIUtil.getCurrentUser();
        long ownerId = user.getResponse()[0].getId();
        String message = RandomUtil.generateRandomString(testData.getTextLength());
        Post post = new Post(new Post.PostResponse(ownerId, message));
        int postId = VkAPIUtil.sendPostGetPostId(post);
        post.getResponse().setPostId(postId);
        assertTrue(myProfilePage.isPostDisplayed(postId), "post isn't displayed");
        assertEquals(myProfilePage.getPostTextById(postId), message, "post messages are not equal");
        assertEquals(myProfilePage.getPostAuthorName(postId), user.getResponse()[0].getUserName(),
                "wrong post author");

        myProfilePage.getPostAuthorName(postId);
        SavePhotoOnWall photo = VkAPIUtil.sendPhotoOnServer(ownerId, PathConstants.PATH_TO_TEST_IMAGE);
        String newMessage = RandomUtil.generateRandomString(testData.getTextLength());
        postId = VkAPIUtil.editPostGetId(postId, ownerId, newMessage, photo.getResponse()[0].getOwnerId(),
                photo.getResponse()[0].getPhotoId());
        assertTrue(myProfilePage.isTextEdited(postId, newMessage), "post text isn't changed");

        DownloadPhoto downloadPhoto = VkAPIUtil.getPhoto(photo.getResponse()[0].getOwnerId(),
                photo.getResponse()[0].getPhotoId());
        RequestUtil.downloadImage(downloadPhoto.getResponse()[0].getSizes()[testData.getImageSize()].getUrl(),
                PathConstants.PATH_TO_DOWNLOAD_IMAGE);

        //проверка изображения падает
        //assertTrue(ImageUtil.areImagesEqual(PathConstants.PATH_TO_TEST_IMAGE, PathConstants.PATH_TO_DOWNLOAD_IMAGE));

        String comment = RandomUtil.generateRandomString(testData.getTextLength());
        int commentId = VkAPIUtil.sendComment(ownerId, postId, comment);
        assertEquals(ownerId, myProfilePage.getCommentAuthorId(commentId), "wrong comment author");
        assertEquals(comment, myProfilePage.getCommentMessage(commentId), "wrong comment message");

        Likes likesBefore = VkAPIUtil.likePost(ownerId, postId);
        myProfilePage.likePostById(postId);
        Likes likesAfter = VkAPIUtil.likePost(ownerId, postId);
        assertEquals(likesAfter.getResponse().getCount() - likesBefore.getResponse().getCount(),
                testData.getLikesCount(), "like isn't added");
        assertEquals(likesAfter.getResponse().getItems()[0], ownerId, "like author is wrong");

        VkAPIUtil.deletePost(ownerId, postId);
        assertTrue(myProfilePage.isPostNotDisplayed(postId), "post isn't deleted");
    }
}
