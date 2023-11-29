package com.hust.itep.aims.entity.invoice;

import com.hust.itep.aims.entity.order.Order;

import java.math.BigInteger;

public class Invoice {

    private BigInteger id;
    private int totalAmount;
    private Order order;

    public Invoice(BigInteger id, int totalAmount, Order order) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.order = order;
    }

    public Invoice() {};

    public Invoice(Order order){
        this.order = order;
    }
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
