module com.group16 {
    requires javafx.controls;
    requires java.sql;
    requires javafx.graphics;
    requires org.json;
    requires org.xerial.sqlitejdbc;
    requires jdk.compiler;

    opens com.group16.view to javafx.graphics;
    opens com.group16.tools to org.json;
    exports com.group16;
    opens com.group16 to javafx.graphics;
}
