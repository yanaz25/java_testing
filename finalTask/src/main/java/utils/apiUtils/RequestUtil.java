package utils.apiUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class RequestUtil {
    private static HttpClient client;

    public static HttpClient getClient() {
        if (client == null) {
            client = HttpClients.createDefault();
        }
        return client;
    }

    public static String sendPostRequest(String url, List<NameValuePair> params) {
        HttpPost postRequest = new HttpPost(url);

        String response = null;
        try {
            postRequest.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));
            HttpResponse httpResponse = getClient().execute(postRequest);
            response = EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return response;
    }

    public static void close() throws IOException {
        ((CloseableHttpClient) getClient()).close();
    }
}
