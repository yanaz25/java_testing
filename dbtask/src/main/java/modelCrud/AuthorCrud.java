package modelCrud;

import model.AuthorModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorCrud extends Crud {
    private static final String TABLE_NAME = "author";
    private static final String ALL_COLUMNS = "id, name, login, email";
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String LOGIN_COLUMN = "login";
    private static final String EMAIL_COLUMN = "email";

    public AuthorCrud() {
        super(TABLE_NAME);
    }

    public AuthorModel getModel(ResultSet resultSet) {
        AuthorModel model = null;
        try {
            int id = resultSet.getInt(ID_COLUMN);
            String name = resultSet.getString(NAME_COLUMN);
            String login = resultSet.getString(LOGIN_COLUMN);
            String email = resultSet.getString(EMAIL_COLUMN);
            model = new AuthorModel(id, name, login, email);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return model;
    }

    public AuthorModel selectByLogin(String login) {
        AuthorModel author = selectByParameter(LOGIN_COLUMN, login);
        return author;
    }

    public void insertRow(AuthorModel authorModel) {
        super.insertRow(ALL_COLUMNS, authorModel.getId(), authorModel.getName(), authorModel.getLogin(),
                authorModel.getEmail());
    }
}
