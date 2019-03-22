package daos;

import java.sql.Connection;
import com.mysql.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static final String url = "jdbc:mysql://localhost:3306/testdb?useLegacyDatetimeCode=false&serverTimezone=America/New_York";
    public static final String user = "root";
    public static final String password = "secretpassword";

    /**
     * Get a connection to database
     *
     * @return ConnectionFactory object
     */
    public static java.sql.Connection getConnection() {

        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }

    /**
     * Test Connection
     */
    public static void main(String[] args) {
        Connection connection = ConnectionFactory.getConnection();
        System.out.println("Connection successful!");
    }
}
