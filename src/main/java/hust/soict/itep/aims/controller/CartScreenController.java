package hust.soict.itep.aims.controller;


import java.io.IOException;

import hust.soict.itep.aims.entity.cart.ProductInCart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CartScreenController {

    @FXML
    private TextField tfFilter;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

    @FXML
    private TableView<ProductInCart> tblMedia;

    @FXML
    private ObservableList<ProductInCart> products;

    @FXML
    private TableColumn<ProductInCart, String> colMediaTitle;

    @FXML
    private TableColumn<ProductInCart, String> colMediaCategory;

    @FXML
    private TableColumn<ProductInCart, Float> colMediaCost;


    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private Label CostLabel;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    public void initialize() {
        // Initialize the products list
        products = FXCollections.observableArrayList();

        // Add some products to the list
        products.add(new ProductInCart("Product 1", "Category 1", 10.99));
        products.add(new ProductInCart("Product 2", "Category 2", 20.99));

        // Set the items of the TableView
        tblMedia.setItems(products);

        // Bind the columns to the properties of the Product class
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
    }

    @FXML
    void addBookPressed() {
        // TODO: Implement the action when the "Add Book" menu item is pressed
    }

    @FXML
    void addCDPressed() {
        // TODO: Implement the action when the "Add CD" menu item is pressed
    }

    @FXML
    void addDVDPressed() {
        // TODO: Implement the action when the "Add DVD" menu item is pressed
    }

    @FXML
    void viewStorePressed() {
        // TODO: Implement the action when the "View Store" menu item is pressed
    }

    @FXML
    void btnRemovePressed() {
        // TODO: Implement the action when the "Remove" button is pressed
    }

    @FXML
    void placeOrderPressed() {
        try {
        // Load the delivery form
        Parent deliveryForm = FXMLLoader.load(getClass().getResource("/fxml/deliveryForm.fxml"));

        // Get the current stage
        Stage stage = (Stage) btnPlaceOrder.getScene().getWindow();

        // Set the delivery form as the new scene of the stage
        stage.setScene(new Scene(deliveryForm));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
