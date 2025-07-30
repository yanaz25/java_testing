package utils.apiUtils;

import java.io.FileNotFoundException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;

public class BodyUtil {
    public static HttpResponse.BodyHandler getBodyHandlerAsString() {
        return HttpResponse.BodyHandlers.ofString();
    }

    public static HttpResponse.BodyHandler getBodyHandlerAsFile(String filePath) {
        return HttpResponse.BodyHandlers.ofFile(Paths.get(filePath));
    }

    public static HttpRequest.BodyPublisher getBodyPublisherAsString(String body) {
        return HttpRequest.BodyPublishers.ofString(body);
    }

    public static HttpRequest.BodyPublisher getBodyPublisherAsFile(String filePath) {
        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.noBody();
        try {
            bodyPublisher = HttpRequest.BodyPublishers.ofFile(Paths.get(filePath));
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        }
        return bodyPublisher;
    }
}
