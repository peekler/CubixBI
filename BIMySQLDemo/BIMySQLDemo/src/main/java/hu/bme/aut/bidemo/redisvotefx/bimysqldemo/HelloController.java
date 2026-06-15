package hu.bme.aut.bidemo.redisvotefx.bimysqldemo;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.sql.*;

public class HelloController {
    @FXML private Button btnQueryAll;
    @FXML private TextArea txtOutput;

    // Adjust credentials if different in your local Docker
    private static final String URL =
            "jdbc:mysql://127.0.0.1:3306/bi_schema?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "StrongPassword123!";

    @FXML
    private void initialize() {
        appendLog("Starting application...");
        // Test DB connection on startup in background
        Task<Void> connectTask = new Task<>() {
            @Override
            protected Void call() {
                try (Connection c = DbUtil.getConnection(URL, USER, PASS)) {
                    if (c != null && c.isValid(3)) {
                        appendLog("✅ Database connection successful.");
                    } else {
                        appendLog("⚠️ Database connection returned invalid.");
                    }
                } catch (Exception e) {
                    appendLog("❌ Database connection failed: " + e.getMessage());
                }
                return null;
            }
        };
        new Thread(connectTask).start();
    }

    @FXML
    private void onQueryAll() {
        appendLog("Executing query: SELECT * FROM bi_schema.phone_calls");
        Task<String> queryTask = new Task<>() {
            @Override
            protected String call() throws Exception {
                String sql = "SELECT * FROM bi_schema.phone_calls";
                try (Connection c = DbUtil.getConnection(URL, USER, PASS);
                     PreparedStatement ps = c.prepareStatement(sql);
                     ResultSet rs = ps.executeQuery()) {

                    StringBuilder sb = new StringBuilder();
                    ResultSetMetaData md = rs.getMetaData();
                    int cols = md.getColumnCount();

                    // Header
                    for (int i = 1; i <= cols; i++) {
                        sb.append(md.getColumnLabel(i));
                        if (i < cols) sb.append('\t');
                    }
                    sb.append('\n');

                    // Rows
                    while (rs.next()) {
                        for (int i = 1; i <= cols; i++) {
                            Object val = rs.getObject(i);
                            sb.append(val == null ? "NULL" : val.toString());
                            if (i < cols) sb.append('\t');
                        }
                        sb.append('\n');
                    }
                    return sb.toString();
                }
            }

            @Override
            protected void succeeded() {
                appendLog("✅ Query completed.");
                String result = getValue();
                if (result.isEmpty()) {
                    appendLog("ℹ️ No rows returned.");
                } else {
                    appendText(result);
                }
            }

            @Override
            protected void failed() {
                Throwable e = getException();
                appendLog("❌ Query failed: " + (e != null ? e.getMessage() : "Unknown error"));
            }
        };

        Thread t = new Thread(queryTask);
        t.setDaemon(true);
        t.start();
    }

    private void appendLog(String line) {
        appendText("[LOG] " + line + "\n");
    }

    private void appendText(String text) {
        Platform.runLater(() -> txtOutput.appendText(text));
    }
}
