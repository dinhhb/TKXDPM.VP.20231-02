package hust.soict.itep.aims.entity.cart;

import javafx.beans.property.*;

public class ProductInCart {
    private final StringProperty title;
//    private final StringProperty category;
    private final DoubleProperty cost;

    private final IntegerProperty quantity;

//    public ProductInCart(String title, String category, double cost, int quantity) {
//        this.title = new SimpleStringProperty(title);
//        this.category = new SimpleStringProperty(category);
//        this.cost = new SimpleDoubleProperty(cost);
//        this.quantity = new SimpleIntegerProperty(quantity);
//    }

    public ProductInCart(String title, double cost, int quantity) {
        this.title = new SimpleStringProperty(title);
        this.cost = new SimpleDoubleProperty(cost);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

//    public String getCategory() {
//        return category.get();
//    }
//
//    public StringProperty categoryProperty() {
//        return category;
//    }

    public double getCost() {
        return cost.get();
    }

    public DoubleProperty costProperty() {
        return cost;
    }

    public int getQuantiy(){
        return quantity.get();
    }
}