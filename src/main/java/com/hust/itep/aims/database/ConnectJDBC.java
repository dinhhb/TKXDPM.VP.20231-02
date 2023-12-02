package com.hust.itep.aims.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ConnectJDBC {

    public static Connection getConnection() {
        Connection conn = null;

        try(FileInputStream f = new FileInputStream("src/main/java/db.properties")) {
            // load the properties file
            Properties pros = new Properties();
            pros.load(f);
            // assign db parameters
            String url       = pros.getProperty("url");
            System.out.println(url);
            String user      = pros.getProperty("user");
            System.out.println(user);
            String password  = pros.getProperty("password");
            System.out.println(password);
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Ok");
            //Test excute data
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("select * from media");
//            while (rs.next()) {
//                System.out.println(rs.getString("category"));
//                System.out.println(rs.getInt("price"));
//            }
        } catch(IOException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try{
                if(conn != null)
                    conn.close();
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }

        return null;
    }

}