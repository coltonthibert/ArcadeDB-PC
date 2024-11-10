module com.example.arcadedb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens com.example.arcadedb to javafx.fxml;
    exports com.example.arcadedb;
}