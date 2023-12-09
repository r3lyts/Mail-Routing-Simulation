package HelperClasses;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.stage.Screen;
import javafx.stage.Stage;


import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        centerStage(stage);
    }

    public static void centerStage(Stage stage) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Calculate the center position
        double centerXPosition = screenBounds.getMinX() + (screenBounds.getWidth() - stage.getWidth()) / 2;
        double centerYPosition = screenBounds.getMinY() + (screenBounds.getHeight() - stage.getHeight()) / 2;

        // Set position of stage
        stage.setX(centerXPosition);
        stage.setY(centerYPosition);
    }

    /**
     * Generate Time slots for any combo box that needs time.
     */
    public static List<String> generateTimeSlots() {
        List<String> timeSlots = new ArrayList<>();
        LocalTime startTime = LocalTime.of(8, 0); // Starting at 8:00
        LocalTime endTime = LocalTime.of(23, 45); // Ending at 23:45
        LocalTime time = startTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        do {
            timeSlots.add(time.format(formatter));
            time = time.plusMinutes(15);
        } while (!time.isAfter(endTime) && !time.isBefore(startTime));

        return timeSlots;
    }

    public static Instant convertLocalToUTC(LocalDateTime localDateTime) {
        ZoneId localZoneId = ZoneId.systemDefault(); // Gets the system's default time zone
        ZonedDateTime localZonedDateTime = ZonedDateTime.of(localDateTime, localZoneId);
        return localZonedDateTime.withZoneSameInstant(ZoneOffset.UTC).toInstant();
    }

    public static LocalDateTime convertUTCToLocal(Instant utcInstant) {
        ZoneId localZoneId = ZoneId.systemDefault(); // Gets the system's default time zone
        ZonedDateTime localZonedDateTime = utcInstant.atZone(ZoneOffset.UTC).withZoneSameInstant(localZoneId);
        return localZonedDateTime.toLocalDateTime();
    }

    public static LocalDateTime combineDateAndTime(LocalDate localDate, String timeString) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime time = LocalTime.parse(timeString, timeFormatter);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, time);

        return localDateTime;
    }

    public static ZonedDateTime convertLocalToEastern(LocalDateTime localDateTime) {
        ZoneId localZoneId = ZoneId.systemDefault(); // Local time zone
        ZoneId easternZoneId = ZoneId.of("America/New_York"); // Eastern Time zone

        ZonedDateTime localZonedDateTime = localDateTime.atZone(localZoneId);
        ZonedDateTime easternZonedDateTime = localZonedDateTime.withZoneSameInstant(easternZoneId);

        return easternZonedDateTime;
    }



}
