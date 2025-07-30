package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigModel {
    private String url;
    private String user;
    private String password;
    private int sessionId;
    private String browser;
    private String env;
    private String dateFormat;
    private AuthorModel author;
    private ProjectModel project;
}
