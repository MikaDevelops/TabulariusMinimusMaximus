/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package mikan.tabulariusminimusmaximus;

import java.util.ArrayList;
import mikan.tabulariusminimusmaximus.datamodel.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mika
 */
public class HashChecksumTest {
    
    public HashChecksumTest() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of countHash method, of class HashChecksum.
     */
    @Test
    public void testCountHash() throws Exception {
        
        String testPreviousHash = "8368f5f06e1b362211890e7717c274ce";
        ArrayList<JournalRow> testArray = new ArrayList<JournalRow>();
        
        JournalRow testJournalRow = new JournalRow(777,11,22,99999,"testitili","");
        testArray.add(testJournalRow);
        JournalDocument testDoc = new JournalDocument(9099,"2023-08-01","u.jpg","");
        Journal testJournal = new Journal("testLegend", 55, "", testArray, testDoc);
        
        
        
        HashChecksum checksum = new HashChecksum(testJournal, testPreviousHash);
        String result = checksum.countHash();
        String expectation = "jotain";
 
        assertTrue(expectation.equals(result));
    }
    
}
