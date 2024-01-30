/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mikan.tabulariusminimusmaximus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import mikan.tabulariusminimusmaximus.datamodel.*;

/**
 * Handles SQLite database actions.
 * @author mika
 */
public class DataBase {
    
    private String pathToDB = "./data/database.db";
    private String dbUrl = "jdbc:sqlite:"+pathToDB;

    private Connection getConnection(){
        Connection conn = null;
        try{ conn = DriverManager.getConnection(dbUrl); }
        catch (SQLException e){ System.out.println(e.getMessage()); }
        return conn;
    }
    
    private Statement getStatement(){
        Connection conn = this.getConnection();
        Statement statement = null;
        try { statement = conn.createStatement(); }
        catch (SQLException ex) { System.out.println(ex.getMessage()); }
        return statement;
    }
    
    private PreparedStatement getPreparedStatement(String sqlString){
        Connection conn = this.getConnection();
        PreparedStatement statement = null;
        try { statement = conn.prepareStatement(sqlString); }
        catch (SQLException ex) { System.out.println(ex.getMessage()); }
        return statement;
    }
    
    private void closeStatement(Statement statement){
        if (statement != null){
            try{
                Connection conn = statement.getConnection();
                statement.close();
                conn.close();
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    private void createDB () {
        
        // Datamodel. Järjestyksellä on väliä. Taulut joihin viitataan ensin.
        String[] tables1 = {
            "CREATE TABLE tosite (tositeID INT PRIMARY KEY NOT NULL, pvm TEXT NOT NULL, kuvalinkki TEXT NOT NULL, tarkiste TEXT NOT NULL)",
            "CREATE TABLE alvkannat (prosentti INT PRIMARY KEY NOT NULL, selite TEXT NOT NULL)",
            "CREATE TABLE ylakategoria (ylakategoriaID INT PRIMARY KEY NOT NULL, nimi TEXT NOT NULL, paattyy TEXT NOT NULL)",
            "CREATE TABLE tilikategoria (kategoriaID INT PRIMARY KEY NOT NULL, ylakategoriaID INT NOT NULL, nimi TEXT NOT NULL, FOREIGN KEY(ylakategoriaID) REFERENCES ylakategoria(ylakategoriaID))"

        };
        String[] tables2 = {
            "CREATE TABLE tilikartta (tilinumero INT PRIMARY KEY NOT NULL, kategoriaID INT NOT NULL, alvkanta INT, nimi TEXT NOT NULL, kuvaus TEXT, FOREIGN KEY(kategoriaID) REFERENCES tilikategoria(kategoriaID), FOREIGN KEY(alvkanta) REFERENCES alvkannat(prosentti))",
            "CREATE TABLE paivakirja (tapahtumaID INT PRIMARY KEY NOT NULL, tositeID INT NOT NULL, selite TEXT NOT NULL, tarkiste TEXT NOT NULL, FOREIGN KEY(tositeID) REFERENCES tosite(tositeID))"
        };
        String[] tables3 = {
            "CREATE TABLE paakirja (riviID INT PRIMARY KEY NOT NULL, tilinumero INT NOT NULL, vuosi INT NOT NULL, kuukausi INT NOT NULL, debet INT, kredit INT, tarkiste TEXT NOT NULL, FOREIGN KEY(tilinumero) REFERENCES tilikartta(tilinumero))",
            "CREATE TABLE tapahtumarivi (riviID INT PRIMARY KEY NOT NULL, tapahtumaID INT NOT NULL, tilinumero INT NOT NULL, debet INT, kredit INT, tarkiste TEXT NOT NULL, FOREIGN KEY(tapahtumaID) REFERENCES paivakirja(tapahtumaID), FOREIGN KEY(tilinumero) REFERENCES tilikartta(tilinumero))"
        };
        
        // Starting data.
        String[] startData = {
                "INSERT INTO alvkannat(prosentti, selite) VALUES "
                +"(0, 'Ei ALV'), "
                +"(10, 'Alennettu: kirjat, lehdet, lääkkeet, henkilökuljetus, majoitus'), "
                +"(14, 'Alennettu: elintarvikkeet, rehut, ravintolapalvelut'), "
                +"(24, 'Yleinen verokanta')",
            
                "INSERT INTO tosite(tositeID, pvm, kuvalinkki, tarkiste) VALUES "
                +"(0, '2023-07-23', 'tietokannanaloitus.jpg', '8368f5f06e1b362211890e7717c274ce')",
                
                "INSERT INTO paivakirja(tapahtumaID, tositeID, selite, tarkiste) VALUES "
                +"(0, 0, 'tietokannan pystytys', 'd1feea7d7becb110087e1463868a3ac4')",
                
                "INSERT INTO ylakategoria(ylakategoriaID, nimi, paattyy) VALUES "
                +"(1, 'Pysyvät vastaavat', 'tase'), "
                +"(2, 'Vaihtuvat vastaavat', 'tase'), "
                +"(3, 'Oma pääoma', 'tase'), "
                +"(4, 'Vieras pääoma', 'tase'), "
                +"(5, 'Liikevaihto', 'tulos'), "
                +"(6, 'Materiaalit ja palvelut', 'tulos'), "
                +"(7, 'Henkilöstökulut', 'tulos'), "
                +"(8, 'Henkilöstösivukulut', 'tulos'), "
                +"(9, 'Poistot ja arvonalenemiset', 'tulos'), "
                +"(10, 'Liiketoiminnan muut kulut', 'tulos'), "
                +"(11, 'Rahoitustuotot ja rahoituskulut', 'tulos')",
                
                "INSERT INTO tilikategoria(kategoriaID, ylakategoriaID, nimi) VALUES "
                +"(1, 1, 'aineelliset hyödykkeet'), "
                +"(2, 2, 'vaihto-omaisuus'), "
                +"(3, 2, 'saamiset, lyhytaikaiset'), "
                +"(4, 2, 'rahat ja pankkisaamiset'), "
                +"(5, 3, 'oma pääoma'), "
                +"(6, 4, 'pitkäaikainen'), "
                +"(7, 4, 'lyhytaikainen'), "
                +"(8, 5, 'liikevaihto'), "
                +"(9, 5, 'liiketoiminnan muut tuotot'), "
                +"(10, 6, 'aineet, tarvikkeet ja tavarat'), "
                +"(11, 7, 'palkat ja palkkiot'), "
                +"(12, 8, 'eläkekulut'), "
                +"(13, 8, 'muut henkilöstösivukulut'), "
                +"(14, 9, 'suunnitelman mukaiset poistot'), "
                +"(15, 10, 'liiketoiminnan muut kulut'), "
                +"(16, 11, 'muut korko- ja rahoitustuotot'), "
                +"(17, 11, 'korkokulut ja muut rahoituskulut')",
                
                "INSERT INTO tilikartta(tilinumero, kategoriaID, nimi, kuvaus, alvkanta) VALUES "
                +"(1161, 1, 'Kalusto', 'kaluston ja koneiden hankintamenot', 24), "
                +"(1531, 2, 'Tavaravarasto', 'Vaihto-omaisuus, varaston lisäys tai vähennys', 0), "
                +"(1701, 3, 'Myyntisaamiset', 'Asiakkaille lähetty laskut, hyvityslaskut, suoritukset', 24), "
                +"(1760, 3, 'Hankintojen alv-saamiset', 'vähennettävä alv', 0), "
                +"(1849, 3, 'Siirtosaamiset', 'avustavien päätösvientien tulojäämät ja menoennakot', 0), "
                +"(1900, 4, 'Kassa', '', 0), "
                +"(1910, 4, 'Pankkitili', '', 0), "
                +"(2201, 5, 'Oma pääoma', 'kirjataan oma pääoma tilikauden alussa, yksityiskäyttö vähentää', 0), "
                +"(2361, 5, 'Yksityissijoitukset', 'tilikauden aikana tapahtuvat omistajan lisäsijoitukset', 0), "
                +"(2365, 5, 'Yksityiskäyttö', 'yksityiskäyttö rahana tai tavarana', 0), "
                +"(2371, 5, 'Tilikauden tulos', 'tilikauden voitto tai tappio', 0), "
                +"(2621, 6, 'Lainat', 'lainojen nostot ja takaisinmaksut', 0), "
                +"(2821, 7, 'Lainojen lyhennyserät', 'tilikaudella erääntyvät lainojen lyhennykset', 0), "
                +"(2841, 7, 'Pankkitili luotollinen', 'tilikauden päättyessä jos saldoa, siirretään vastaavaa rahat ja pankkisaamiset', 0), "
                +"(2871, 7, 'Ostovelat', 'vaihto-omaisuus, kalusto, tuotannontekijähankinnat laskut ja niiden maksut', 0), "
                +"(2921, 7, 'Ennakonpidätysvelka', 'palkoista toimitetut ennakonpidätykset, siirrot verotilitapahtumiin', 0), "
                +"(2923, 7, 'Sosiaaliturvamaksuvelka', 'palkoista maksettavat sosiaaliturvamaksut ja siirrot verotilitapahtumiin', 0), "
                +"(2936, 7, 'Myynnin alv-velka', 'myynneistä suoritettava alv', 0), "
                +"(2939, 7, 'Tilitettävä alv-velka', 'alv-saamisten ja velkojen saldot, maksettavat siirrot verotilitapahtumiin', 0), "
                +"(2948, 7, 'Verotilitapahtumat', 'kausiveroilmoituksen mukaan siirto ennakoista, sossuista ja alveista. Maksut verottajalle', 0), "
                +"(2949, 7, 'Muut lyhytaikaiset velat', 'taseessa ilmoitetaan kohtaan Muut velat', 0), "
                +"(2979, 7, 'Siirtovelat', 'avustavina päätösvienteinä menojäämät ja tuloennakot', 0), "
                +"(3000, 8, 'Myynnit', '', 24), "
                +"(3500, 8, 'Annetut käteisalennukset', '', 24), "
                +"(3790, 9, 'Vuokratulot', '', 0), "
                +"(4000, 10, 'Ostot', 'vaihto-omaisuusostot', 24), "
                +"(4230, 10, 'Saadut käteisalennukset', '', 24), "
                +"(4410, 10, 'Varaston muutos', 'tilikauden lopussa varaston lisäys tai vähennys', 0), "
                +"(5000, 11, 'Palkat', '', 0), "
                +"(6130, 12, 'Eläkevakuutusmaksut', 'palkoista johtuvat eläkevakuutusmaksut', 0), "
                +"(6140, 12, 'Perityt työeläkemaksut', 'työntekijöiltä perityt osuudet', 0), "
                +"(6300, 13, 'Sosiaaliturvamaksut', '', 0), "
                +"(6410, 13, 'Työttömyysvakuutusmaksut', '', 0), "
                +"(6420, 13, 'Perityt työttömyysvakuutusmaksut', '', 0), "
                +"(6430, 13, 'Muut henkilöstösivumenokulut', 'tapaturma- ja ryhmähenkivakuutusmaksut', 0), "
                +"(6870, 14, 'Kaluston poistot', 'suunnitelmanmukaiset poistot', 0), "
                +"(7230, 15, 'Vuokramenot', '', 0), "
                +"(7390, 15, 'Sähkömenot', '', 24), "
                +"(8070, 15, 'Mainosmenot', '', 24), "
                +"(8680, 15, 'Muut liikemenot', 'toimistotarvikkeet, posti, puhelin yms.', 24), "
                +"(8685, 15, 'Muut liikemenot alv0', 'sekalaiset liikemenot joissa ei vähennettävää alvia', 0), "
                +"(9250, 16, 'Korkotulot', 'viivästyskorot ja muut korkotulot', 0), "
                +"(9460, 17, 'Korkomenot', 'lainojen korot, maksetut viivästyskorot', 0), "
                +"(9690, 17, 'Muut rahoitusmenot', 'lainojen pankkitakausprovisiot, toimitusmaksut ym. menot', 0)",
                
                "INSERT INTO tapahtumarivi(riviID, tapahtumaID, debet, kredit, tilinumero, tarkiste) VALUES "
                +"(0, 0, 0, 0, 2201, '1bb6d008b9600db6b4b1e76720210980')",
                
                "INSERT INTO paakirja(riviID, tilinumero, vuosi, kuukausi, debet, kredit, tarkiste) VALUES "
                +"(0, 2201, 2023, 7, 0, 0, '674be30f03285cb40adb02b71f542148')"
        };
        
        Statement statement = this.getStatement();
        statement = this.makeBatchFromArray(statement, tables1);
        
        int[] response = null;
        boolean responseOK = false;
        
        try { 
            response = statement.executeBatch();
            responseOK = checkResponseIntArray(response);
        }
        catch (SQLException ex) { System.out.println(ex.getMessage()); }
        
        if(responseOK){
            statement = this.makeBatchFromArray(statement, tables2);
            try { 
                response = statement.executeBatch();
                responseOK = checkResponseIntArray(response);
            }
            catch (SQLException ex) { System.out.println(ex.getMessage()); }
        }
        
        if(responseOK){
            statement = this.makeBatchFromArray(statement, tables3);
            try { 
                response = statement.executeBatch();
                responseOK = checkResponseIntArray(response);
            }
            catch (SQLException ex) { System.out.println(ex.getMessage()); }
        }
        
        if(responseOK){
            statement = this.makeBatchFromArray(statement, startData);
            try { 
                response = statement.executeBatch();
                responseOK = checkResponseIntArray(response);
            }
            catch (SQLException ex) { System.out.println(ex.getMessage()); }
        }
        
        
        this.closeStatement(statement);
    }
    
    private boolean checkResponseIntArray(int[] response){
        if(response != null){
            
            // check if all values in array are -2
            int countMinusTwos = 0;
            for(int i = 0; i < response.length; i++){
                if(response[i] == -2) countMinusTwos += 1; 
            }
            if(countMinusTwos == response.length) return true;
            
            // check that all values are 0 or greater
            for(int i = 0; i < response.length; i++){
                if(response[i]<0) return false;
            }
            return true;
        }
        return false;
    }
    
    private Statement makeBatchFromArray(Statement statement, String[] array){
        for(int i = 0; i < array.length; i++){
        try { statement.addBatch(array[i]); }
        catch (SQLException ex) { System.out.println(ex.getMessage()); }
        }
        return statement;
    }
    
    /**
     * Checks if SQLite file can be found. If not found, runs createDB -method
     * that creates default database.
     */
    public void checkDB(){
        File file = new File(pathToDB);
        if (!file.exists()){
            this.createDB();
        }
    }
    
    /**
     * Gets the last id from document table (tosite).
     * @return int last id from document table. -1 if nothing returned.
     */
    public int getLastDocumentID () {
        int id = -1;
        Statement statement = this.getStatement();
        String sql = "SELECT tositeID FROM tosite ORDER BY tositeID DESC LIMIT 1";
        
        try {
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                id = result.getInt("tositeID");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        this.closeStatement(statement);
        return id;
    }
    
    /**
     * Selects journal markings from database.
     * @return ArrayList of Journal-objects
     */
    public ArrayList<Journal> getJournalEntries(){
        
        // First get journal documents and journal markings
        String sql = "SELECT tosite.tositeID, tosite.pvm, tosite.kuvalinkki, "
                + "tosite.tarkiste AS tositetarkiste, paivakirja.selite, paivakirja.tapahtumaID, "
                + "paivakirja.tarkiste AS paivakirjatarkiste FROM paivakirja "
                + "INNER JOIN tosite ON paivakirja.tositeID = tosite.tositeID";
        Statement statement = this.getStatement();
        try {
            ResultSet results = statement.executeQuery(sql);
            ArrayList journalEntries = new ArrayList<Journal>();
            while (results.next()){
            
                Journal journal = new Journal();
                journal.selite      = results.getString("selite");
                journal.tapahtumaID = results.getInt("tapahtumaID");
                journal.tarkiste    = results.getString("paivakirjatarkiste");
                journal.tosite      = new JournalDocument(
                        results.getInt("tositeID"),
                        results.getString("pvm"),
                        results.getString("kuvalinkki"),
                        results.getString("tositetarkiste")
                );
                
                sql = "SELECT * FROM tapahtumarivi WHERE tapahtumaID="+journal.tapahtumaID;
                ResultSet innerResult = statement.executeQuery(sql);
                
                ArrayList<JournalRow> journalRows = new ArrayList<JournalRow>();
                while (innerResult.next()){
                    JournalRow journalRowObject = new JournalRow();
                    journalRowObject.riviID = innerResult.getInt("riviID");
                    journalRowObject.tilinumero = innerResult.getInt("tilinumero");
                    journalRowObject.debet = innerResult.getInt("debet");
                    journalRowObject.kredit = innerResult.getInt("kredit");
                    journalRowObject.tarkiste = innerResult.getString("tarkiste");
                    
                    // get a human readable name for the account
                    sql = "SELECT nimi FROM tilikartta WHERE tilinumero="
                            +journalRowObject.tilinumero;
                    ResultSet accountName = statement.executeQuery(sql);
                    if (accountName.next())journalRowObject.tilinimi = accountName.getString("nimi");
                    
                    journalRows.add(journalRowObject);
                }
                journal.tapahtumarivi = journalRows;
                journalEntries.add(journal);
            }
            this.closeStatement(statement);
            return journalEntries;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        this.closeStatement(statement);
        return null;
    }
    
    /**
     * 
     * @return ArrayList of Accounts objects, containing all in account table
     */
    public ArrayList<Account> getAccountList(){
    
        ArrayList<Account> accounts = new ArrayList<>();
        Statement statement = this.getStatement();
        String sqlString = "SELECT * FROM tilikartta";
        
        try {
            ResultSet result = statement.executeQuery(sqlString);
            while (result.next()){
               accounts.add( new Account (
                       result.getInt("tilinumero"),
                       result.getInt("kategoriaID"),
                       result.getString("nimi"),
                       result.getString("kuvaus"),
                       result.getInt("alvkanta")   
               ) );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        this.closeStatement(statement);
        return accounts;
    }
    
    public String getSavedHash(String table, int id){
        
        String idField = "";
        switch (table){
            case "paivakirja":
                idField = "tapahtumaID";
                break;
            case "tosite":
                idField = "tositeID";
                break;
            case "tapahtumarivi":
                idField = "riviID";
                break;
        }
        
        try{
            String sqlString = "SELECT tarkiste FROM " + table
                    +" WHERE " + idField + "=" + id;
            Statement statement = this.getStatement();
            ResultSet result = statement.executeQuery(sqlString);
            String tarkiste = result.getString("tarkiste").toUpperCase();
            this.closeStatement(statement);
            return tarkiste;
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public void saveJournalEntries(){
        //TODO
    }
}
