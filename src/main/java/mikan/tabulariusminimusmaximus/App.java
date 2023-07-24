package mikan.tabulariusminimusmaximus;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        
        DataBase db = new DataBase();
        
        // Check if database file exists. Creates a new database
        // if file not found.
        db.checkDB();
        
        Label leibeli = new Label("tekstiä testauksen tähren");

        BorderPane pane = new BorderPane();
        pane.setCenter(leibeli);
        Scene scene = new Scene(pane);
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}