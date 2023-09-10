module com.example.sotnya {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.sotnya to javafx.fxml;
    exports com.example.sotnya;
}