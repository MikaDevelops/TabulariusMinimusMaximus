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
        JournalRow journalRow = new JournalRow(777,"selitys",11,22,55555,99999,"testitili","testitili2",33,"");
        HashChecksum checksum = new HashChecksum(journalRow, testPreviousHash);
        String result = checksum.countHash();
        String expectation = "b87534c951ffb56e62aa96e03712f546".toUpperCase();
 
        assertEquals(expectation, result);
    }
    
}
