package com.hust.itep.aims;

import com.hust.itep.aims.controller.BaseController;
import com.hust.itep.aims.utils.BaseAlert;
import com.hust.itep.aims.utils.Configs;
import com.hust.itep.aims.view.BaseScreenHandler;
import com.hust.itep.aims.view.cart.CartScreenHandler;
import com.hust.itep.aims.view.invoice.InvoiceScreenHandler;
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
            CartScreenHandler cartScreen = new CartScreenHandler(primaryStage, Configs.CART_SCREEN_PATH);
            cartScreen.setScreenTitle("Cart Screen");
            cartScreen.requestToViewCart();
            cartScreen.setImage();
            cartScreen.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        launch(args);
//    }
}
