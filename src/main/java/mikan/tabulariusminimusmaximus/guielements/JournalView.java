package mikan.tabulariusminimusmaximus.guielements;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import mikan.tabulariusminimusmaximus.datamodel.*;

/**
 *
 * @author mika
 */
public class JournalView {
    public static ScrollPane getJournalView(ArrayList<Journal> journalEntries){

        ScrollPane journalScrollView = new ScrollPane();
        VBox journalView = new VBox();
        journalView.setPadding(new Insets(10,10,10,20));
        
        Label test = new Label("koekenttä1");
        TextField testField = new TextField();

        
        for (int i = 0; i < journalEntries.size(); i++){

            GridPane taulu = new GridPane();
            taulu.add(new Text("Päiväys:  "), 0, 0);
            taulu.add(new Text("Tosite numero:  "), 0, 1);
            taulu.add(new Text("Selite:  "), 0, 2);
            taulu.add(new Text("Kuvalinkki:  "), 0, 3);
            
            taulu.add(new Text(journalEntries.get(i).tosite.pvm), 1, 0);
            taulu.add(new Text(Integer.toString(journalEntries.get(i).tosite.tositeID)), 1, 1);
            taulu.add(new Text(journalEntries.get(i).selite), 1, 2);
            taulu.add(new Text(journalEntries.get(i).tosite.kuvalinkki), 1, 3);
            
            GridPane rivit = new GridPane();
            rivit.setHgap(15);

            rivit.add(new Text("tili"),0,0);
            rivit.add(new Text("debet"),1,0);
            rivit.add(new Text("kredit"),2,0);
            
            for (int j = 0; j < journalEntries.get(i).tapahtumarivi.size(); j++){
                int row = j+1;
                rivit.add(new Text(Integer.toString(journalEntries.get(i).tapahtumarivi.get(j).tilinumero)),0,row);
                rivit.add(new Text(Integer.toString(journalEntries.get(i).tapahtumarivi.get(j).debet)),1,row);
                rivit.add(new Text(Integer.toString(journalEntries.get(i).tapahtumarivi.get(j).kredit)),2,row);
            }
            
            journalView.getChildren().addAll(taulu, rivit);
            
            if(i < journalEntries.size()){
                Separator separator = new Separator(Orientation.HORIZONTAL);
       
                journalView.getChildren().add(separator);
            }
        }
        
        journalScrollView.setContent(journalView);
        return journalScrollView;
    }
}
