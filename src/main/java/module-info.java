module com.group16 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.group16.controller to javafx.fxml;
    exports com.group16;
}
