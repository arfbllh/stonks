module com.example.stonks {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires java.desktop;

    opens com.example.stonks to javafx.fxml;
    exports Stonks;
    //exports User;
    //exports Users;
    exports Stonks.Users;
    exports Stonks.Records;
    exports Stonks.Entries;
    exports Stonks.Pages;
    exports Stonks.Windows;
}