package modelCrud;

import model.LogModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LogCrud extends Crud {
    private static final String TABLE_NAME = "log";
    private static final String ALL_COLUMNS = "id, content, is_exception, test_id";
    private static final String ID_COLUMN = "id";
    private static final String CONTENT_COLUMN = "content";
    private static final String IS_EXCEPTION_COLUMN = "is_exception";
    private static final String TEST_ID_COLUMN = "test_id";

    public LogCrud() {
        super(TABLE_NAME);
    }

    public LogModel getModel(ResultSet resultSet) {
        LogModel model = null;
        try {
            int id = resultSet.getInt(ID_COLUMN);
            String content = resultSet.getString(CONTENT_COLUMN);
            int isException = resultSet.getInt(IS_EXCEPTION_COLUMN);
            int testId = resultSet.getInt(TEST_ID_COLUMN);
            model = new LogModel(id, content, isException, testId);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return model;
    }

    public void insert(LogModel logModel) {
        insertRow(ALL_COLUMNS, logModel.getId(), logModel.getContent(), logModel.getIsException(),
                logModel.getTestId());
    }
}
