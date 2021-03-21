package sql_package.resultsetmetadata_interface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ramesh Fadatare
 *
 */
public class ResuleSetMetaDataExample {
    private static final String QUERY = "select id,name,email,country,password from Users";

    public static void main(String[] args) {

        // using try-with-resources to avoid closing resources (boiler plate code)

        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/test1?useSSL=false", "root", "");

            // Step 2:Create a statement using connection object
            Statement stmt = connection.createStatement();

            // Step 3: Execute the query or update query
            ResultSet rs = stmt.executeQuery(QUERY)) {

            ResultSetMetaData resultSetMetaData = rs.getMetaData();

            System.out.println("1. Column count in users table :: " + resultSetMetaData.getColumnCount());
            System.out.println("2. First column name in users table :: " + resultSetMetaData.getColumnName(1));
            System.out.println("3. Database name of users table' column id :: " + resultSetMetaData.getCatalogName(1));;
            System.out.println("4. Data type of column id :: " + resultSetMetaData.getColumnTypeName(1));
            System.out.println("5. Get table name of column id :: " + resultSetMetaData.getTableName(1));

        } catch (SQLException e) {
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
