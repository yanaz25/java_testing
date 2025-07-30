package utils.bdUtils;

import constants.PathConstants;
import model.resourcesModels.ConfigModel;
import utils.fileUtils.JsonToObjectUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static ConfigModel configData = JsonToObjectUtil.getObjectFromFile(PathConstants.PATH_TO_CONFIG,
            ConfigModel.class);
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(configData.getDb(), configData.getUser(),
                        configData.getPassword());
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException ex) {
            ex.getSQLState();
        }
    }
}
