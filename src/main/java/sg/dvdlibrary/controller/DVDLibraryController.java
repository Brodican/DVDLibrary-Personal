/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.dvdlibrary.controller;

import java.util.List;
import sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import sg.dvdlibrary.dto.DVD;
import sg.dvdlibrary.service.DVDLibraryDataValidationException;
import sg.dvdlibrary.service.DVDLibraryDuplicateTitleException;
import sg.dvdlibrary.service.DVDLibraryServiceLayer;
import sg.dvdlibrary.ui.DVDLibraryView;

/**
 * Orchestrates application by making calls to other classes and methods to perform operations.
 * @author utkua
 */
public class DVDLibraryController {

    
    private DVDLibraryView view;
    private DVDLibraryServiceLayer service;
    
    /**
     * Constructs a DVDLibraryController.
     * @param view DVDLibraryView that will communicate with the user.
     * @param service DVDLibraryDao that will read and store data.
     */
    public DVDLibraryController(DVDLibraryView view, DVDLibraryServiceLayer service) {
        this.view = view;
        this.service = service;
    }
    
    /**
     * Runs the application by putting the menu in a while loop, waiting for user input.
     * @throws sg.dvdlibrary.service.DVDLibraryDuplicateTitleException
     * @throws sg.dvdlibrary.service.DVDLibraryDataValidationException
     */
    public void run() {
        // Loop until otherwise specified by user
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            // Loop will run until user inputs 7
            // setting keepGoing to false.
            while (keepGoing) {
                
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        createDVD();
                        break;
                    case 2:
                        viewDVD();
                        break;
                    case 3:
                        removeDVD();
                        break;
                    case 4:
                        editDvdField();
                        break;
                    case 5:
                        editDVD();
                        break;
                    case 6:
                        listDVDs();
                        break;
                    case 7:
                        countDVDs();
                        break;
                    case 8:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (DVDLibraryPersistenceException e) {
            // Ensure user is informed when error occurs
            view.displayErrorMessage(e.getMessage());
        }
    }

    /**
     * Prints the menu selection and returns the users choice.
     * @return Integer value representing the users choice.
     */
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    /**
     * Creates a DVD.
     * @throws DVDLibraryPersistenceException 
     */
    private void createDVD() throws DVDLibraryPersistenceException {
        // Make view display create banner to inform user
        view.displayCreateDVDBanner();
        boolean hasErrors = false;
        do {
            String newDvdTitle = view.getTitleChoice();
            DVD newDVD = view.getNewDVDInfo(newDvdTitle);
            try {
                service.createDVD(newDVD);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            } catch (DVDLibraryDataValidationException | DVDLibraryDuplicateTitleException e) {
                hasErrors = true;
            }
        } while (hasErrors);
    }
    
    /**
     * Edits a DVD
     * @param title String value that represents the title of the DVD.
     * @throws DVDLibraryPersistenceException 
     */
    private void createDVDEdit(String title) throws DVDLibraryPersistenceException {
        boolean hasErrors = false;
        do {
            DVD newDVD = view.getEditDVDInfo(title);
            try {
                service.createDVD(newDVD);
                view.displayEditSuccessBanner();
                hasErrors = false;
            } catch (DVDLibraryDataValidationException | DVDLibraryDuplicateTitleException e) {
                hasErrors = true;
            }
        } while (hasErrors);
        
    }

    /**
     * Retrieves a list of DVDs from the DAO then outputs them in the view.
     * @throws DVDLibraryPersistenceException 
     */
    private void listDVDs() throws DVDLibraryPersistenceException {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = service.getAllDVDs();
        view.displayDVDList(dvdList);
    }

    /**
     * Retrieves information about a DVD from the DAO before outputting in the view.
     * @throws DVDLibraryPersistenceException 
     */
    private void viewDVD() throws DVDLibraryPersistenceException {
        view.displayDisplayDVDBanner();
        String title = view.getTitleChoice();
        DVD dvd = service.getDVD(title);
        view.displayDVD(dvd);
    }

    /**
     * Removes a DVD from the system.
     * @throws DVDLibraryPersistenceException 
     */
    private void removeDVD() throws DVDLibraryPersistenceException {
        view.displayRemoveDVDBanner();
        String title = view.getTitleChoice();
        DVD removedDVD = service.removeDVD(title);
        view.displayRemoveResult(removedDVD);
    }
    
    /**
     * Edits the information stored about a DVD
     * @throws DVDLibraryPersistenceException 
     */
    private void editDVD() throws DVDLibraryPersistenceException {
        view.displayEditDVDBanner();
        String title = view.getTitleChoice();
        DVD editedDVD = service.getDVD(title);
        view.displayDVD(editedDVD);
        if (editedDVD != null) {
            createDVDEdit(title);
        }
    }
    
    /**
     * Allows the user to edit a specific field of a DVD.
     * @throws DVDLibraryPersistenceException 
     */
    private void editDvdField() throws DVDLibraryPersistenceException {
        // Get title - if it doesn't exist, indicate failure to user
        /*String title = view.getTitleChoice();
        if (service.getDVD(title) == null) {
            view.displayEditFailureBanner();
            return;
        }
        
        // Get field to edit from user
        int field = view.printEditChoicesAndGetSelection();
        String fieldString;
        // String for new value of edited field
        String newVal;
        // Double for new value of edited field - in this case just IMDB
        double newValDouble;
        
        // Get new value, indicating field to be changed
        // Call field setting method with new value
        switch (field) {
            case 1:
                fieldString = "Release Date";
                newVal = view.getEditValue(fieldString);
                service.setFieldDate(LocalDate.parse(newVal, DateTimeFormatter.ofPattern("dd/MM/yyyy")), title);
                break;
            case 2:
                fieldString = "MPAA Rating";
                newVal = view.getEditValue(fieldString);
                service.setFieldRating(newVal, title);
                break;
            case 3:
                fieldString = "Director's Name";
                newVal = view.getEditValue(fieldString);
                service.setFieldDirectorName(newVal, title);
                break;
            case 4:
                fieldString = "Studio";
                newVal = view.getEditValue(fieldString);
                service.setFieldStudio(newVal, title);
                break;
            case 5:
                fieldString = "User Rating/Note";
                newVal = view.getEditValue(fieldString);
                service.setFieldRating(newVal, title);
                break;
            case 6:
                fieldString = "IMDB Rating";
                newValDouble = view.getEditValueImdb(fieldString);
                service.setFieldImdb(newValDouble, title);
                break;
        }
        
        view.displayEditSuccessBanner();*/
    }
    
    /**
     * Counts the DVDs
     * @throws DVDLibraryPersistenceException 
     */
    private void countDVDs() throws DVDLibraryPersistenceException {
        view.displayCountDVDBanner();
        view.displayCount(service.getAllDVDs().size());
    }

    /**
     * Informs the user there was an unknown command.
     */
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    /**
     * Displays an exit message to the user.
     */
    private void exitMessage() {
        view.displayExitBanner();
    }
    
}
