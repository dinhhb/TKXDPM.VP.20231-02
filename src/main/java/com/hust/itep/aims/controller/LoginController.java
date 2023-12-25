package com.hust.itep.aims.controller;


import com.hust.itep.aims.database.ConnectJDBC;
import com.hust.itep.aims.entity.admin.AdminSession;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    @FXML
    private AnchorPane loginForm;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginBtn;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Alert alert;

    public void loginBtn(){
        if (username.getText().isEmpty() || password.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect username or password");
            alert.showAndWait();
        } else{
            String selectData = "SELECT username, password, isAdmin FROM user WHERE username = ? and password = ?";
            connect = ConnectJDBC.getConnection();
            try{
                prepare = connect.prepareStatement(selectData);
                prepare.setString(1, username.getText());
                prepare.setString(2, password.getText());

                result = prepare.executeQuery();

                // neu success chuyen sang man hinh admin neu la admin, user neu la user
                if (result.next()){
                    // lay username user nhap vao
                    AdminSession.username = username.getText();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully login");
                    alert.showAndWait();

                    boolean isAdmin = result.getBoolean("isAdmin");
                    if (isAdmin){
                        Parent root = FXMLLoader.load(getClass().getResource("/fxml/admin.fxml"));

                        Stage stage = new Stage();
                        Scene scene = new Scene(root);

                        stage.setTitle("AIMS");
                        stage.setMinWidth(1400);
                        stage.setMinHeight(800);

                        stage.setScene(scene);
                        stage.show();
                    } else{
                        Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));

                        Stage stage = new Stage();
                        Scene scene = new Scene(root);

                        stage.setTitle("AIMS");
                        stage.setMinWidth(1600);
                        stage.setMinHeight(600);

                        stage.setScene(scene);
                        stage.show();
                    }

                }else{ // neu failed hien ra error message
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect username or password");
                    alert.showAndWait();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
