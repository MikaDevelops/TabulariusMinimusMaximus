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
    public static ScrollPane getJournalView(ArrayList<JournalRow> journalEntries){

        ScrollPane journalScrollView = new ScrollPane();
        VBox journalView = new VBox();
        journalView.setPadding(new Insets(10,10,10,20));

        
        for (int i = 0; i < journalEntries.size(); i++){

            GridPane taulu = new GridPane();
            taulu.add(new Text("Päiväys:  "), 0, 0);
            taulu.add(new Text("Tosite numero:  "), 0, 1);
            taulu.add(new Text("Selite:  "), 0, 2);
            taulu.add(new Text("Kuvalinkki:  "), 0, 3);
            
            taulu.add(new Text(journalEntries.get(i).pvm), 1, 0);
            taulu.add(new Text(Integer.toString(journalEntries.get(i).tositeID)), 1, 1);
            taulu.add(new Text(journalEntries.get(i).selite), 1, 2);


            GridPane rivit = new GridPane();
            rivit.setHgap(20);

            rivit.add(new Text("tili"),0,0);
            rivit.add(new Text("tilinimi"),1,0);
            rivit.add(new Text("debet"),2,0);
            rivit.add(new Text("kredit"),3,0);

            rivit.add(new Text(Integer.toString(journalEntries.get(i).tilinumero)),0,1);
            rivit.add(new Text(journalEntries.get(i).tilinimi),1,1);
            rivit.add(new Text(Integer.toString(journalEntries.get(i).debet)),2,1);
            rivit.add(new Text(Integer.toString(journalEntries.get(i).kredit)),3,1);

            
            journalView.getChildren().addAll(taulu, rivit);
            
            if(i < journalEntries.size()-1){
                Separator separator = new Separator(Orientation.HORIZONTAL);
       
                journalView.getChildren().add(separator);
            }
        }
        
        journalScrollView.setContent(journalView);
        return journalScrollView;
    }
}
