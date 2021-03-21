package batch_processing.batch_insert;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

/**
 * Insert Batch operation using Statement Interface
 * @author Ramesh Fadatare
 *
 */
public class BatchInsertExample {

    public static void main(String[] args) {
        batchUpdate();
    }
    private static void batchUpdate() {
        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/test1?useSSL=false", "root", "");
            // Step 2:Create a statement using connection object
            Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.addBatch("INSERT INTO Users VALUES (2, 'Pramod', 'pramod@gmail.com', 'India', '123');");
            statement.addBatch("INSERT INTO Users VALUES (3, 'A', 'a@gmail.com', 'India', '123');");
            statement.addBatch("INSERT INTO Users VALUES (4, 'B', 'b@gmail.com', 'India', '123');");
            statement.addBatch("INSERT INTO Users VALUES (5, 'C', 'c@gmail.com', 'India', '123');");
            statement.addBatch("INSERT INTO Users VALUES (6, 'D', 'd@gmail.com', 'India', '123');");
            int[] updateCounts = statement.executeBatch();
            System.out.println(Arrays.toString(updateCounts));
            connection.commit();
        } catch (BatchUpdateException batchUpdateException) {
            printBatchUpdateException(batchUpdateException);
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
