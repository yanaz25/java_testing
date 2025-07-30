package utils.fileUtils;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReaderUtil {
    private static String space = " ";

    public static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        String line;
        try {
            java.io.FileReader file = new java.io.FileReader(filePath);
            BufferedReader br = new BufferedReader(file);
            while ((line = br.readLine()) != null) {
                content.append(line).append(space);
            }
        } catch (IOException ex) {
            ex.getMessage();
        }
        return content.toString().trim();
    }
}
