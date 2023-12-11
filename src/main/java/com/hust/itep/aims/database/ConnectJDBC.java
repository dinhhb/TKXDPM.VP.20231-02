package com.hust.itep.aims.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ConnectJDBC {

    public static Connection getConnection() {
        Connection conn = null;

        try (FileInputStream f = new FileInputStream("src/main/java/db.properties")) {
            // load the properties file
            Properties pros = new Properties();
            pros.load(f);
            // assign db parameters
            String url = pros.getProperty("url");
            String user = pros.getProperty("user");
            String password = pros.getProperty("password");
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful !");
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            Properties properties = new Properties();
//            properties.setProperty("user", "root");
//            properties.setProperty("password", "Dinh2612");
//            properties.setProperty("useSSL", "false");
//            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/aims?useSSL=false", properties);
//            return connect;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
    }
}

