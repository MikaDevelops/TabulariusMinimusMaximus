package mikan.tabulariusminimusmaximus.guielements;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mikan.tabulariusminimusmaximus.DataBase;
import mikan.tabulariusminimusmaximus.HashChecksum;
import mikan.tabulariusminimusmaximus.datamodel.*;

/**
 *
 * @author mika
 */
public class JournalAdd {
    
    /**
     * Returns view for journal entry adding.
     * @param id Previous journal id.
     * @param stage 
     * @param scene 
     * @param db DataBase instance.
     * @return GridPane to be shown in main area.
     */
    public static GridPane getJournalAddView( int id, Stage stage, Scene scene ){
        IndexCalculator indexCalculator = new IndexCalculator();
        DataBase db = new DataBase();
        int nextEntryID = id+1;
        
        HashMap<String, String> addedFields;
        
        GridPane journalView = new GridPane();
        journalView.setPadding(new Insets(10,10,10,20));
        journalView.setHgap(15);
        journalView.setVgap(5);
        
        FileChooser fileChooser = new FileChooser();
        ArrayList<String> chosenFiles = new ArrayList<>();
        
        Label tositeIDLabel = new Label("Tositenumero");
        Label tositeTiedostoLabel = new Label("Tositetiedosto");
        Label tositepvmLabel = new Label("Päivämäärä");
        Label seliteLabel = new Label("Selite");
        Label perTiliLabel = new Label("Tili debet / per");
        Label anTiliLabel = new Label("Tili kredit / an");
        Label tiedostoLabel = new Label("Tositetiedosto");
        
        Label tositeIDText = new Label(Integer.toString(nextEntryID));
        Button chooseFilesBtn = new Button("Valitse tositetiedosto");
        Label valittuTiedostoLabel = new Label();
        DatePicker datePicker = new DatePicker();
        TextField seliteTextField = new TextField();
        ComboBox perTiliCmbBox = new ComboBox();
        ComboBox anTiliCmbBox = new ComboBox();
        

        TextField debetTextField = new TextField();
        TextField kreditTextField = new TextField();
        Button addRowBtn = new Button("lisää rivi");
        Button saveBtn = new Button("Tallenna\nmerkintä\npäiväkirjaan");
        
        
        // Combobox lists
        // Accounts from database
        ArrayList<Account> accounts = db.getAccountList();
        ArrayList<String> accountListing = new ArrayList<>();
        for (int i=0; i < accounts.size(); i++){
            accountListing.add(Integer.toString(accounts.get(i).accountNumber)+" "
                    + "ALV" + Integer.toString(accounts.get(i).vatBase)+" "
                    + accounts.get(i).name);
        }
        ObservableList<String> obsAccounts = FXCollections.observableArrayList(accountListing);
        perTiliCmbBox.setItems(obsAccounts);
        anTiliCmbBox.setItems(obsAccounts);
        
        // event handlers
        chooseFilesBtn.setOnAction(e->{
            File file = fileChooser.showOpenDialog(stage);
            if (file != null){      
                valittuTiedostoLabel.setText(file.getName());
            }
        });
        
        addRowBtn.setOnAction(e->{
            
            TextField seliteAddTxtField = new TextField();
            TextField addPerEurTxtField = new TextField();
            TextField addAnEurTxtField  = new TextField();
            ComboBox addPerCmb = new ComboBox(obsAccounts);
            ComboBox addAnCmb = new ComboBox(obsAccounts);
            
            // IDs for extra fields
            seliteAddTxtField.setId("seliteAdded"+indexCalculator.idIndex());
            addPerEurTxtField.setId("perEurAdded"+indexCalculator.idIndex());
            addAnEurTxtField.setId("anAdded"+indexCalculator.idIndex());
            addPerCmb.setId("perCmbAdded"+indexCalculator.idIndex());
            addAnCmb.setId("anCmbAdded"+indexCalculator.idIndex());
            
            journalView.add(new Label("Selite"), 0, indexCalculator.value());
            journalView.add(seliteAddTxtField,   1, indexCalculator.value(), 2, 1);
            
            journalView.add(new Label("Tili debet/per"),0, indexCalculator.value()+1);
            journalView.add(addPerCmb,                  1, indexCalculator.value()+1);
            
            journalView.add(new Label("debet/per"), 0, indexCalculator.value()+2);
            journalView.add(addPerEurTxtField,      1, indexCalculator.value()+2);
            
            journalView.add(new Label("Tili kredit/an"), 0, indexCalculator.value()+3);
            journalView.add(addAnCmb,                    1, indexCalculator.value()+3);
            
            journalView.add(new Label("kredit/an"), 0, indexCalculator.value()+4);
            journalView.add(addAnEurTxtField,      1, indexCalculator.value()+4);
            
            // Move add row button and save button
            journalView.getChildren().remove(addRowBtn);
            journalView.add(addRowBtn, 0, indexCalculator.value()+5);
            
            journalView.getChildren().remove(saveBtn);
            journalView.add(saveBtn, 0, indexCalculator.value()+6);
            
            TextField tx = (TextField)scene.lookup("#seliteAdded"+indexCalculator.idIndex());
            System.out.println(scene.lookup("#seliteAdded"+indexCalculator.idIndex()).getId() + tx.getText());
            
            indexCalculator.increment();
        });
        
        saveBtn.setOnAction(e->{
            
            /*
            nextEntryID,
            datePicker.getValue(),
            valittuTiedostoLabel.getText(),
            */
            
            // Get hash from previous journal document.
            String previousJournalHash = db.getSavedHash(HashedDataBaseTables.TOSITE, id);
            
           
            
            // ArrayList for row objects.
            ArrayList<JournalRow> arrayForRow = new ArrayList<>();
            
            // Handle first line comboboxes
            String[] perTiliValues = ((String)perTiliCmbBox.getValue()).split(" ");
            String[] anTiliValues = ((String)anTiliCmbBox.getValue()).split(" ");
            
            // add the first row into the database
            JournalRow firstRow = new JournalRow (
                    db.getLastID(TableIDfield.TAPAHTUMARIVI)+1,
                    seliteTextField.getText(),
                    Integer.parseInt( debetTextField.getText() ),
                    Integer.parseInt( kreditTextField.getText() ),
                    Integer.parseInt( perTiliValues[0] ),
                    Integer.parseInt( anTiliValues[0] ),
                    perTiliValues[2],
                    anTiliValues[2],
                    nextEntryID,
                    ""
            );
            
            // count hash
            HashChecksum firstRowChecksum = new HashChecksum(firstRow, db.getSavedHash(HashedDataBaseTables.TOSITE, id));
            try {
                firstRow.tarkiste = firstRowChecksum.countHash();
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            } catch (IllegalAccessException ex) {
                System.out.println(ex.getMessage());
            } catch (NoSuchAlgorithmException ex) {
                System.out.println(ex.getMessage());
            }
            
            // go through all the rest rows
            for (int i = 0; i < indexCalculator.idIndex(); i++){
                
            }
            
            // Journal row elements
            // Hash from new journal document
            
            // Add ArrayList to journal document.
            
            // Journal document to database.
            
        });
        
        journalView.add(tositeIDLabel, 0, 0);
        journalView.add(tositeTiedostoLabel, 0, 1);
        journalView.add(tiedostoLabel, 0, 2);
        journalView.add(tositepvmLabel, 0, 3);
        journalView.add(seliteLabel, 0, 4);
        journalView.add(perTiliLabel, 0, 5);
        journalView.add(new Label("debet / per"), 0, 6);
        journalView.add(anTiliLabel, 0, 7);
        journalView.add(new Label("kredit / an"), 0, 8);
        journalView.add(addRowBtn, 0, 9);
        journalView.add(saveBtn, 0, 10);
        
        journalView.add(tositeIDText, 1, 0);
        journalView.add(chooseFilesBtn, 1, 1);
        journalView.add(valittuTiedostoLabel, 1, 2);
        journalView.add(datePicker, 1, 3);
        journalView.add(seliteTextField, 1, 4, 2, 1);
        journalView.add(perTiliCmbBox, 1, 5, 2, 1);
        journalView.add(debetTextField, 1, 6);
        journalView.add(anTiliCmbBox, 1, 7, 2, 1);
        journalView.add(kreditTextField, 1, 8);
        journalView.add(new Label("eur"), 2, 6);
        journalView.add(new Label("eur"), 2, 8);
        
        return journalView;
    }
    
}
