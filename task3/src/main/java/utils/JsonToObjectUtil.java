package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

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
            ex.getMessage();
        }
        return object;
    }

    public static String getJsonStringFromObject(Object object) {
        String json = "";
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            json = ow.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            ex.getMessage();
        }
        return json;
    }

    public static <T> List<T> getObjectListFromString(String body, TypeReference<List<T>> typeRef) {
        List<T> objects = null;
        try {
            objects = objectMapper.readValue(body, typeRef);
        } catch (IOException ex) {
            ex.getMessage();
        }
        return objects;
    }
}