package com.hust.itep.aims.view.cart;

import com.hust.itep.aims.controller.ViewCartController;
import com.hust.itep.aims.entity.cart.CartMedia;
import com.hust.itep.aims.entity.media.Media;
import com.hust.itep.aims.utils.Configs;
import com.hust.itep.aims.utils.Utils;
import com.hust.itep.aims.view.BaseScreenHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CartScreenHandler extends BaseScreenHandler {

    @FXML
    private ImageView aimsImage;

    @FXML
    private Label subtotalLabel;

    @FXML
    private Label VATLabel;

    @FXML
    private Label totalLabel;

    @FXML
    VBox vboxCart;

    @FXML
    private Button placeOrderBtn;

//    String screenPath = "AIMS_cart.fxml";

    ClassLoader classLoader = getClass().getClassLoader();

    public CartScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        // fix relative image path caused by fxml
        File file = new File(".../resources/image/Logo.png");
        Image im = new Image(file.toURI().toString());
        aimsImage.setImage(im);

        // on mouse clicked, we back to home
//        aimsImage.setOnMouseClicked(e -> {
//            homeScreenHandler.show();
//        });

        // on mouse clicked, we start processing place order usecase
        placeOrderBtn.setOnMouseClicked(e -> {
            try {
                requestToPlaceOrder();
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        });
    }

    public ViewCartController getBController(){
        return (ViewCartController) super.getBController();
    }

    public void requestToViewCart(BaseScreenHandler prevScreen) throws SQLException {
        setPreviousScreen(prevScreen);
        setScreenTitle("Cart Screen");
        getBController().checkAvailabilityOfProduct();
        displayCartWithMediaAvailability();
        show();
    }

    public void requestToPlaceOrder(){

    }

    void updateCartAmount(){
        // calculate subtotal and amount
        int subtotal = getBController().getCartSubtotal();
        int vat = (int)((Configs.PERCENT_VAT/100)*subtotal);
        int amount = subtotal + vat;
//        LOGGER.info("amount" + amount);

        // update subtotal and amount of Cart
        subtotalLabel.setText(Utils.getCurrencyFormat(subtotal));
        VATLabel.setText(Utils.getCurrencyFormat(vat));
        totalLabel.setText(Utils.getCurrencyFormat(amount));
    }

    private void displayCartWithMediaAvailability(){
        // clear all old cartMedia
        vboxCart.getChildren().clear();

        // get list media of cart after check availability
//        List lstMedia = getBController().getListCartMedia();

        // Create a list of CartMedia
        List<CartMedia> lstMedia = new ArrayList<>();

        // Create a media object
        Media media = new Media();
        media.setId(BigInteger.valueOf(1));
        media.setCategory("Book");
        media.setPrice(20);

        // Create a CartMedia object
        CartMedia cartMedia1 = new CartMedia(media, 2, 200); // replace quantity and price with actual values

        // Add the CartMedia to the list
        lstMedia.add(cartMedia1);

//        // Create a cart media object
//        CartMedia cartMedia = new CartMedia(media, 2, 200);
//
//        // Add the cart media to the cart
//        List<CartMedia> cart = new ArrayList<>();
//        cart.add(cartMedia);

        try {
            for (Object cm : lstMedia) {

                // display the attribute of vboxCart media
                CartMedia cartMedia = (CartMedia) cm;
                MediaHandler mediaCartScreen = new MediaHandler(Configs.CART_MEDIA_PATH, this);
                mediaCartScreen.setCartMedia(cartMedia);

                // add spinner
                vboxCart.getChildren().add(mediaCartScreen.getContent());
            }
            // calculate subtotal and amount
            updateCartAmount();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
