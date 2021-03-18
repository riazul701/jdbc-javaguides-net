package com.javaguides.jdbc.statement.examples.packages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverManagerExamples {
    public static void main(String[] args) throws SQLException {

        DriverManager.setLoginTimeout(10); //to set login timeout
        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/mysql_database?useSSL=false", "root", "root")) {
            System.out.println("Connection created");
            //to get login timeout
            System.out.println("your login timeout is = " + DriverManager.getLoginTimeout());
        }
        System.out.println("Connection auto closed");
    }
}
