module com.hust.itep.aims {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.hust.itep.aims to javafx.fxml;
//    opens com.hust.itep.aims.view.home to javafx.fxml;
//    opens com.hust.itep.aims.view.cart to javafx.fxml;
    opens com.hust.itep.aims.entity.media to javafx.base;
    opens com.hust.itep.aims.view.invoice to javafx.fxml;
    opens com.hust.itep.aims.controller to javafx.fxml;
    exports com.hust.itep.aims.controller;
    exports com.hust.itep.aims;
}