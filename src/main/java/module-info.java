module com.example.technicalexercise {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires mysql.connector.java;

    opens com.example.technicalexercise to javafx.fxml;
    exports View;
    opens View to javafx.fxml;
    exports Controller;
    opens Controller to javafx.fxml;
}