module hu.bme.aut.bidemo.bicalldemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;


    opens hu.bme.aut.bidemo.bicalldemo to javafx.fxml;
    exports hu.bme.aut.bidemo.bicalldemo;
}