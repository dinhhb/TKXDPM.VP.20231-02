package hust.soict.itep.aims.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DeliveryFormController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField emailField;

    @FXML
    public void handleSubmitButtonAction() {
        String name = nameField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();

        // Handle the form submission here
        // For example, you might want to print the form data to the console:
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
    }
}