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
                    HomeScreenHandler homeHandler = new HomeScreenHandler(primaryStage, Configs.HOME_PATH);
                    homeHandler.setScreenTitle("Home Screen");
//                    homeHandler.setImage();
                    homeHandler.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void run() {
        launch();
    }
}
