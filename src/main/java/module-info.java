module com.hust.itep.aims {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires com.google.zxing;
    requires jasperreports;
    requires com.formdev.flatlaf;
//    requires distance.api;
//    requires com.google.zxing.javase;


    opens com.hust.itep.aims to javafx.fxml;
    opens com.hust.itep.aims.view.cart to javafx.fxml;
    opens com.hust.itep.aims.view.invoice to javafx.fxml;
    opens com.hust.itep.aims.view.shipping to javafx.fxml;
    opens com.hust.itep.aims.view.home to javafx.fxml;
    opens com.hust.itep.aims.controller to javafx.fxml;
    opens com.hust.itep.aims.entity.media to javafx.base;
    exports com.hust.itep.aims.controller;
    exports com.hust.itep.aims;
    exports com.hust.itep.aims.controller.admin;
    opens com.hust.itep.aims.controller.admin to javafx.fxml;
    exports com.hust.itep.aims.service;
    opens com.hust.itep.aims.service to javafx.fxml;
    exports com.hust.itep.aims.service.admin;
    opens com.hust.itep.aims.service.admin to javafx.fxml;
    exports com.hust.itep.aims.view.invoice;

    uses com.hust.itep.aims.controller.admin.MediaScreenCreator;
    provides com.hust.itep.aims.controller.admin.MediaScreenCreator with
            com.hust.itep.aims.controller.admin.BookScreenCreator,
            com.hust.itep.aims.controller.admin.CDAndLPScreenCreator,
            com.hust.itep.aims.controller.admin.DVDScreenCreator;
}