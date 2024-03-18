module com.example.assignment04j {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.assignment04j to javafx.fxml;
    exports com.example.assignment04j;
    exports business;
    opens business to javafx.fxml;
    exports presentation;
    opens presentation to javafx.fxml;
    exports data;
    opens data to javafx.fxml;
}