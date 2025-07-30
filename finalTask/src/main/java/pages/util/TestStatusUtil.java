package pages.util;

import constants.crudConstants.StatusConstants;
import model.StatusModel;
import modelCrud.StatusCrud;

public class TestStatusUtil {
    public static int getStatusId(String result) {
        int id = 0;
        StatusCrud statusCrud = new StatusCrud();
        StatusModel statusModel;

        switch (result) {
            case "Passed":
                statusModel = statusCrud.selectByName(StatusConstants.PASSED);
                id = statusModel.getId();
                break;
            case "Failed":
                statusModel = statusCrud.selectByName(StatusConstants.FAILED);
                id = statusModel.getId();
                break;
            case "Skipped":
                statusModel = statusCrud.selectByName(StatusConstants.SKIPPED);
                id = statusModel.getId();
                break;
        }
        return id;
    }
}
