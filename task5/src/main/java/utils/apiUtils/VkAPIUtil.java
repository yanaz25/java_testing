package utils.apiUtils;

import constants.PathConstants;
import models.*;
import models.resourcesModels.ConfigData;
import models.resourcesModels.Endpoints;
import utils.JsonToObjectUtil;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class VkAPIUtil {
    private static ConfigData configData = JsonToObjectUtil.getObjectFromFile(PathConstants.PATH_TO_CONFIG,
            ConfigData.class);
    private static Endpoints endpoints = JsonToObjectUtil.getObjectFromFile(PathConstants.PATH_TO_ENDPOINTS,
            Endpoints.class);
    private static String accessToken = configData.getAccessToken();
    private static String apiUrl = configData.getApiUrl();
    private static String attachmentPhotoMask = "photo%s_%s";
    private static String photoParamKey = "photo";

    public static User getCurrentUser() {
        String response = RequestUtil.sendGetRequest(String.format(apiUrl, endpoints.getUsers(), accessToken));
        return JsonToObjectUtil.getObjectFromString(response, User.class);
    }

    public static int sendPostGetPostId(Post post) {
        String request = String.format(endpoints.getAddPost(), post.getResponse().getOwnerId(),
                post.getResponse().getMessage());
        String response = RequestUtil.sendPostRequest(String.format(apiUrl, request, accessToken));
        return JsonToObjectUtil.getObjectFromString(response, Post.class).getResponse().getPostId();
    }

    public static SavePhotoOnWall sendPhotoOnServer(long ownerId, String imagePath) {
        String urlRequest = String.format(endpoints.getUploadPhotoUrl(), ownerId);
        String response = RequestUtil.sendGetRequest(String.format(apiUrl, urlRequest, accessToken));
        String uploadUrl = JsonToObjectUtil.getObjectFromString(response, PhotoUploadData.class)
                .getResponse().getUploadUrl();
        response = RequestUtil.uploadImage(uploadUrl, imagePath, photoParamKey);
        PhotoUploadData photoUploadData = JsonToObjectUtil.getObjectFromString(response, PhotoUploadData.class);

        String savePhotoRequest = String.format(endpoints.getSaveWallPhoto(), photoUploadData.getServer(),
                URLEncoder.encode(photoUploadData.getPhoto(), StandardCharsets.UTF_8), photoUploadData.getHash());
        response = RequestUtil.sendGetRequest(String.format(apiUrl, savePhotoRequest, accessToken));
        return JsonToObjectUtil.getObjectFromString(response, SavePhotoOnWall.class);
    }

    public static int editPostGetId(int postId, long ownerId, String message, long photoOwnerId, long photoId) {
        String editPostRequest = String.format(endpoints.getEditPost(), ownerId, postId, message,
                String.format(attachmentPhotoMask, photoOwnerId, photoId));
        String response = RequestUtil.sendGetRequest(String.format(apiUrl, editPostRequest, accessToken));
        return JsonToObjectUtil.getObjectFromString(response, Post.class).getResponse().getPostId();
    }

    public static int sendComment(long ownerId, int postId, String message) {
        String createCommentRequest = String.format(endpoints.getCreateComment(), ownerId, postId, message);
        String response = RequestUtil.sendPostRequest(String.format(apiUrl, createCommentRequest, accessToken));
        return JsonToObjectUtil.getObjectFromString(response, Comment.class).getResponse().getCommentId();
    }

    public static Likes likePost(long ownerId, int postId) {
        String getLikesRequest = String.format(endpoints.getLikes(), ownerId, postId);
        String response = RequestUtil.sendGetRequest(String.format(apiUrl, getLikesRequest, accessToken));
        return JsonToObjectUtil.getObjectFromString(response, Likes.class);
    }

    public static DownloadPhoto getPhoto(long ownerId, long photoId) {
        String getPhotoRequest = String.format(endpoints.getPhoto(), ownerId, photoId);
        String response = RequestUtil.sendGetRequest(String.format(apiUrl, getPhotoRequest, accessToken));
        return JsonToObjectUtil.getObjectFromString(response, DownloadPhoto.class);
    }

    public static void deletePost(long ownerId, int postId) {
        String deletePostRequest = String.format(endpoints.getDeletePost(), ownerId, postId);
        RequestUtil.sendGetRequest(String.format(apiUrl, deletePostRequest, accessToken));
    }
}
