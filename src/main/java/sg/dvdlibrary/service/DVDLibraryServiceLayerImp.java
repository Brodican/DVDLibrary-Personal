/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.dvdlibrary.service;

import java.util.List;
import sg.dvdlibrary.dao.DVDLibraryAuditDao;
import sg.dvdlibrary.dao.DVDLibraryDao;
import sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import sg.dvdlibrary.dto.DVD;

/**
 *
 * @author utkua
 */
public class DVDLibraryServiceLayerImp implements DVDLibraryServiceLayer {

    DVDLibraryAuditDao auditDao;
    DVDLibraryDao dao;
    
    public DVDLibraryServiceLayerImp(DVDLibraryAuditDao auditDao, DVDLibraryDao dao) {
        System.out.println("Called constructor service");
        this.auditDao = auditDao;
        this.dao = dao;
        System.out.println("Done constructor service");
    }
    
    // Effectively just checking if the student already exists
    // Then, check if the object to be added
    @Override
    public void createDVD(DVD dvd) throws
            DVDLibraryDuplicateTitleException,
            DVDLibraryDataValidationException,
            DVDLibraryPersistenceException {
        // First check to see if there is alreay a student 
        // associated with the given student's id
        // If so, we're all done here - 
        // throw a ClassRosterDuplicateIdException
        if (dao.getDVD(dvd.getTitle()) != null) {
            System.out.println("Error createDVD");
            throw new DVDLibraryDuplicateTitleException(
                    "ERROR: Could not create DVD.  DVD Title "
                    + dvd.getTitle()
                    + " already exists");
        }
        System.out.println("Called createDVD");
        // Now validate all the fields on the given DVD object.  
        // This method will throw an
        // exception if any of the validation rules are violated.
        validateDVDData(dvd);

        // We passed all our business rules checks so go ahead 
        // and persist the Student object
        dao.addDVD(dvd.getTitle(), dvd);
    }

    @Override
    public List<DVD> getAllDVDs() throws
            DVDLibraryPersistenceException {
        return dao.getAllDVDs();
    }

    @Override
    public DVD getDVD(String dvdTitle) throws DVDLibraryPersistenceException {
        return dao.getDVD(dvdTitle);
    }

    @Override
    public DVD removeDVD(String dvdTitle) throws DVDLibraryPersistenceException {
        return dao.removeDVD(dvdTitle);
    }
    
    private void validateDVDData(DVD dvd) throws
            DVDLibraryDataValidationException {
        System.out.println("Called validate");
        if (dvd.getDate() == null
                || dvd.getMpaaRating() == null
                || dvd.getMpaaRating().trim().length() == 0
                || dvd.getStudio() == null
                || dvd.getStudio().trim().length() == 0
                || dvd.getNote()== null
                || dvd.getNote().trim().length() == 0
                || dvd.getDirectorName()== null
                || dvd.getDirectorName().trim().length() == 0) {

            throw new DVDLibraryDataValidationException(
                    "ERROR: All fields [Date, MPAA Rating, Studio, Note, Director Name] are required.");
        }
    }
}
