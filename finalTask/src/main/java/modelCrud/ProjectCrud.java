package modelCrud;

import model.ProjectModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectCrud extends Crud {
    private static final String TABLE_NAME = "project";
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";

    public ProjectCrud() {
        super(TABLE_NAME);
    }

    public ProjectModel getModel(ResultSet resultSet) {
        ProjectModel projectModel = null;
        try {
            int id = resultSet.getInt(ID_COLUMN);
            String name = resultSet.getString(NAME_COLUMN);
            projectModel = new ProjectModel(id, name);
        } catch (SQLException e) {
            e.getErrorCode();
        }
        return projectModel;
    }

    public ProjectModel selectByName(String name) {
        return selectByParameter(NAME_COLUMN, name);
    }
}
