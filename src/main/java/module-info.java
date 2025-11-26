module com.example.eigen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.eigen to javafx.fxml;
    exports com.example.eigen;
}