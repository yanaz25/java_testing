package utils;

import constants.crudConstants.StatusConstants;
import model.StatusModel;
import modelCrud.StatusCrud;
import org.testng.ITestResult;

public class StatusInfoUtil {

    public static int getStatusId(ITestResult testResult) {
        int id = 0;
        StatusCrud statusCrud = new StatusCrud();
        StatusModel statusModel;

        switch (testResult.getStatus()) {
            case ITestResult.SUCCESS:
                statusModel = statusCrud.selectByName(StatusConstants.PASSED);
                id = statusModel.getId();
                break;
            case ITestResult.FAILURE:
                statusModel = statusCrud.selectByName(StatusConstants.FAILED);
                id = statusModel.getId();
                break;
            case ITestResult.SKIP:
                statusModel = statusCrud.selectByName(StatusConstants.SKIPPED);
                id = statusModel.getId();
                break;
        }
        return id;
    }
}
