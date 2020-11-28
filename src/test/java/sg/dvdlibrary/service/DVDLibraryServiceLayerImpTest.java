/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.dvdlibrary.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import sg.dvdlibrary.dao.DVDLibraryAuditDao;
import sg.dvdlibrary.dao.DVDLibraryAuditDaoStubImpl;
import sg.dvdlibrary.dao.DVDLibraryDao;
import sg.dvdlibrary.dao.DVDLibraryDaoStubImp;
import sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import sg.dvdlibrary.dto.DVD;

/**
 *
 * @author utkua
 */
public class DVDLibraryServiceLayerImpTest {
    
    public DVDLibraryServiceLayer service;

    public DVDLibraryServiceLayerImpTest() {
        DVDLibraryDao dao = new DVDLibraryDaoStubImp();
        DVDLibraryAuditDao auditDao = new DVDLibraryAuditDaoStubImpl();

        service = new DVDLibraryServiceLayerImp(auditDao, dao);
        System.out.println("Here");
        if (service == null) {
            System.out.println("Service is null.");
        }
        //ApplicationContext ctx = 
            //new ClassPathXmlApplicationContext("applicationContext.xml");
        //service = 
            //ctx.getBean("serviceLayer", DVDLibraryServiceLayer.class);
    }
    
    @BeforeAll
    public static void setUpClass() {
        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testCreateValidDVD() {
        // ARRANGE
        String dvdTitle = "0003";
        DVD onlyDVD = new DVD(dvdTitle);
        onlyDVD.setDate(LocalDate.parse("11/11/1997", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        onlyDVD.setMpaaRating("18");
        onlyDVD.setDirectorName("Utku");
        onlyDVD.setStudio("Jimmy");
        onlyDVD.setNote("Notes");
        onlyDVD.setImdb(5.2);
        System.out.println(service.toString());
        if (onlyDVD == null) {
            System.out.println("DVD null");
        }
        if (service == null) {
            System.out.println("Service null");
        }
        // ACT
        try {
            service.createDVD(onlyDVD);
        } catch (DVDLibraryDuplicateTitleException
                | DVDLibraryDataValidationException
                | DVDLibraryPersistenceException e) {
        // ASSERT
            fail("DVD was valid. No exception should have been thrown.");
        }
    }
    
    @Test
    public void testCreateDuplicateTitleDVD() {
        // ARRANGE
        String dvdTitle = "0001";
        DVD onlyDVD = new DVD(dvdTitle);
        onlyDVD.setDate(LocalDate.parse("11/11/1997", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        onlyDVD.setMpaaRating("18");
        onlyDVD.setDirectorName("Utku");
        onlyDVD.setStudio("Jimmy");
        onlyDVD.setNote("Notes");
        onlyDVD.setImdb(5.2);

        // ACT
        try {
            service.createDVD(onlyDVD);
            fail("Expected DupeTitle Exception was not thrown.");
        } catch (DVDLibraryDataValidationException
                | DVDLibraryPersistenceException e) {
        // ASSERT
            fail("Incorrect exception was thrown.");
        } catch (DVDLibraryDuplicateTitleException e){
            return;
        }
    }
    
    @Test
    public void testCreateDVDInvalidData() throws Exception {
        // ARRANGE
        String dvdTitle = "0002";
        DVD onlyDVD = new DVD(dvdTitle);
        onlyDVD.setDate(LocalDate.parse("11/11/1997", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        onlyDVD.setMpaaRating("18");
        onlyDVD.setDirectorName("");
        onlyDVD.setStudio("Jimmy");
        onlyDVD.setNote("Notes");
        onlyDVD.setImdb(5.2);

        // ACT
        try {
            service.createDVD(onlyDVD);
            fail("Expected ValidationException was not thrown.");
        } catch (DVDLibraryDuplicateTitleException
                | DVDLibraryPersistenceException e) {
        // ASSERT
            fail("Incorrect exception was thrown.");
        } catch (DVDLibraryDataValidationException e){
            return;
        }  
    }
    
    @Test
    public void testGetAllDVDs() throws Exception {
        // ARRANGE
        String dvdTitle = "0001";
        DVD testClone = new DVD(dvdTitle);
        testClone.setDate(LocalDate.parse("11/11/1997", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        testClone.setMpaaRating("18");
        testClone.setDirectorName("");
        testClone.setStudio("Jimmy");
        testClone.setNote("Notes");
        testClone.setImdb(5.2);

        // ACT & ASSERT
        assertEquals( 1, service.getAllDVDs().size(), 
                                       "Should only have one student.");
        assertTrue( service.getAllDVDs().contains(testClone),
                                  "The one student should be Ada.");
    }
    
    @Test
    public void testGetDVD() throws Exception {
        // ARRANGE
        String dvdTitle = "0001";
        DVD testClone = new DVD(dvdTitle);
        testClone.setDate(LocalDate.parse("11/11/1997", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        testClone.setMpaaRating("18");
        testClone.setDirectorName("");
        testClone.setStudio("Jimmy");
        testClone.setNote("Notes");
        testClone.setImdb(5.2);

        // ACT & ASSERT
        DVD shouldBeAda = service.getDVD("0001");
        assertNotNull(shouldBeAda, "Getting 0001 should be not null.");
        assertEquals( testClone, shouldBeAda,
                                       "DVD stored under 0001 should be Ada.");

        DVD shouldBeNull = service.getDVD("0042");    
        assertNull( shouldBeNull, "Getting 0042 should be null.");

    }
    
    @Test
    public void testRemoveDVD() throws Exception {
        // ARRANGE
        String dvdTitle = "0001";
        DVD testClone = new DVD(dvdTitle);
        testClone.setDate(LocalDate.parse("11/11/1997", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        testClone.setMpaaRating("18");
        testClone.setDirectorName("");
        testClone.setStudio("Jimmy");
        testClone.setNote("Notes");
        testClone.setImdb(5.2);

        // ACT & ASSERT
        DVD shouldBeAda = service.removeDVD("0001");
        assertNotNull( shouldBeAda, "Removing 0001 should be not null.");
        assertEquals( testClone, shouldBeAda, "DVD removed from 0001 should be Ada.");

        DVD shouldBeNull = service.removeDVD("0042");    
        assertNull( shouldBeNull, "Removing 0042 should be null.");

    }
    
}
