module hu.bme.aut.bidemo.redisvotefx.biredisvotefxdemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires redis.clients.jedis;


    opens hu.bme.aut.bidemo.redisvotefx.biredisvotefxdemo to javafx.fxml;
    exports hu.bme.aut.bidemo.redisvotefx.biredisvotefxdemo;
}