package mikan.tabulariusminimusmaximus.guielements;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author mika
 */
public class JournalAdd {
    public static GridPane getJournalAddView( int id ){
        
        int nextEntryID = id+1;
        
        GridPane journalView = new GridPane();
        journalView.setPadding(new Insets(10,10,10,20));
        journalView.setHgap(15);
        journalView.setVgap(5);
        
        Label tositeIDLabel = new Label("Tositenumero");
        Label tositeIDText = new Label(Integer.toString(nextEntryID));
        
        Label test = new Label("koekenttä1");
        TextField testField = new TextField();
        journalView.add(tositeIDLabel, 0, 0);
        journalView.add(tositeIDText, 1, 0);
        return journalView;
    }
}
