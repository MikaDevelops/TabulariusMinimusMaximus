package mikan.tabulariusminimusmaximus;

import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mikan.tabulariusminimusmaximus.guielements.MenuBarElement;
import mikan.tabulariusminimusmaximus.guielements.SidePanel;


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
        
        // static menus
        MenuBar menu = MenuBarElement.getMenuBar();
        VBox sideMenu = SidePanel.getSidePanel();
                
        BorderPane pane = new BorderPane();
        pane.setTop(menu);
        pane.setLeft(sideMenu);
        pane.setCenter(leibeli);
        Scene scene = new Scene(pane);
        
        stage.setTitle("Tabularius Minimus Maximus");
        stage.getIcons().add(new Image(new File("./images/logo.png").toURI().toString()));
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}