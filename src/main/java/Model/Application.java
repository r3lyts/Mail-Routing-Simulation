package Model;

import DAO.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("C195 Project - Tyler Small");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException{
        DBConnection.openConnection();
        launch();

        DBConnection.closeConnection();
    }
}