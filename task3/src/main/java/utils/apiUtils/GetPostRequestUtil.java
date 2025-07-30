package utils.apiUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetPostRequestUtil {
    private static String url;
    private static HttpClient client;
    private static HttpResponse response;

    public static HttpClient getClient() {
        if (client == null) {
            client = HttpClient.newHttpClient();
        }
        return client;
    }

    public static void setUrl(String url) {
        GetPostRequestUtil.url = url;
    }

    private static HttpRequest createGETRequest(String resource) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + resource)).GET()
                .build();
        return request;
    }

    public static HttpResponse sendGETRequest(String resource, HttpResponse.BodyHandler<?> bodyHandler) {
        HttpRequest request = createGETRequest(resource);
        try {
            response = getClient().send(request, bodyHandler);
        } catch (IOException ioeEx) {
            ioeEx.getMessage();
        } catch (InterruptedException ex) {
            ex.getMessage();
        }
        return response;
    }

    private static HttpRequest createPOSTRequest(String resource, HttpRequest.BodyPublisher bodyPublisher, String contentType) {
        return HttpRequest.newBuilder()
                .header("Content-Type", contentType)
                .uri(URI.create(url + resource)).POST(bodyPublisher)
                .build();
    }

    public static HttpResponse sendPOSTRequest(String resource, HttpRequest.BodyPublisher bodyPublisher, HttpResponse.BodyHandler<?> bodyHandler, String contentType) {
        HttpRequest request = createPOSTRequest(resource, bodyPublisher, contentType);
        try {
            response = getClient().send(request, bodyHandler);
        } catch (IOException ioeEx) {
            ioeEx.getMessage();
        } catch (InterruptedException ex) {
            ex.getMessage();
        }
        return response;
    }
}
