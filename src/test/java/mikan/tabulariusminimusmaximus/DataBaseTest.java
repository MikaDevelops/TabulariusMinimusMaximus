/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package mikan.tabulariusminimusmaximus;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import mikan.tabulariusminimusmaximus.DataBase.*;

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
    
}
