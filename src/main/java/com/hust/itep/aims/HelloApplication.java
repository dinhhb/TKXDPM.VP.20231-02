package com.hust.itep.aims;

import com.hust.itep.aims.database.ConnectJDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
            //Test query
        Connection conn = null;
        try {
            String sql = "select * from media";
            // Connnect to database
            conn = ConnectJDBC.getConnection();
            // Create statement
            Statement stmt = conn.createStatement();
            // Get data
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString("category"));
                System.out.println(rs.getInt("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                    System.out.println("Close successful !");
                }
            } catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        launch();
    }
}