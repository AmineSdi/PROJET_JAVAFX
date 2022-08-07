package Model.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class implements a singleton design pattern
 * in order to protect the connection
 */
class DBConnection {
    private static DBConnection instance = null;
    //private String jdbcUrl = "jdbc:sqlite:MedicalSystem.db";
    private Connection connection = null;
    private DBConnection(String databasePath) {
//        String jdbcUrl = "jdbc:sqlite:MedicalSystem.db";
        try {
            connection = DriverManager.getConnection(databasePath);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    // Factory method to provide the users with instances
    static public DBConnection getInstance(String databasePath)
    {
        if (instance == null)
            instance = new DBConnection(databasePath);
        return instance;
    }
    public Connection getConnection() {
        return this.connection;
    }
}
