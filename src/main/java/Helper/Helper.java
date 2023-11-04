package Helper;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Optional;

public class Helper {

    public static void displayAlert(String title, String header, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void nextView(String fxmlFile, ActionEvent event) throws IOException {
        // Load the new scene
        FXMLLoader loader = new FXMLLoader(Helper.class.getResource(fxmlFile));
        Parent scene = loader.load();

        // Get the stage from the event source
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(scene));
        stage.show();
    }
}
