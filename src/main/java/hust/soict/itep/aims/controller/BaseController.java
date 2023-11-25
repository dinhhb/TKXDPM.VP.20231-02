package hust.soict.itep.aims.controller;

import java.util.List;

import hust.soict.itep.aims.entity.cart.Cart;
import hust.soict.itep.aims.entity.cart.CartMedia;
import hust.soict.itep.aims.entity.media.Media;
public class BaseController {

    /**
     * The method checks whether the Media in Cart, if it were in, we will return the CartMedia else return null
     * @param media
     * @return CartMedia or null
     */
    public CartMedia checkMediaInCart(Media media){
        return Cart.getCart().checkMediaInCart(media);
    }

    /**
     * This method gets the list of items in cart
     * @return List[CartMedia]
     */
    public List getListCartMedia(){
        return Cart.getCart().getListMedia();
    }
}
