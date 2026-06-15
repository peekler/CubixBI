package hu.bme.aut.bidemo.redisvotefx.bimysqldemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("BI Demo – Phone Calls");
        stage.setScene(scene);
        stage.setWidth(800);
        stage.setHeight(500);
        stage.show();
    }
}
