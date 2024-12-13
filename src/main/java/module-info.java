module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo.controller;
    opens com.example.demo.User to javafx.fxml;
    //opens com.example.demo.level1 to javafx.fxml;
    //opens com.example.demo.level2 to javafx.fxml;
    opens com.example.demo.Actor to javafx.fxml;
    //opens com.example.demo.Projectile to javafx.fxml;
    opens com.example.demo.UI to javafx.fxml;
    exports com.example.demo;
    opens com.example.demo.Levels to javafx.fxml;
    exports com.example.demo.Levels;
}