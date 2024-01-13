package com.hust.itep.aims.service;

import com.hust.itep.aims.entity.order.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    @Test
    public void test(){
        OrderService orderService = new OrderService(new Order());
        int instance = orderService.getAmount();
        assertNotNull(instance);
    }
}