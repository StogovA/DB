package sample;

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

    public static void updatePerson(Person updatedPerson) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE users " +
                    "SET Name = ?,Age = ?,Email = ? WHERE id = " + updatedPerson.getId());
            ps.setString(1, updatedPerson.getName());
            ps.setString(2, updatedPerson.getAge());
            ps.setString(3, updatedPerson.getEmail());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removePerson(String id) {
        try {
            statement.executeUpdate("delete from users where id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addPerson(Person person) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into users (Name,Age,Email)" +
                    "values (?,?,?)");
            ps.setString(1, person.getName());
            ps.setString(2, person.getAge());
            ps.setString(3, person.getEmail());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getNextID() {
        try {
            ResultSet rs = statement.executeQuery("SELECT AUTO_INCREMENT FROM information_schema.tables" +
                    " WHERE table_name = 'users' AND table_schema = DATABASE()");
            rs.next();
            return rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ResultSet getFullBase() {
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

    public static List<Person> getPersons() {
        List<Person> personList = new ArrayList<Person>();
        if (resultSet == null) {
            resultSet = getFullBase();
        }
        try {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                personList.add(new Person(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }
}
