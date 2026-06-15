module hu.bme.aut.bidemo.redisvotefx.bimysqldemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens hu.bme.aut.bidemo.redisvotefx.bimysqldemo to javafx.fxml;
    exports hu.bme.aut.bidemo.redisvotefx.bimysqldemo;
}