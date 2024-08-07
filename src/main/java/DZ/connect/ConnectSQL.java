package DZ.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectSQL {
    private static volatile ConnectSQL instance;
    private static final String URL = "jdbc:postgresql://localhost:5432/DZ35";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "64Tank46";

    private ConnectSQL() {
        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ConnectSQL getInstance() {
        if (instance == null) {
            synchronized (ConnectSQL.class) {
                if (instance == null) {
                    instance = new ConnectSQL();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
