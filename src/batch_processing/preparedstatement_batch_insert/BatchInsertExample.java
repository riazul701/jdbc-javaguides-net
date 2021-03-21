package batch_processing.preparedstatement_batch_insert;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Insert Batch operation using PreparedStatement Interface
 * @author Ramesh Fadatare
 *
 */
public class BatchInsertExample {

    public static void main(String[] args) {
        parameterizedBatchUpdate();
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

    private static void parameterizedBatchUpdate() {

        String INSERT_USERS_SQL = "INSERT INTO users" + "  (id, name, email, country, password) VALUES " +
            " (?, ?, ?, ?, ?);";

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/test1?useSSL=false", "root", "");
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            connection.setAutoCommit(false);

            preparedStatement.setInt(1, 20);
            preparedStatement.setString(2, "a");
            preparedStatement.setString(3, "a@gmail.com");
            preparedStatement.setString(4, "India");
            preparedStatement.setString(5, "secret");
            preparedStatement.addBatch();

            preparedStatement.setInt(1, 21);
            preparedStatement.setString(2, "b");
            preparedStatement.setString(3, "b@gmail.com");
            preparedStatement.setString(4, "India");
            preparedStatement.setString(5, "secret");
            preparedStatement.addBatch();

            preparedStatement.setInt(1, 22);
            preparedStatement.setString(2, "c");
            preparedStatement.setString(3, "c@gmail.com");
            preparedStatement.setString(4, "India");
            preparedStatement.setString(5, "secret");
            preparedStatement.addBatch();

            preparedStatement.setInt(1, 23);
            preparedStatement.setString(2, "d");
            preparedStatement.setString(3, "d@gmail.com");
            preparedStatement.setString(4, "India");
            preparedStatement.setString(5, "secret");
            preparedStatement.addBatch();

            int[] updateCounts = preparedStatement.executeBatch();
            System.out.println(Arrays.toString(updateCounts));
            connection.commit();
            connection.setAutoCommit(true);
        } catch (BatchUpdateException batchUpdateException) {
            printBatchUpdateException(batchUpdateException);
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static void printBatchUpdateException(BatchUpdateException b) {

        System.err.println("----BatchUpdateException----");
        System.err.println("SQLState:  " + b.getSQLState());
        System.err.println("Message:  " + b.getMessage());
        System.err.println("Vendor:  " + b.getErrorCode());
        System.err.print("Update counts:  ");
        int[] updateCounts = b.getUpdateCounts();

        for (int i = 0; i < updateCounts.length; i++) {
            System.err.print(updateCounts[i] + "   ");
        }
    }
}
