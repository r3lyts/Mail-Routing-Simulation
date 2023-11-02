module com.c195.tylersmall.c195project_tylersmall {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.c195.tylersmall.c195project_tylersmall to javafx.fxml;
    exports com.c195.tylersmall.c195project_tylersmall;
}