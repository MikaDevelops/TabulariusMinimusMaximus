package mikan.tabulariusminimusmaximus.datamodel;

import java.util.ArrayList;

/**
 *
 * @author mika
 */
public class JournalDocument {
    
    public int    tositeID;
    public String pvm;
    public String kuvalinkki;
    public String tarkiste;
    
    
    public JournalDocument(){}
    /**
     * Constructor for JournalDocument object.
     * @param tositeID      int
     * @param pvm           String date for document
     * @param kuvalinkki    ArrayList of String
     * @param tarkiste      String checksum for object fields
     */
    public JournalDocument(int tositeID, String pvm, String kuvalinkki, String tarkiste){
        this.tositeID   = tositeID;
        this.pvm        = pvm;
        this.kuvalinkki = kuvalinkki;
        this.tarkiste   = tarkiste;
    }
}
