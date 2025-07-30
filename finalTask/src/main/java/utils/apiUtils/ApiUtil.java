package utils.apiUtils;

import constants.PathConstants;
import model.resourcesModels.ConfigModel;
import model.resourcesModels.TestDataModel;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import utils.fileUtils.JsonToObjectUtil;

import java.util.ArrayList;
import java.util.List;

public class ApiUtil extends RequestUtil {
    private TestDataModel testData = JsonToObjectUtil.getObjectFromFile(PathConstants.PATH_TO_TEST_DATA,
            TestDataModel.class);
    private ConfigModel configData = JsonToObjectUtil.getObjectFromFile(PathConstants.PATH_TO_CONFIG, ConfigModel.class);
    private String url = configData.getApiUrl();
    private String paramName = "variant";

    public String getToken() {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair(paramName, String.valueOf(testData.getVariant())));
        return sendPostRequest(url + configData.getToken(), params);
    }
}
