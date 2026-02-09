package org.example.utils;
import java.sql.*;

public class DatabaseConnection {

    private static String URL;
    private static String USER;
    private static String PASSWORD;

    public static void set_URL(String url) {URL = url;}
    public static void set_USER(String user){
        USER = user;
    }
    public static void set_PASSWORD(String password){
        PASSWORD = password;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}


//create role manager password '4231';
//create role loader password '2341';
//create role business_admin password '4132';