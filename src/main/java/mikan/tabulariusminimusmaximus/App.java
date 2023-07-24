package mikan.tabulariusminimusmaximus;

import java.io.File;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mikan.tabulariusminimusmaximus.guielements.JournalAdd;
import mikan.tabulariusminimusmaximus.guielements.MenuBarElement;



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
        
        BorderPane pane = new BorderPane();
        
        Label leibeli = new Label("tekstiä testauksen tähren");
        
        Label leibeli2 = new Label("tekstiä testauksen tähren333");
        
        // static menus
        MenuBar menu = MenuBarElement.getMenuBar();
        
        Button paivakirja = new Button("Kirjaa tapahtuma päiväkirjaan");
        Button paivakirjaLue = new Button("Tarkastele päiväkirjamerkintöjä");
        
        paivakirja.setOnAction(e->{
            int lastEntryID = db.getLastDocumentID();
            if(lastEntryID > -1) {
                pane.setCenter(JournalAdd.getJournalAddView( lastEntryID ));
            }else {pane.setCenter(new Label("Tietokanta viallinen"));}
        });
        
        paivakirjaLue.setOnAction(e->{
            pane.setCenter(leibeli);
        });
        
        VBox sideMenu = new VBox(4);
        sideMenu.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,CornerRadii.EMPTY, Insets.EMPTY)));
        sideMenu.getChildren().addAll(paivakirja, paivakirjaLue);
        
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