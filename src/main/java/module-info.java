module com.example.aims {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens hust.soict.itep.aims.controller to javafx.fxml;
    opens hust.soict.itep.aims.model to javafx.base;

    exports hust.soict.itep.aims.view;
}