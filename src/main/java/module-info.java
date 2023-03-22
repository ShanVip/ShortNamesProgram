module com.diplom.shornames {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;



    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.diplom.ShortNamesProgram to javafx.fxml;
    exports com.diplom.ShortNamesProgram;
    exports com.diplom.ShortNamesProgram.controllers;
    opens com.diplom.ShortNamesProgram.controllers to javafx.fxml;
}