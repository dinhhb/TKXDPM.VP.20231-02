package hust.soict.itep.aims.entity.cart;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProductInCart {
    private final StringProperty title;
    private final StringProperty category;
    private final DoubleProperty cost;

    public ProductInCart(String title, String category, double cost) {
        this.title = new SimpleStringProperty(title);
        this.category = new SimpleStringProperty(category);
        this.cost = new SimpleDoubleProperty(cost);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public double getCost() {
        return cost.get();
    }

    public DoubleProperty costProperty() {
        return cost;
    }
}