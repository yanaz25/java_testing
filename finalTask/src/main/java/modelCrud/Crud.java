package modelCrud;

import com.mysql.cj.jdbc.Blob;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static constants.crudConstants.CrudConstants.*;
import static utils.bdUtils.ConnectionUtil.getConnection;

public abstract class Crud {
    private String tableName;
    private String valueDelimiter = ", ";
    private String valueMark = "?";

    public Crud(String tableName) {
        this.tableName = tableName;
    }

    public abstract <T> T getModel(ResultSet resultSet);

    public <T> List<T> selectAll() {
        List<T> data = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(String.format(SELECT_ALL, tableName));
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                data.add(getModel(rs));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return data;
    }

    public <T> T selectByParameter(String parameter, String value) {
        T model = null;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(
                    String.format(SELECT_BY_PARAMETER, tableName, parameter));
            preparedStatement.setString(1, value);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            model = getModel(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return model;
    }

    public <T> List<T> selectAllByCondition(String condition, int projectId, int limit) {
        List<T> models = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(
                    String.format(SELECT_ALL, tableName) + condition);
            preparedStatement.setInt(1, projectId);
            preparedStatement.setInt(2, limit);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                models.add(getModel(rs));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return models;
    }

    public int getRowsCount() {
        int count = 0;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(
                    String.format(COUNT_ROWS, tableName));
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public void insertRow(String columns, Object... values) {
        StringJoiner paramPlace = new StringJoiner(valueDelimiter);
        for (int i = 0; i < values.length; i++) {
            paramPlace.add(valueMark);
        }
        String query = String.format(INSERT_ROWS, tableName, columns, paramPlace);
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            for (int i = 0; i < values.length; i++) {
                Object value = values[i];
                int paramIndex = i + 1;
                if (value instanceof Integer) {
                    statement.setInt(paramIndex, (Integer) value);
                } else if (value instanceof Long) {
                    statement.setLong(paramIndex, (Long) value);
                } else if (value instanceof String) {
                    statement.setString(paramIndex, (String) value);
                } else if (value instanceof Blob) {
                    statement.setBlob(paramIndex, (Blob) value);
                }
            }
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
