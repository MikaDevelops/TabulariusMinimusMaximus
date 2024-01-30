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
    @org.junit.jupiter.api.Test
    public void testGetLastDocumentID() {
        DataBase db = new DataBase();
        int last = db.getLastDocumentID();
        assertTrue(last>-1);
    }
    
    /**
     * Test of getLastDocumentID method, of class DataBase.
     */
    @org.junit.jupiter.api.Test
    public void testGetgetSavedHash() {
        DataBase db = new DataBase();
        int id = 0;
        String table = "";
        String expected = "D1FEEA7D7BECB110087E1463868A3AC4";
        String hash = db.getSavedHash(HashedDataBaseTables.PAIVAKIRJA, 0);
        assertEquals(expected, hash);
    }
    
}
