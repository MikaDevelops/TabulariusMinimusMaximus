package mikan.tabulariusminimusmaximus;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import mikan.tabulariusminimusmaximus.DataBase.*;
import mikan.tabulariusminimusmaximus.datamodel.*;

/**
 *
 * @author mika
 */
public class DataBaseTest {
    
    
    public DataBaseTest() {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {

    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }
    

    /**
     * Test of checkDB method, of class DataBase.
     */
//    @org.junit.jupiter.api.Test
//    public void testCheckDB() {
//    }

    /**
     * Test of getLastDocumentID method, of class DataBase.
     */
//    @org.junit.jupiter.api.Test
//    public void testGetLastDocumentID() {
//        DataBase db = new DataBase();
//        int last = db.getLastID();
//        assertTrue(last>-1);
//    }
    
    /**
     * Test of getLastID method, of class DataBase.
     */
    @org.junit.jupiter.api.Test
    public void testGetgetSavedHash() {
        DataBase db = new DataBase();
        int id = 0;
        String table = "";
        String expected = "8368f5f06e1b362211890e7717c274ce".toUpperCase();
        String hash = db.getSavedHash(HashedDataBaseTables.TOSITE, 0);
        assertEquals(expected, hash);
    }
    
}
