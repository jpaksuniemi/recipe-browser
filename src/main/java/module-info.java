module com.group16 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;

    opens com.group16.controller to javafx.fxml;
    opens com.group16.view to javafx.graphics;
    exports com.group16;
}
