package statement_interface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Insert Statement JDBC Example
 * @author Ramesh Fadatare
 *
 */
public class InsertStatementExample {
    private static final String INSERT_MULTIPLE_USERS_SQL = "INSERT INTO Users " +
        "VALUES (3, 'Pramod', 'pramod@gmail.com', 'India', '123')," +
        "(4, 'Deepa', 'deepa@gmail.com', 'India', '123')," + "(5, 'Tom', 'top@gmail.com', 'India', '123');";

    public static void main(String[] argv) throws SQLException {
        InsertStatementExample createTableExample = new InsertStatementExample();
        createTableExample.insertRecord();
    }

    public void insertRecord() throws SQLException {
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/test1?useSSL=false", "root", "");

            // Step 2:Create a statement using connection object
            Statement statement = connection.createStatement();) {

            // Step 3: Execute the query or update query
            int result = statement.executeUpdate(INSERT_MULTIPLE_USERS_SQL);
            System.out.println("No. of records affected : " + result);
        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
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
