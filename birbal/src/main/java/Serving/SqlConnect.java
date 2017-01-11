package Serving;

import java.sql.*;

/**
 * Created by shreenath on 11/1/17.
 */
public class SqlConnect {
    public static void main(String args[]){
        String url = "jdbc:mysql://localhost:3306/javadb";
        String username = "java";
        String password = "java";
        loadDriver();
        System.out.println("Connecting database...");
        Statement stmt;
        ResultSet rs;
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("show databases;");
            rs = stmt.executeQuery("use javadb;");
            rs = stmt.executeQuery("desc taskTable;");
            System.out.println(rs);


        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    static void loadDriver() {
        System.out.println("Loading driver...");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }

    }
}
