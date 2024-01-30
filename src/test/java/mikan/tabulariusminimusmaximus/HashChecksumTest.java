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
    
    @Test
    public void testCountHash_jounalRow() throws Exception{
    
        String testPreviousHash = "8368f5f06e1b362211890e7717c274ce";
        JournalRow journalRow = new JournalRow(777,"selitys",11,22,99999,"testitili",33,"");
        HashChecksum checksum = new HashChecksum(journalRow, testPreviousHash);
        String result = checksum.countHash();
        String expectation = "7245F0E00604D4DA108852647E6A9934";
 
        assertEquals(expectation, result);
    }
    
}
