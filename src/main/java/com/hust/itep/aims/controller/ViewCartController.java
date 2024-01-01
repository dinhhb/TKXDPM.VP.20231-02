package com.hust.itep.aims.controller;

import com.hust.itep.aims.service.CartService;


public class ViewCartController extends BaseController {

    CartService cartService = new CartService();

    public void checkAvailabilityOfProduct() {
        cartService.checkAvailabilityOfProduct();
    }
    public int getCartSubtotal(){
        int subtotal = cartService.calSubtotal();
        return subtotal;
    }
}
