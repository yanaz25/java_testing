package constants.crudConstants;

public class CrudConstants {
    public static final String SELECT_ALL = "SELECT * FROM %s";
    public static final String SELECT_BY_PARAMETER = "SELECT * FROM %s WHERE %s = ?";
    public static final String COUNT_ROWS = "SELECT COUNT(*) FROM %s";
    public static final String DELETE_TESTS_BETWEEN_ID = "DELETE FROM %s WHERE id BETWEEN ? AND ?;";
    public static final String INSERT_ROWS = "INSERT INTO %s (%s) VALUES (%s);";
    public static final String UPDATE_ROWS = "UPDATE %s SET %s WHERE %s = ?";
}
