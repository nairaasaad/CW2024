module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo.controller;
    opens User to javafx.fxml;
    opens level1 to javafx.fxml;
    opens level2 to javafx.fxml;
    opens com.example.demo.Actor to javafx.fxml;
}