package com.hust.itep.aims;

import com.hust.itep.aims.utils.Configs;
import com.hust.itep.aims.view.home.HomeScreenHandler;
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
            InvoiceScreenHandler invoiceScreenHandler = new InvoiceScreenHandler(primaryStage, Configs.INVOICE_SCREEN_PATH);
            invoiceScreenHandler.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        launch(args);
//    }
}
