package sql_package.drivermanager_class;

import java.sql.Driver;
import java.sql.DriverAction;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverManagerExamples {
    public static void main(String[] args) throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();
        DriverManager.registerDriver(driver);
        System.out.println("Driver successfully registered !");

        DeRegisterDriver deRegisterDriver = new DeRegisterDriver(driver);
        deRegisterDriver.deregister();
    }
}

class DeRegisterDriver implements DriverAction {

    private Driver driver;
    DeRegisterDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void deregister() {
        try {
            DriverManager.deregisterDriver(driver);
            System.out.println("DeregisterDriver successfully");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
