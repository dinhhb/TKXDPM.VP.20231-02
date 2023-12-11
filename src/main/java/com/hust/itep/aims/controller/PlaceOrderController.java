package com.hust.itep.aims.controller;


import com.hust.itep.aims.entity.cart.CartMedia;
import com.hust.itep.aims.entity.invoice.Invoice;
import com.hust.itep.aims.entity.order.Order;
import com.hust.itep.aims.entity.order.OrderMedia;
import com.hust.itep.aims.service.CartService;

import java.util.HashMap;


public class PlaceOrderController {

    CartService cartService = new CartService();

    // Functional cohesion
    public void placeOrder() {
        cartService.checkAvailabilityOfProduct();
    }

    // Fuctional cohesion
    public Order createOrder() {
        Order order = new Order();
        for (Object object : cartService.getListMedia()) {
            CartMedia cartMedia = (CartMedia) object;
            OrderMedia orderMedia =
                    new OrderMedia(cartMedia.getMedia(), cartMedia.getQuantity(), cartMedia.getPrice());
            order.getlstOrderMedia().add(orderMedia);
        }
        return order;
    }

    // Procedural cohesion
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

    // Coincidental cohesion
    public int validateDeliveryInfo(HashMap<String, String> info) {
        ValidateController valController = new ValidateController();

        if (!valController.validateName(info.get("name"))) {
            System.out.println("Invalid name!");
            throw new RuntimeException("Invalid name! Error!" );
        }
        if (!valController.validatePhoneNumber(info.get("phone"))) {
            System.out.println("Invalid phone number!");
            throw new RuntimeException("Invalid phone number! Error!" );
        }
        if (!valController.validateAddress(info.get("address"))) {
            System.out.println("Invalid address!");
            throw new RuntimeException("Invalid address! Error!" );
        }
        return 1;
    }
}
