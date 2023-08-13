package mikan.tabulariusminimusmaximus.guielements;

import java.io.File;
import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import mikan.tabulariusminimusmaximus.datamodel.*;

/**
 *
 * @author mika
 */
public class JournalAdd {
    public static GridPane getJournalAddView( int id, Stage stage, Scene scene ){
        
        int nextEntryID = id+1;
        int[] rowPointers = {7,8,9};
        int indexForField = 1;
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
        Label tiliLabel = new Label("Tili");
        Label tiedostoLabel = new Label("Tositetiedosto");
        
        Label tositeIDText = new Label(Integer.toString(nextEntryID));
        Button chooseFilesBtn = new Button("Valitse tositetiedosto");
        Label valittuTiedostoLabel = new Label();
        DatePicker datePicker = new DatePicker();
        TextField seliteTextField = new TextField();
        ComboBox tiliCmbBox = new ComboBox();
        ComboBox perAnCmbBox = new ComboBox();
        TextField eur = new TextField();
        Button addRowBtn = new Button("lisää rivi");
        Button saveBtn = new Button("Tallenna\nmerkintä\npäiväkirjaan");
        
        
        // Combobox lists
        // Accounts from database
        DataBase db = new DataBase();
        ArrayList<Account> accounts = db.getAccountList();
        ArrayList<String> accountListing = new ArrayList<>();
        for (int i=0; i < accounts.size(); i++){
            accountListing.add(Integer.toString(accounts.get(i).accountNumber)+" "
                    + "ALV" + Integer.toString(accounts.get(i).vatBase)+" "
                    + accounts.get(i).name);
        }
        ObservableList<String> obsAccounts = FXCollections.observableArrayList(accountListing);
        tiliCmbBox.setItems(obsAccounts);
        // perAn
        ArrayList<String> perAnArray = new ArrayList<>();
        perAnArray.addAll(Arrays.asList(new String[] {"debet","kredit"}));
        ObservableList<String> obsPerAn = FXCollections.observableArrayList(perAnArray);
        perAnCmbBox.setItems(obsPerAn);
        
        // event handlers
        chooseFilesBtn.setOnAction(e->{
            File file = fileChooser.showOpenDialog(stage);
            if (file != null){      
                valittuTiedostoLabel.setText(file.getName());
            }
        });
        addRowBtn.setOnAction(e->{
            
            TextField seliteAddTxtField = new TextField();
            TextField addEurTxtField = new TextField();
            ComboBox addPerAnCmb = new ComboBox();
            
            ArrayList<String> perAnAdd = new ArrayList<>();
            perAnAdd.addAll(Arrays.asList(new String[] {"debet","kredit"}));
            ObservableList<String> obsAddPerAn = FXCollections.observableArrayList(perAnAdd);
            addPerAnCmb.setItems(obsPerAn);
            
            // IDs for extra fields
//            seliteAddTxtField.setId("seliteAdded"+Integer.toString(indexForField));
//            addEurTxtField.setId("eurAdded"+Integer.toString(indexForField));
//            addPerAnCmb.setId("perAnAdded"+Integer.toString(indexForField));
//            indexForField++;
            
            
            //journalView.getChildren().remove(addRowBtn);
            TextField testii = (TextField)scene.lookup("#testid");
            System.out.println(testii.getText());
        });
        saveBtn.setOnAction(e->{
            //TODO
            
        });
        
        journalView.add(tositeIDLabel, 0, 0);
        journalView.add(tositeTiedostoLabel, 0, 1);
        journalView.add(tiedostoLabel, 0, 2);
        journalView.add(tositepvmLabel, 0, 3);
        journalView.add(seliteLabel, 0, 4);
        journalView.add(tiliLabel, 0, 5);
        journalView.add(perAnCmbBox, 0, 6);
        journalView.add(addRowBtn, 0, 7);
        journalView.add(saveBtn, 0, 8);
        
        journalView.add(tositeIDText, 1, 0);
        journalView.add(chooseFilesBtn, 1, 1);
        journalView.add(valittuTiedostoLabel, 1, 2);
        journalView.add(datePicker, 1, 3);
        journalView.add(seliteTextField, 1, 4);
        journalView.add(tiliCmbBox, 1, 5);
        journalView.add(eur, 1, 6);
        

        
        return journalView;
    }
    
}
