package com.hust.itep.aims;

import com.hust.itep.aims.controller.ViewCartController;
import com.hust.itep.aims.utils.Configs;
import com.hust.itep.aims.view.cart.CartScreenHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
//            HomeScreenHandler homeHandler = new HomeScreenHandler(primaryStage, Configs.HOME_PATH);
//            homeHandler.setScreenTitle("Home Screen");
////            homeHandler.setImage();
//            homeHandler.show();
//            CartScreenHandler cartScreen = new CartScreenHandler(primaryStage, Configs.CART_SCREEN_PATH);
//            cartScreen.setScreenTitle("Cart Screen");
//            cartScreen.setBController(new ViewCartController());
//            cartScreen.requestToViewCart(this);
//            cartScreen.setImage();
//            cartScreen.show();
//            Order order = new Order();
//            ShippingScreenHandler shippingScreenHandler = new ShippingScreenHandler(primaryStage, Configs.SHIPPING_SCREEN_PATH, order);
//            shippingScreenHandler.setScreenTitle("Shhipping");
//            shippingScreenHandler.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        launch(args);
//    }
}
