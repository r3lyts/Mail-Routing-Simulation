module com.c195.tylersmall.c195project_tylersmall {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens Model to javafx.fxml;
    exports Model;
    exports Controller;
    opens Controller to javafx.fxml;
}