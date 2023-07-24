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
}
