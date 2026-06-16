module com.example.interactivechessguinea {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.interactivechessguinea to javafx.fxml;
    exports com.example.interactivechessguinea;
}