package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private static volatile Connect instance;
    private static final String URL = "jdbc:postgresql://localhost:5432/DZ35";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "64Tank46";

    private Connect() {
        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Connect getInstance() {
        if (instance == null) {
            synchronized (Connect.class) {
                if (instance == null) {
                    instance = new Connect();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}