package com.hust.itep.aims.entity.print;

public class FieldReportPayment {

    private String category;
    private double price;
    private String title;

    public FieldReportPayment(String category, double price, String title, int quantity) {
        this.category = category;
        this.price = price;
        this.title = title;
        this.quantity = quantity;
    }

    private int quantity;

    public FieldReportPayment() {
    }

}
