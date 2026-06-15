package hu.bme.aut.bidemo.bicalldemo;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
    public static Connection getConnection(String url, String user, String pass) throws Exception {
        // The driver is auto-registered by mysql-connector-j on recent Java
        return DriverManager.getConnection(url, user, pass);
    }
}
