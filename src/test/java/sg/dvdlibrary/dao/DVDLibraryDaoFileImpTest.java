/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.dvdlibrary.dao;

import java.io.FileWriter;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import sg.dvdlibrary.dto.DVD;

/**
 *
 * @author utkua
 */
public class DVDLibraryDaoFileImpTest {
    
    DVDLibraryDao testDao;
    
    public DVDLibraryDaoFileImpTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }

    
    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testroster.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new DVDLibraryDaoFileImp(testFile);
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void testGetDVD() throws Exception {
        // Create our method test inputs
        String dvdTitle = "0001";
        DVD dvd = new DVD(dvdTitle);
        dvd.setDate("1997");
        dvd.setMpaaRating("18");
        dvd.setDirectorName("Utku");
        dvd.setStudio("Jimmy");
        dvd.setNote("Notes");
        dvd.setImdb(5.2);

        //  Add the dvd to the DAO
        testDao.addDVD(dvdTitle, dvd);
        // Get the dvd from the DAO
        DVD retrievedDVD = testDao.getDVD(dvdTitle);

        // Check the data is equal
        assertEquals(dvd.getTitle(),
                    retrievedDVD.getTitle(),
                    "Checking DVD title.");
        assertEquals(dvd.getDate(),
                    retrievedDVD.getDate(),
                    "Checking DVD date.");
        assertEquals(dvd.getDirectorName(), 
                    retrievedDVD.getDirectorName(),
                    "Checking DVD director name.");
        assertEquals(dvd.getMpaaRating(), 
                    retrievedDVD.getMpaaRating(),
                    "Checking DVD MPAA Rating.");
    }
    
    @Test
    public void testEditDVD() throws Exception {
        // Create our method test inputs
        String dvdTitle = "0001";
        DVD dvd = new DVD(dvdTitle);
        dvd.setDate("1997");
        dvd.setMpaaRating("18");
        dvd.setDirectorName("Utku");
        dvd.setStudio("Jimmy");
        dvd.setNote("Notes");
        dvd.setImdb(5.2);
        
        //  Add the dvd to the DAO
        testDao.addDVD(dvdTitle, dvd);
        
        dvd.setDate("1992");
        dvd.setMpaaRating("16");
        dvd.setDirectorName("Utkus");
        dvd.setStudio("Jimmys");
        dvd.setNote("Note");
        dvd.setImdb(5.5);

        testDao.setFieldDate("1992", dvdTitle);
        testDao.setFieldRating("16", dvdTitle);
        testDao.setFieldDirectorName("Utkus", dvdTitle);
        testDao.setFieldStudio("Jimmys", dvdTitle);
        testDao.setFieldNote("Note", dvdTitle);
        testDao.setFieldImdb(5.5, dvdTitle);
        
        // Get the dvd from the DAO
        DVD retrievedDVD = testDao.getDVD(dvdTitle);

        // Check the data is equal
        assertEquals(dvd.getTitle(),
                    retrievedDVD.getTitle(),
                    "Checking DVD title.");
        assertEquals(dvd.getDate(),
                    retrievedDVD.getDate(),
                    "Checking DVD date.");
        assertEquals(dvd.getDirectorName(), 
                    retrievedDVD.getDirectorName(),
                    "Checking DVD director name.");
        assertEquals(dvd.getMpaaRating(), 
                    retrievedDVD.getMpaaRating(),
                    "Checking DVD MPAA Rating.");
    }
    
    @Test
    public void testGetAllDVDs() throws Exception {
        // Create our first DVD
        DVD firstDVD = new DVD("Kumar");
        firstDVD.setDate("1997");
        firstDVD.setMpaaRating("18");
        firstDVD.setDirectorName("Utku");
        firstDVD.setStudio("Jim");
        firstDVD.setNote("Note");
        firstDVD.setImdb(5.2);

        // Create our second DVD
        DVD secondDVD = new DVD("Harold");
        secondDVD.setDate("1998");
        secondDVD.setMpaaRating("18");
        secondDVD.setDirectorName("Utku");
        secondDVD.setStudio("Jim");
        secondDVD.setNote("Note");
        secondDVD.setImdb(5.2);

        // Add both our DVDs to the DAO
        testDao.addDVD(firstDVD.getTitle(), firstDVD);
        testDao.addDVD(secondDVD.getTitle(), secondDVD);

        // Retrieve the list of all DVDs within the DAO
        List<DVD> allDVDs = testDao.getAllDVDs();

        // First check the general contents of the list
        assertNotNull(allDVDs, "The list of DVDs must not null");
        assertEquals(2, allDVDs.size(),"List of DVDs should have 2 DVDs.");

        // Then the specifics
        assertTrue(testDao.getAllDVDs().contains(firstDVD),
                    "The list of DVDs should include Ada.");
        assertTrue(testDao.getAllDVDs().contains(secondDVD),
                "The list of DVDs should include Charles.");

    }
    
    @Test
    public void testRemoveDVD() throws Exception {
        // Create our first DVD
        DVD firstDVD = new DVD("Kumar");
        firstDVD.setDate("1997");
        firstDVD.setMpaaRating("18");
        firstDVD.setDirectorName("Utku");
        firstDVD.setStudio("Jim");
        firstDVD.setNote("Note");
        firstDVD.setImdb(5.2);

        // Create our second DVD
        DVD secondDVD = new DVD("Harold");
        secondDVD.setDate("1998");
        secondDVD.setMpaaRating("18");
        secondDVD.setDirectorName("Utku");
        secondDVD.setStudio("Jim");
        secondDVD.setNote("Note");
        secondDVD.setImdb(5.2);

        // Add both to the DAO
        testDao.addDVD(firstDVD.getTitle(), firstDVD);
        testDao.addDVD(secondDVD.getTitle(), secondDVD);

        // remove the first DVD - Ada
        DVD removedDVD = testDao.removeDVD(firstDVD.getTitle());

        // Check that the correct object was removed.
        assertEquals(removedDVD, firstDVD, "The removed DVD should be Ada.");

        // Get all the DVDs
        List<DVD> allDVDs = testDao.getAllDVDs();

        // First check the general contents of the list
        assertNotNull( allDVDs, "All DVDs list should be not null.");
        assertEquals( 1, allDVDs.size(), "All DVDs should only have 1 DVD.");

        // Then the specifics
        assertFalse( allDVDs.contains(firstDVD), "All DVDs should NOT include Ada.");
        assertTrue( allDVDs.contains(secondDVD), "All DVDs should NOT include Charles.");    

        // Remove the second DVD
        removedDVD = testDao.removeDVD(secondDVD.getTitle());
        // Check that the correct object was removed.
        assertEquals( removedDVD, secondDVD, "The removed DVD should be Charles.");

        // retrieve all of the DVDs again, and check the list.
        allDVDs = testDao.getAllDVDs();

        // Check the contents of the list - it should be empty
        assertTrue( allDVDs.isEmpty(), "The retrieved list of DVDs should be empty.");

        // Try to 'get' both DVDs by their old id - they should be null!
        DVD retrievedDVD = testDao.getDVD(firstDVD.getTitle());
        assertNull(retrievedDVD, "Ada was removed, should be null.");

        retrievedDVD = testDao.getDVD(secondDVD.getTitle());
        assertNull(retrievedDVD, "Charles was removed, should be null.");

    }
    
}
