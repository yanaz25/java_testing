import com.fasterxml.jackson.core.type.TypeReference;
import constants.PathConstants;
import constants.StatusCodeConstants;
import models.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.*;
import utils.apiUtils.BodyUtil;
import utils.apiUtils.GetPostRequestUtil;

import java.net.http.HttpResponse;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class JsonPlaceholderTest {
    ConfigData configData;
    TestData testData;
    Endpoints endpoints;

    @BeforeClass
    public void setUp() {
        configData = JsonToObjectUtil.getObjectFromFile(PathConstants.PATH_TO_CONFIG, ConfigData.class);
        testData = JsonToObjectUtil.getObjectFromFile(PathConstants.PATH_TO_TEST_DATA, TestData.class);
        endpoints = JsonToObjectUtil.getObjectFromFile(PathConstants.PATH_TO_ENDPOINTS, Endpoints.class);
        GetPostRequestUtil.setUrl(configData.getUrl());
    }

    @Test
    public void getAllPostsTest() {
        HttpResponse response = GetPostRequestUtil.sendGETRequest(endpoints.getPosts(), BodyUtil.getBodyHandlerAsString());
        assertEquals(response.statusCode(), StatusCodeConstants.STATUS_CODE_OK, testData.getCodeAssertion());
        assertTrue(response.headers().map().get(testData.getContentTypeAttr()).get(0).contains(configData.getContentType()), "response isn't json");

        List<Post> posts = JsonToObjectUtil.getObjectListFromString(response.body().toString(), new TypeReference<>() {
        });
        assertTrue(Post.isPostsOrderedById(posts), "posts isn't ordered by id");
    }

    @Test
    public void getPostByIdTest() {
        HttpResponse response = GetPostRequestUtil.sendGETRequest(String.format(endpoints.getPostNum(), testData.getPostNum()), BodyUtil.getBodyHandlerAsString());
        assertEquals(response.statusCode(), StatusCodeConstants.STATUS_CODE_OK, testData.getCodeAssertion());

        Post postFromGet = JsonToObjectUtil.getObjectFromString(response.body().toString(), Post.class);
        Post testPost = new Post(testData.getUserId(), testData.getPostNum());
        assertTrue(testPost.isEqualIdAndNonEmpty(postFromGet), "wrong id or title/body is empty");
    }

    @Test
    public void getWrongPostTest() {
        HttpResponse response = GetPostRequestUtil.sendGETRequest(String.format(endpoints.getPostNum(), testData.getWrongPostNum()), BodyUtil.getBodyHandlerAsString());
        assertEquals(response.statusCode(), StatusCodeConstants.STATUS_CODE_NOT_FOUND, testData.getCodeAssertion());
        assertEquals(response.body(), testData.getEmptyJson(), "response isn't empty");
    }

    @Test
    public void postRequestTest() {
        Post myPost = new Post(testData.getMyPostUserId(), RandomUtil.generateRandomString(testData.getTextLength()), RandomUtil.generateRandomString(testData.getTextLength()));
        String json = JsonToObjectUtil.getJsonStringFromObject(myPost);
        HttpResponse response = GetPostRequestUtil.sendPOSTRequest(endpoints.getPosts(), BodyUtil.getBodyPublisherAsString(json), BodyUtil.getBodyHandlerAsString(), configData.getContentType());
        assertEquals(response.statusCode(), StatusCodeConstants.STATUS_CODE_CREATED, testData.getCodeAssertion());

        Post postFromResponse = JsonToObjectUtil.getObjectFromString(response.body().toString(), Post.class);
        assertTrue(myPost.isEqualExceptId(postFromResponse), "request and response don't match");
        assertTrue(response.body().toString().contains(testData.getIdAttr()), "response doesn't contain id");
    }

    @Test
    public void getAllUsersTest() {
        HttpResponse response = GetPostRequestUtil.sendGETRequest(endpoints.getUsers(), BodyUtil.getBodyHandlerAsString());
        assertEquals(response.statusCode(), StatusCodeConstants.STATUS_CODE_OK, testData.getCodeAssertion());
        assertTrue(response.headers().map().get(testData.getContentTypeAttr()).get(0).contains(configData.getContentType()), "response isn't json");

        User testUser = testData.getUser();
        List<User> usersList = JsonToObjectUtil.getObjectListFromString(response.body().toString(), new TypeReference<>() {
        });
        User userFromList = User.getUserById(usersList, testData.getUserNum());
        assertEquals(userFromList, testUser, "test data user and response don't match");
    }

    @Test
    public void getUserByIdTest() {
        HttpResponse response = GetPostRequestUtil.sendGETRequest(String.format(endpoints.getUserNum(), testData.getUserNum()), BodyUtil.getBodyHandlerAsString());
        assertEquals(response.statusCode(), StatusCodeConstants.STATUS_CODE_OK, testData.getCodeAssertion());

        User testUser = testData.getUser();
        User userFromResponse = JsonToObjectUtil.getObjectFromString(response.body().toString(), User.class);
        assertEquals(testUser, userFromResponse, "test data user and response don't match");
    }
}