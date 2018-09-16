package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcHelper {

    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_example?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        properties.put("user", USER);
        properties.put("password", PASSWORD);

        return DriverManager.getConnection(URL, properties);
    }

}
