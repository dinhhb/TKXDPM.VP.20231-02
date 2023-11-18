package hust.soict.itep.aims.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CartScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/cart.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1024, 768);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Cart");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
