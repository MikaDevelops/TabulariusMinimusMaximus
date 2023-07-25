package mikan.tabulariusminimusmaximus.datamodel;

import java.util.ArrayList;

/**
 *
 * @author mika
 */
public class Journal {
    
    public String                   selite;
    public int                      tapahtumaID;
    public String                   tarkiste;
    public ArrayList<JournalRow>    tapahtumarivi;
    public JournalDocument          tosite;

    // constructors
    public Journal(){}
    public Journal(String selite, int tapahtumaID, String tarkiste, ArrayList<JournalRow> tapahtumarivi, JournalDocument tosite) {
        this.selite = selite;
        this.tapahtumaID = tapahtumaID;
        this.tarkiste = tarkiste;
        this.tapahtumarivi = tapahtumarivi;
        this.tosite = tosite;
    }
}
