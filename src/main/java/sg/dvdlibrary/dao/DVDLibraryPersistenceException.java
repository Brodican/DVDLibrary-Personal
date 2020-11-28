/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.dvdlibrary.dao;

/**
 *
 * @author utkua
 */
public class DVDLibraryPersistenceException extends Exception {
    
    // For use when something goes wrong but not a different exception
    // For instance, invalid DVD data according to our rules
    public DVDLibraryPersistenceException(String message) {
        super(message);
    }
    
    // When problem caused by another exception/error - pass it as a throwable
    // Throwable since that is widest range
    public DVDLibraryPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
