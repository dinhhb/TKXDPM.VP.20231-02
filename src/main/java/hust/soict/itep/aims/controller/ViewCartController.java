package hust.soict.itep.aims.controller;


import java.sql.SQLException;
import java.util.List;

import hust.soict.itep.aims.entity.media.Media;
import hust.soict.itep.aims.entity.cart.Cart;
import hust.soict.itep.aims.entity.cart.CartMedia;

/**
 * This class controls the flow of events when users view the Cart
 */
public class ViewCartController extends BaseController{

    /**
     * This method checks the available products in Cart
     * @throws SQLException
     */
    public void checkAvailabilityOfProduct() throws SQLException{
        Cart.getCart().checkAvailabilityOfProduct();
    }

    /**
     * This method calculates the cart subtotal
     * @return subtotal
     */
    public int getCartSubtotal(){
        int subtotal = Cart.getCart().calSubtotal();
        return subtotal;
    }

}