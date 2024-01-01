package com.hust.itep.aims;

import com.hust.itep.aims.entity.cart.Cart;
import com.hust.itep.aims.entity.cart.CartMedia;
import com.hust.itep.aims.entity.media.Media;
import com.hust.itep.aims.utils.Configs;
import com.hust.itep.aims.view.BaseScreenHandler;
import com.hust.itep.aims.view.home.HomeScreenHandler;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App1 extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {


//            List lstMedia = new ArrayList();
//            Media media = new Media (5, "abc", 100, "book", 3);
//            Cart cart = Cart.getCart();
//            CartMedia cartMedia1 = new CartMedia(media, cart,2, 200); // replace quantity and price with actual values
//            // lstMedia.add(cartMedia1);
//            // System.out.println("Test: "+lstMedia);
//        // Add the CartMedia to the list
//            lstMedia.add(cartMedia1);
                    HomeScreenHandler homeHandler = new HomeScreenHandler(primaryStage, Configs.HOME_PATH);
                    homeHandler.setScreenTitle("Home Screen");
//                    homeHandler.setImage();
                    homeHandler.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

}
