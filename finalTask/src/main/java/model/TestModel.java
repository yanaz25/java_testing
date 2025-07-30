package model;

import constants.PathConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.resourcesModels.ConfigModel;
import utils.fileUtils.JsonToObjectUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestModel implements Comparable<TestModel> {
    private int id;
    private String name;
    private int statusId;
    private String methodName;
    private int projectId;
    private int sessionId;
    private String startTime;
    private String endTime;
    private String env;
    private String browser;
    private int authorId;

    public int compareTo(TestModel model) {
        ConfigModel configData = JsonToObjectUtil.getObjectFromFile(PathConstants.PATH_TO_CONFIG, ConfigModel.class);
        try {
            Date date1 = new SimpleDateFormat(configData.getDateFormat()).parse(this.getStartTime());
            Date date2 = new SimpleDateFormat(configData.getDateFormat()).parse(model.getStartTime());
            if (date1.after(date2)) {
                return -1;
            } else {
                return 1;
            }
        } catch (ParseException ex) {
            ex.getMessage();
        }
        return 0;
    }

    public boolean areEqualTests(TestModel model) {
        if (this == model) {
            return true;
        }
        if (model == null || getClass() != model.getClass()) {
            return false;
        }
        boolean equals = name.equals(model.name) &&
                statusId == model.statusId &&
                methodName.equals(model.methodName) &&
                startTime.equals(model.startTime) &&
                endTime.equals(model.endTime);
        if (projectId != 0) {
            return equals &&
                    projectId == model.projectId &&
                    env.equals(model.env) &&
                    browser.equals(model.browser) &&
                    authorId == model.authorId;
        }
        return equals;
    }

    public static boolean areEqualLists(List<TestModel> actual, List<TestModel> expected) {
        for (int i = 0; i < actual.size(); i++) {
            if (!actual.get(i).areEqualTests(expected.get(i))) {
                return false;
            }
        }
        return true;
    }
}
