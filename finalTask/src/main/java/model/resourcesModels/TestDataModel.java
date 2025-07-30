package model.resourcesModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.AuthorModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestDataModel {
    private int variant;
    private String project;
    private String saveProjectText;
    private int textLength;
    private int sessionId;
    private String browser;
    private String env;
    private AuthorModel author;
}
