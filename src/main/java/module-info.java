module com.group16 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.group16 to javafx.fxml;
    exports com.group16;
}
