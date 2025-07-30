package models.resourcesModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigData {
    private String url;
    private String apiUrl;
    private String accessToken;
    private String login;
    private String password;
    private int wait;
}
