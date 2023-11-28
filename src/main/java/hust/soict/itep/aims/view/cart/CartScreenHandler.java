package hust.soict.itep.aims.view.cart;

import hust.soict.itep.aims.entity.cart.CartMedia;
import hust.soict.itep.aims.entity.cart.ProductInCart;
import hust.soict.itep.aims.exception.PlaceOrderException;
import hust.soict.itep.aims.view.BaseScreenHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    String screenPath = "AIMS_cart.fxml";

    ClassLoader classLoader = getClass().getClassLoader();

//    public CartScreenHandler(Stage stage, String screenPath) throws IOException {
//        super(stage, screenPath);
//
//        List<ProductInCart> products = new ArrayList<>();
//
//        // Add some sample products to the list
//        products.add(new ProductInCart("Product 1", 10.0, 2));
//        products.add(new ProductInCart("Product 2", 20.0, 1));
//        products.add(new ProductInCart("Product 3", 30.0, 3));
//
//        // Loop through the products and create HBox elements for each product
//        for (ProductInCart product : products) {
//            HBox productBox = new HBox();
//            // Create and configure UI elements for the product
////            ImageView productImage = new ImageView(/* Set the image for the product */);
//            titleLabel = new Label(product.getTitle());
//            amountLabel = new Label(String.valueOf(product.getCost()));
//            quantityLabel = new Label(String.valueOf(product.getCost()));
//            subtotalLabel = new Label(String.valueOf(100));
//
//            // Add UI elements to the HBox
//            productBox.getChildren().addAll(titleLabel, amountLabel, quantityLabel, subtotalLabel);
//
//            // Add the product HBox to the main layout (e.g., a VBox or another container)
//            // For example, if you have a VBox named productContainer, you can do:
//            // productContainer.getChildren().add(productBox);
//        }
//    }

    public CartScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);

        // fix relative image path caused by fxml
        File file = new File(classLoader.getResource("image/Logo.png").toExternalForm());
        Image im = new Image(file.toURI().toString());
        aimsImage.setImage(im);

        // on mouse clicked, we back to home
//        aimsImage.setOnMouseClicked(e -> {
//            homeScreenHandler.show();
//        });

        // on mouse clicked, we start processing place order usecase
        placeOrderBtn.setOnMouseClicked(e -> {
//            LOGGER.info("Place Order button clicked");
            try {
                requestToPlaceOrder();
            } catch (Exception exp) {
//                LOGGER.severe("Cannot place the order, see the logs");
                exp.printStackTrace();
                throw new PlaceOrderException(Arrays.toString(exp.getStackTrace()).replaceAll(", ", "\n"));
            }

        });
    }

//    public ViewCartController getBController(){
//        return (ViewCartController) super.getBController();
//    }

    public void requestToViewCart(BaseScreenHandler prevScreen) throws SQLException {
        setPreviousScreen(prevScreen);
        setScreenTitle("Cart Screen");
        getBController().checkAvailabilityOfProduct();
        displayCartWithMediaAvailability();
        show();
    }

    private void displayCartWithMediaAvailability(){
        // clear all old cartMedia
        vboxCart.getChildren().clear();

        // get list media of cart after check availability
        List lstMedia = getBController().getListCartMedia();

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

    public void requestToPlaceOrder(){

    }
}