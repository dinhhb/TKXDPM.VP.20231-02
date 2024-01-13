package com.hust.itep.aims.service;


import com.hust.itep.aims.entity.order.Order;
import com.hust.itep.aims.entity.order.OrderMedia;
import com.hust.itep.aims.utils.Configs;

import java.util.List;

public class OrderService {
    private List lstOrderMedia;

    public OrderService(Order order) {
        lstOrderMedia = order.getLstOrderMedia();
    }

    public int getAmount(){
        double amount = 0;
        if (lstOrderMedia == null) return 0;
        for (Object object : lstOrderMedia) {
            OrderMedia om = (OrderMedia) object;
            amount += om.getPrice();
        }

        return (int) (amount + (Configs.PERCENT_VAT/100)*amount);
    }
}
