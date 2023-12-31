package com.hust.itep.aims.view.cart;

import com.hust.itep.aims.controller.PlaceOrderController;
import com.hust.itep.aims.controller.ViewCartController;
import com.hust.itep.aims.entity.cart.Cart;
import com.hust.itep.aims.entity.cart.CartMedia;
import com.hust.itep.aims.entity.media.Media;
import com.hust.itep.aims.entity.order.Order;
import com.hust.itep.aims.utils.Configs;
import com.hust.itep.aims.utils.Utils;
import com.hust.itep.aims.view.BaseScreenHandler;
import com.hust.itep.aims.view.shipping.ShippingScreenHandler;
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
import java.util.logging.Logger;

public class CartScreenHandler extends BaseScreenHandler {

    private static Logger LOGGER = Utils.getLogger(CartScreenHandler.class.getName());

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


    ClassLoader classLoader = getClass().getClassLoader();

    public CartScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        placeOrderBtn.setOnMouseClicked(e -> {
            LOGGER.info("Place Order button clicked");
            try {
                requestToPlaceOrder();
            } catch (Exception exp) {
                LOGGER.severe("Cannot place the order, see the logs");
                exp.printStackTrace();
                throw new RuntimeException("Cannot place the order");
            }
        });
    }


    public ViewCartController getBController(){
        return (ViewCartController) super.getBController();
    }

    public void requestToViewCart() throws SQLException {
        setScreenTitle("Cart Screen");
//        getBController().checkAvailabilityOfProduct();
        displayCartWithMediaAvailability();
        show();
    }

    public void requestToPlaceOrder(){
        try {
            // create placeOrderController and process the order
            PlaceOrderController placeOrderController = new PlaceOrderController();
//            if (placeOrderController.getListCartMedia().size() == 0){
//                return;
//            }

//            placeOrderController.placeOrder();

            // display available media
            displayCartWithMediaAvailability();

            // create order
//            Order order = placeOrderController.createOrder();
            Order order = new Order();

            // display shipping form
            ShippingScreenHandler shippingScreenHandler = new ShippingScreenHandler(this.stage, Configs.SHIPPING_SCREEN_PATH, order);
            shippingScreenHandler.setScreenTitle("Shipping Screen");
//            shippingScreenHandler.setBController(placeOrderController);
            shippingScreenHandler.show();
        } catch (Exception e) {
            LOGGER.info("Error: "+e);
            // if some media are not available then display cart and break usecase Place Order
            displayCartWithMediaAvailability();
        }
    }

    public void updateCart() throws SQLException{
//        getBController().checkAvailabilityOfProduct();
        displayCartWithMediaAvailability();
    }

    void updateCartAmount(){
        // calculate subtotal and amount
        int subtotal = getBController().getCartSubtotal();
        int vat = (int)((Configs.PERCENT_VAT/100)*subtotal);
        int amount = subtotal + vat;
        LOGGER.info("amount" + amount);

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
        List lstMedia = new ArrayList();
        Media media = new Media (5, "abc", 100, "book", 3);
        Cart cart = Cart.getCart();
        CartMedia cartMedia1 = new CartMedia(media, cart,2, 200); // replace quantity and price with actual values
        lstMedia.add(cartMedia1);
        System.out.println("Test: "+lstMedia);
        // Add the CartMedia to the list
        lstMedia.add(cartMedia1);
//
////        // Create a cart media object
////        CartMedia cartMedia = new CartMedia(media, 2, 200);
////
////        // Add the cart media to the cart
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
//            updateCartAmount();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setImage(){

    }

}
