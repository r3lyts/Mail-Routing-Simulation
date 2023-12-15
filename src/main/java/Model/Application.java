package Model;

import DAO.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author tylersmall
 * This is the main class and entry point into the program.
 *
 * LOCATION FOR LAMBDAs: Controller/.ReportController.aggregateAppointments method and Controller.ReportController.initialize method
 */
public class Application extends javafx.application.Application {
    /**
     * Launches the login screen as the first view of the app.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("C195 Project - Tyler Small");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This is the main method, the entry point into the program.
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException{
        DBConnection.openConnection();
        launch();

        DBConnection.closeConnection();
    }
}