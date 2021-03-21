package sql_package.drivermanager_class;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class DriverManagerExamples {
    public static void main(String[] args) throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();
        DriverManager.registerDriver(driver);
        System.out.println("Driver successfully registered !");

        Enumeration < Driver > iterator = DriverManager.getDrivers();
        while (iterator.hasMoreElements()) {
            Driver driver2 = (Driver) iterator.nextElement();
            System.out.println(driver2);
        }
    }
}
