package mikan.tabulariusminimusmaximus.datamodel;

/**
 *
 * @author mika
 */
public class JournalRow {
    
    public int riviID;
    public int debet;
    public int kredit;
    public int tilinumero;
    public String tilinimi;
    public String tarkiste;
    
    public JournalRow(){}

    public JournalRow(int riviID, int debet, int kredit, int tilinumero, String tilinimi, String tarkiste) {
        this.riviID = riviID;
        this.debet = debet;
        this.kredit = kredit;
        this.tilinumero = tilinumero;
        this.tilinimi = tilinimi;
        this.tarkiste = tarkiste;
    }
    
}
