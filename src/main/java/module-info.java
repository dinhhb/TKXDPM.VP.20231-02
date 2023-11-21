module com.example.aims {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.junit.jupiter.api;
    requires java.sql;

    opens hust.soict.itep.aims.controller to javafx.fxml;
//    opens hust.soict.itep.aims.entity to javafx.base;

    exports hust.soict.itep.aims.view;
    opens hust.soict.itep.aims.entity.cart to javafx.base;
    opens hust.soict.itep.aims.view to javafx.fxml;
}