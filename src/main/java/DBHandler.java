import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Даги on 09.11.2017.
 */
public class DBHandler {
    private static final String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&" +
            "useSSL=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASS = "root";
    private static ResultSet resultSet;
    private static Statement statement;
    private static Connection connection;

    static {
        connection = getConnection();
        try {
            assert connection != null;
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet getResultSet() {
        return resultSet;
    }

    private static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet getFullBase() {
        try {
            resultSet = statement.executeQuery("SELECT * from users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static int getColumnCount() {
        int count = 0;
        getFullBase();
        try {
            count = resultSet.getMetaData().getColumnCount();
            resultSet.beforeFirst();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static List<String> getNamesColumn() {
        List<String> namesColumn = new ArrayList<String>();
        for (int i = 0; i < getColumnCount(); i++) {
            try {
                namesColumn.add(resultSet.getMetaData().getColumnName(i + 1));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return namesColumn;
    }

    public static List<Row> getRows() {
        int count = getColumnCount();
        List<Row> rows = new ArrayList<Row>();
        try {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                List<String> row = new ArrayList<String>();
                for (int i = 0; i < count; i++) {
                    row.add(resultSet.getString(i + 1));
                }
                rows.add(new Row(row));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }
}
