module com.example.eigen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires javafx.base;


    opens com.example.eigen to javafx.fxml;
    exports com.example.eigen;
}