/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.dvdlibrary.service;

import java.util.List;
import sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import sg.dvdlibrary.dto.DVD;

/**
 *
 * @author utkua
 */
public interface DVDLibraryServiceLayer {
    
    void createDVD(DVD dvd) throws
            DVDLibraryDuplicateTitleException,
            DVDLibraryDataValidationException,
            DVDLibraryPersistenceException;
 
    List<DVD> getAllDVDs() throws
            DVDLibraryPersistenceException;
 
    DVD getDVD(String dvdTitle) throws
            DVDLibraryPersistenceException;
 
    DVD removeDVD(String dvdTitle) throws
            DVDLibraryPersistenceException;   
    
}
