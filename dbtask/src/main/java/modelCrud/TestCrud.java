package modelCrud;

import model.TestModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestCrud extends Crud {

    private static final String TABLE_NAME = "test";
    private static final String ALL_COLUMNS = "id, name, status_id, method_name, project_id, session_id, start_time,"
           + "end_time, env, browser, author_id";
    private static final String UPDATE_VALUES = "name = ?, status_id= ?, method_name = ?, project_id = ?,"
            + "session_id = ?, start_time = ?, end_time = ?, env = ?, browser = ?, author_id = ?";
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String STATUS_ID_COLUMN = "status_id";
    private static final String METHOD_NAME_COLUMN = "method_name";
    private static final String PROJECT_ID_COLUMN = "project_id";
    private static final String SESSION_ID_COLUMN = "session_id";
    private static final String START_TIME_COLUMN = "start_time";
    private static final String END_TIME_COLUMN = "end_time";
    private static final String ENV_COLUMN = "env";
    private static final String BROWSER_COLUMN = "browser";
    private static final String AUTHOR_ID_COLUMN = "author_id";

    public TestCrud() {
        super(TABLE_NAME);
    }

    public TestModel getModel(ResultSet resultSet) {
        TestModel testModel = null;
        try {
            int id = resultSet.getInt(ID_COLUMN);
            String name = resultSet.getString(NAME_COLUMN);
            int statusId = resultSet.getInt(STATUS_ID_COLUMN);
            String methodName = resultSet.getString(METHOD_NAME_COLUMN);
            int projectId = resultSet.getInt(PROJECT_ID_COLUMN);
            int sessionId = resultSet.getInt(SESSION_ID_COLUMN);
            String startTime = resultSet.getString(START_TIME_COLUMN);
            String endTime = resultSet.getString(END_TIME_COLUMN);
            String env = resultSet.getString(ENV_COLUMN);
            String browser = resultSet.getString(BROWSER_COLUMN);
            int authorId = resultSet.getInt(AUTHOR_ID_COLUMN);
            testModel = new TestModel(id, name, statusId,
                    methodName, projectId, sessionId, startTime, endTime, env, browser, authorId);

        } catch (SQLException e) {
            e.getErrorCode();
        }
        return testModel;
    }

    public TestModel selectById(int id) {
        TestModel testModel = selectByParameter(ID_COLUMN, id);
        return testModel;
    }

    public void insertRow(TestModel testModel) {
        insertRow(ALL_COLUMNS, testModel.getId(), testModel.getName(), testModel.getStatusId(),
                testModel.getMethodName(), testModel.getProjectId(), testModel.getSessionId(), testModel.getStartTime(),
                testModel.getEndTime(), testModel.getEnv(), testModel.getBrowser(), testModel.getAuthorId());
    }

    public void updateRow(TestModel testModel) {
        updateRow(UPDATE_TEST, ID_COLUMN, testModel.getId(), testModel.getName(), testModel.getStatusId(),
                testModel.getMethodName(), testModel.getProjectId(), testModel.getSessionId(), testModel.getStartTime(),
                testModel.getEndTime(), testModel.getEnv(), testModel.getBrowser(), testModel.getAuthorId());
    }
}
