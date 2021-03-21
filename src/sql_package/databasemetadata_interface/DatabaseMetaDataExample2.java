package sql_package.databasemetadata_interface;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DatabaseMetaData interface API examples
 * @author Ramesh Fadatare
 *
 */
public class DatabaseMetaDataExample {

    public static void main(String[] args) {
        printDatabaseTables();
    }

    private static void printDatabaseTables() {
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/test1?useSSL=false", "root", "")) {
            DatabaseMetaData dbmd = connection.getMetaData();
            String table[] = {
                "TABLE"
            };
            ResultSet rs = dbmd.getTables(null, null, null, table);

            while (rs.next()) {
                System.out.println(rs.getString(3));
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
