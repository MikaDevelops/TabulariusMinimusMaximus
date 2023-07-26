package mikan.tabulariusminimusmaximus.guielements;

import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.util.Locale;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
        Label tositepvmLabel = new Label("Päivämäärä");
        Label seliteLabel = new Label("Selite");
        Label tiliLabel = new Label("Tili");
        
        Label tositeIDText = new Label(Integer.toString(nextEntryID));
        DatePicker datePicker = new DatePicker();
        TextField seliteTextField = new TextField();
        ComboBox tiliCmbBox = new ComboBox();
        
        journalView.add(tositeIDLabel, 0, 0);
        journalView.add(tositepvmLabel, 0, 1);
        journalView.add(seliteLabel, 0, 2);
        journalView.add(tiliLabel, 0, 3);
        
        journalView.add(tositeIDText, 1, 0);
        journalView.add(datePicker, 1, 1);
        journalView.add(seliteTextField, 1, 2);
        journalView.add(tiliCmbBox, 1, 3);
        
        
        return journalView;
    }
}
