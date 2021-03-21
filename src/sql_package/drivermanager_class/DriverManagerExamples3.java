package sql_package.drivermanager_class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverManagerExamples {
    public static void main(String[] args) throws SQLException {

        DriverManager.setLoginTimeout(10); //to set login timeout
        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/test1?useSSL=false", "root", "")) {
            System.out.println("Connection created");
            //to get login timeout
            System.out.println("your login timeout is = " + DriverManager.getLoginTimeout());
        }
        System.out.println("Connection auto closed");
    }
}
