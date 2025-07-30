package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class JsonToObjectUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T getObjectFromFile(String path, Class<T> type) {
        File file = Paths.get(path).toFile();
        T object = null;
        try {
            object = objectMapper.readValue(file, type);
        } catch (IOException ex) {
            ex.getMessage();
        }
        return object;
    }

    public static <T> T getObjectFromString(String body, Class<T> type) {
        T object = null;
        try {
            object = objectMapper.readValue(body, type);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return object;
    }
}
