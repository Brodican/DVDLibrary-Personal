/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.dvdlibrary.ui;

import java.util.List;
import sg.dvdlibrary.dto.DVD;

/**
 *
 * @author utkua
 */
public class DVDLibraryView {
    
    // io object
    private UserIO io;

    // io passed to constructor for dependency injection
    public DVDLibraryView(UserIO io) {
        this.io = io;
    }

    // Print menu and get input
    public int printMenuAndGetSelection() {
        io.print("\nMain Menu");
        io.print("1. Add a DVD");
        io.print("2. View a DVD");
        io.print("3. Delete a DVD");
        io.print("4. Edit a specific field of a DVD");
        io.print("5. Edit an entire DVD");
        io.print("6. List all DVDs");
        io.print("7. Show DVD Count");
        io.print("8. Exit");

        return io.readInt("Please select from the above choices.", 1, 8);
    }
    
    // Print fields that can be edited and get selection 
    public int printEditChoicesAndGetSelection() {
        io.print("\nEdit Menu");
        io.print("1. Release Date");
        io.print("2. MPAA Rating");
        io.print("3. Director's Name");
        io.print("4. Studio");
        io.print("5. User Rating or Note");
        io.print("6. IMDB Rating");

        return io.readInt("Please select from the above choices.", 1, 6);
    }
    
    // Get new value for edited String field
    public String getEditValue(String field) {
        return io.readString("Please enter new " + field);
    }
    
    // Get new value for edited IMDB Rating field
    public double getEditValueImdb(String field) {
        return io.readDouble("Please enter new " + field, 1, 10);
    }
    
    // Get all info for a DVD
    public DVD getNewDVDInfo(String title) {
        String date = io.readString("Please enter Release Date");
        String rating = io.readString("Please enter MPAA Rating");
        String director = io.readString("Please enter Director's Name");
        String studio = io.readString("Please enter Studio");
        String note = io.readString("Please enter User Rating or Note");
        double imdb = io.readDouble("Please enter IMDB Rating", 0, 10);
        DVD currentDVD = new DVD(title);
        
        currentDVD.setDate(date);
        currentDVD.setMpaaRating(rating);
        currentDVD.setDirectorName(director);
        currentDVD.setStudio(studio);
        currentDVD.setNote(note);
        currentDVD.setImdb(imdb);
        return currentDVD;
    }
    
    // Get List of all DVDs
    public void displayDVDList(List<DVD> dvdList) {
        for (DVD currentDVD : dvdList) {
            // Format method to avoid clunky concatenation
            String dvdInfo = String.format("%s : %s %s %s %s %s %s",
                currentDVD.getTitle(),
                currentDVD.getDate(),
                currentDVD.getMpaaRating(),
                currentDVD.getDirectorName(),
                currentDVD.getStudio(),
                currentDVD.getNote(),
                currentDVD.getImdb());
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    
    // Various banner to inform user
    public void displayDisplayAllBanner() {
        io.print("\n=== Display All DVDs ===");
    }
    
    public void displayDisplayDVDBanner () {
        io.print("\n=== Display DVD ===");
    }
    
    public void displayRemoveDVDBanner () {
        io.print("\n=== Remove DVD ===");
    }
    
    public void displayEditDVDBanner() {
        io.print("\n === Edit DVD ===");
    }
    
    public void displayCountDVDBanner() {
        io.print("\n === DVD Count ===");
    }

    public void displayEditSuccessBanner() {
        io.print("\n === Edit Success ===");
    }
    
    public void displayCreateDVDBanner() {
        io.print("\n=== Create DVD ===");
    }
    
    public void displayCreateSuccessBanner() {
        io.readString("DVD successfully created.  Please hit enter to continue");
    }
    
    public void displayCreateFailureBanner() {
        io.readString("DVD already exists.  Please hit enter to continue");
    }
    
    public void displayExitBanner() {
        io.print("\nApplication Exit.");
    }

    public void displayUnknownCommandBanner() {
        io.print("\nUnknown Command!!!");
    }

    // Get choice of title from user
    public String getTitleChoice() {
        return io.readString("Please enter the DVD title.");
    }

    // Display 1 DVD
    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            String dvdInfo = String.format("%s\n Release Date: %s\n "
                    + "MPAA Rating: %s\n Director Name: %s\n Studio: %s\n "
                    + "User Note: %s\n IMDB Rating: %s",
                dvd.getTitle(),
                dvd.getDate(),
                dvd.getMpaaRating(),
                dvd.getDirectorName(),
                dvd.getStudio(),
                dvd.getNote(),
                dvd.getImdb());
            io.print(dvdInfo);
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    // Indicate whether DVD found and removed or not
    public void displayRemoveResult(DVD dvd) {
        if(dvd != null){
          io.print("DVD successfully removed.");
        }else{
          io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    // Print error to inform user
    public void displayErrorMessage(String errorMsg) {
        io.print("\n=== ERROR ===");
        io.print(errorMsg);
    }

    // Get all info but title for editing
    public DVD getEditDVDInfo(String title) {
        String date = io.readString("Please enter new Release Date");
        String rating = io.readString("Please enter new MPAA Rating");
        String director = io.readString("Please enter new Director's Name");
        String studio = io.readString("Please enter new Studio");
        String note = io.readString("Please enter new User Rating or Note");
        double imdb = io.readDouble("Please enter new IMDB Rating", 0, 10);
        DVD currentDVD = new DVD(title);
        
        currentDVD.setDate(date);
        currentDVD.setMpaaRating(rating);
        currentDVD.setDirectorName(director);
        currentDVD.setStudio(studio);
        currentDVD.setNote(note);
        currentDVD.setImdb(imdb);
        return currentDVD;
    }

    // Display number of DVDs
    public void displayCount(int count) {
        io.print("DVD count: " + count);
    }

    public void displayEditFailureBanner() {
        io.print("No such DVD.");
    }
    
}
