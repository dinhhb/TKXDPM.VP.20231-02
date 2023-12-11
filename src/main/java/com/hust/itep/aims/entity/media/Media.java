package com.hust.itep.aims.entity.media;

import java.math.BigInteger;
import java.util.Date;

public class Media {

    private int id;
    private String category;
    private int price;
    private int value;
    private String title;
    private String description;
    private String barcode;
    private int quantity;
    private Date importDate;
    private Boolean rushOrderSupport;
    private String imageUrl;
    private String productDimension;

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getValue() {
        return value;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getImportDate() {
        return importDate;
    }

    public Boolean getRushOrderSupport() {
        return rushOrderSupport;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getProductDimension() {
        return productDimension;
    }

    public Media(Integer id, String category, int price, int value, String title, String description, int quantity, Date importDate, Boolean rushOrderSupport, String barcode, String productDimension, String imageUrl) {
        this.id = id;
        this.category = category;
        this.price = price;
        this.value = value;
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.importDate = importDate;
        this.rushOrderSupport = rushOrderSupport;
        this.productDimension = productDimension;
        this.barcode = barcode;
        this.imageUrl = imageUrl;
    }

}
