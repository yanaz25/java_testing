package modelCrud;

import model.StatusModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusCrud extends Crud {
    private static final String TABLE_NAME = "status";
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";

    public StatusCrud() {
        super(TABLE_NAME);
    }

    public StatusModel getModel(ResultSet resultSet) {
        StatusModel status = null;
        try {
            int id = resultSet.getInt(ID_COLUMN);
            String result = resultSet.getString(NAME_COLUMN);
            status = new StatusModel(id, result);
        } catch (SQLException e) {
            e.getErrorCode();
        }
        return status;
    }

    public StatusModel selectByName(String name) {
        return selectByParameter(NAME_COLUMN, name);
    }
}
