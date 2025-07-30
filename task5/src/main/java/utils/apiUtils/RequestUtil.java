package utils.apiUtils;

import java.io.*;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;

public class RequestUtil {
    private static HttpClient client;
    private static final int BUFFER_SIZE = 1024;

    public static HttpClient getClient() {
        if (client == null) {
            client = HttpClients.createDefault();
        }
        return client;
    }

    public static String sendGetRequest(String url) {
        HttpGet request = new HttpGet(url);
        String response = null;
        try {
            HttpResponse httpResponse = getClient().execute(request);
            response = EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return response;
    }

    public static String sendPostRequest(String url) {
        HttpPost postRequest = new HttpPost(url);
        String response = null;
        try {
            HttpResponse httpResponse = getClient().execute(postRequest);
            response = EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return response;
    }

    public static String uploadImage(String uploadUrl, String imagePath, String name) {
        String responseString = null;
        HttpPost httpPost = new HttpPost(uploadUrl);
        File file = new File(imagePath);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody(name, file, ContentType.IMAGE_JPEG, file.getName());
        HttpEntity entity = builder.build();
        httpPost.setEntity(entity);
        try {
            HttpResponse response = getClient().execute(httpPost);
            responseString = EntityUtils.toString(response.getEntity(), Charset.defaultCharset());
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return responseString;
    }

    public static void downloadImage(String uploadUrl, String outputFilePath) {
        HttpGet httpGet = new HttpGet(uploadUrl);
        try {
            HttpResponse response = getClient().execute(httpGet);
            InputStream inputStream = response.getEntity().getContent();
            OutputStream outputStream = new FileOutputStream(outputFilePath);
            byte[] bytes = new byte[BUFFER_SIZE];
            int read;
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    public static void close() throws IOException {
        ((CloseableHttpClient) getClient()).close();
    }
}
