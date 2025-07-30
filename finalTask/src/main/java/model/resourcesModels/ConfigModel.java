package model.resourcesModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigModel {
    private String url;
    private String db;
    private String apiUrl;
    private String token;
    private String user;
    private String password;
    private String cookieName;
    private String dateFormat;
}
