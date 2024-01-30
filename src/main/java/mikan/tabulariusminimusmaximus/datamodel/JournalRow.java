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
    public int tilinumero;
    public String tilinimi;
    public int tositeID;
    public String tarkiste;
    public String pvm;
    
    public JournalRow(){}

    public JournalRow(int riviID, String selite, int debet, int kredit, int tilinumero, String tilinimi, int tositeID, String tarkiste) {
        this.riviID = riviID;
        this.selite = selite;
        this.debet = debet;
        this.kredit = kredit;
        this.tilinumero = tilinumero;
        this.tilinimi = tilinimi;
        this.tositeID = tositeID;
        this.tarkiste = tarkiste;
    }
    
}
