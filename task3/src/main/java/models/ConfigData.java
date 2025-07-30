package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigData {
    private String url;
    private String testDataPath;
    private String testUserPath;
    private String postsPath;
    private String contentType;
}
