/*
DELIMITER $$
USE `mysql_database`$$
BEGIN
 select distinct name from users where id = 1;

    select distinct email from users;

    select count(id) as users_count from users;
END
DELIMITER ;
*/
package sql_package.callablestatement_interface;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * CallableStatement Example
 * @author Ramesh Fadatare
 *
 */
public class CallableMultipleResultSetExample {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/test1?useSSL=false";
        String username = "root";
        String password = "";
        String sql = "call retreive_different_results()";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password); CallableStatement stmt = conn.prepareCall(sql);) {

            boolean hasRs = stmt.execute();

            System.out.println();
            // Get Product Names
            if (hasRs) {
                try (ResultSet rs = stmt.getResultSet()) {
                    while (rs.next()) {
                        System.out.println("NAME = " + rs.getString(1));
                    }
                }
            }

            // Get Total Price
            if (stmt.getMoreResults()) {
                try (ResultSet rs = stmt.getResultSet()) {
                    if (rs.next()) {
                        System.out.println("Email = " + rs.getString(1));
                    }
                }
            }

            // Get Max/Min Price
            if (stmt.getMoreResults()) {
                try (ResultSet rs = stmt.getResultSet()) {
                    if (rs.next()) {
                        System.out.println("Users count = " + rs.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
