package mikan.tabulariusminimusmaximus.datamodel;

/**
 *
 * @author mika
 */
public class JournalRow {
    
    public int riviID;
    public String selite;
    public int debet;
    public int kredit;
    public int tilinumeroPer;
    public int tilinumeroAn;
    public String tilinimiPer;
    public String tilinimiAn;
    public int tositeID;
    public String tarkiste;
    public String pvm ="";
    
    public JournalRow(){}

    public JournalRow(int riviID, String selite, int debet,
            int kredit, int tilinumeroPer, int tilinumeroAn,
            String tilinimiPer, String tilinimiAn, int tositeID,
            String tarkiste) {
        
        this.riviID = riviID;
        this.selite = selite;
        this.debet = debet;
        this.kredit = kredit;
        this.tilinumeroPer = tilinumeroPer;
        this.tilinumeroAn = tilinumeroAn;
        this.tilinimiPer = tilinimiPer;
        this.tilinimiAn = tilinimiAn;
        this.tositeID = tositeID;
        this.tarkiste = tarkiste;
    }
    
}
