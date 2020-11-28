/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.dvdlibrary.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import sg.dvdlibrary.dto.DVD;

/**
 * Used to read and store DVD Data.
 * @author utkua
 */
public class DVDLibraryDaoFileImp implements DVDLibraryDao {
    
    // Hashmap from library java.util.HashMap
    // Map from java.util.Map
    private Map<String, DVD> dvds = new HashMap<>();
    
    // These won't change
    public final String LIBRARY_FILE;
    public static final String DELIMITER = "::";
    
    /**
     * Constructs a DVDLibraryDaoFile object.
     */
    public DVDLibraryDaoFileImp() {
        LIBRARY_FILE = "library.txt";
    }
    
    public DVDLibraryDaoFileImp(String library) {
        LIBRARY_FILE = library;
    }

    /**
     * Adds a DVD
     * @param dvdTitle Title of the DVD
     * @param dvd DVD object to be added.
     * @return DVD that was added to the system.
     * @throws DVDLibraryPersistenceException 
     */
    @Override
    public DVD addDVD(String dvdTitle, DVD dvd) throws DVDLibraryPersistenceException {
        readLibrary();
        // Add passed DVD to Hashmap with String title key and DVD object value
        DVD newDvd = dvds.put(dvdTitle, dvd);
        // Write each added DVD to file - more CPU resource usage,
        // but data saved in case of unexpected error
        writeLibrary();
        return newDvd;
    }

    /**
     * Returns a list of all DVDs stored.
     * @return List of DVD objects.
     * @throws DVDLibraryPersistenceException 
     */
    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryPersistenceException {
        // Read the file to ensure all DVDs in memory
        readLibrary();
        // Return ArrayList with all values
        // From library java.util.ArrayList
        return new ArrayList<>(dvds.values());
    }

    /**
     * Returns a DVD with the title entered.
     * @param dvdTitle Title of DVD
     * @return DVD object or null if no DVD exists with title.
     * @throws DVDLibraryPersistenceException 
     */
    @Override
    public DVD getDVD(String dvdTitle) throws DVDLibraryPersistenceException {
        readLibrary();
        // Get DVD object with title key
        return dvds.get(dvdTitle);
    }
    
    /**
     * Removes a DVD from the system.
     * @param dvdTitle Title of DVD
     * @return DVD object with title entered, or null if none were found.
     * @throws DVDLibraryPersistenceException 
     */
    @Override
    public DVD removeDVD(String dvdTitle) throws DVDLibraryPersistenceException {
        readLibrary();
        // Remove DVD object with title key
        DVD returnDVD = dvds.remove(dvdTitle);
        writeLibrary();
        return returnDVD;
    }
    
    // Set methods for each field
    // Must be seperate as cannot have logic in DAO
    @Override
    public void setFieldDate(LocalDate value, String title) throws DVDLibraryPersistenceException {
        readLibrary();
        // get DVD, set the date, put in dvds, then write
        DVD dvdToEdit = dvds.get(title);
        dvdToEdit.setDate(value);
        dvds.put(title, dvdToEdit);
        writeLibrary();
    }
    
    @Override
    public void setFieldRating(String value, String title) throws DVDLibraryPersistenceException {
        readLibrary();
        // get DVD, set the MPAA rating, put in dvds, then write
        DVD dvdToEdit = dvds.get(title);
        dvdToEdit.setMpaaRating(value);
        dvds.put(title, dvdToEdit);
        writeLibrary();
    }
    
    @Override
    public void setFieldDirectorName(String value, String title) throws DVDLibraryPersistenceException {
        readLibrary();
        // get DVD, set director name, put in dvds, then write
        DVD dvdToEdit = dvds.get(title);
        dvdToEdit.setDirectorName(value);
        dvds.put(title, dvdToEdit);
        writeLibrary();
    }
    
    @Override
    public void setFieldStudio(String value, String title) throws DVDLibraryPersistenceException {
        readLibrary();
        // get DVD, set the studio, put in dvds, then write
        DVD dvdToEdit = dvds.get(title);
        dvdToEdit.setStudio(value);
        dvds.put(title, dvdToEdit);
        writeLibrary();
    }
    
    @Override
    public void setFieldNote(String value, String title) throws DVDLibraryPersistenceException {
        readLibrary();
        // get DVD, set the note, put in dvds, then write
        DVD dvdToEdit = dvds.get(title);
        dvdToEdit.setNote(value);
        dvds.put(title, dvdToEdit);
        writeLibrary();
    }
    
    @Override
    public void setFieldImdb(double value, String title) throws DVDLibraryPersistenceException {
        readLibrary();
        // get DVD, set the IMDB rating, put in dvds, then write
        DVD dvdToEdit = dvds.get(title);
        dvdToEdit.setImdb(value);
        dvds.put(title, dvdToEdit);
        writeLibrary();
    }
    
    // Unmarshall 1 line
    private DVD unmarshallDVD(String dvdAsText) throws DVDLibraryPersistenceException {
        
        // split returns string array split on DELIMETER
        String[] dvdFields = dvdAsText.split(DELIMITER);

        // Given the pattern above, the dvd title is in index 0 of the array.
        String dvdTitle = dvdFields[0];

        // Which we can then use to create a new DVD object to satisfy
        // the requirements of the DVD constructor.
        DVD dvdFromFile = new DVD(dvdTitle);

        // 5 remaining fields that need to be set into the
        // new DVD object. Do this manually by using the appropriate setters.

        try {
            // Index 1 - Release date
            dvdFromFile.setDate(LocalDate.parse(dvdFields[1], DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            // Index 2 - MPAA rating
            dvdFromFile.setMpaaRating(dvdFields[2]);

            // Index 3 - Director's name
            dvdFromFile.setDirectorName(dvdFields[3]);

            // Index 4 - Studio's name
            dvdFromFile.setStudio(dvdFields[4]);

            // Index 5 - Rating or note
            dvdFromFile.setNote(dvdFields[5]);

            // Index 6 IMDB rating
            dvdFromFile.setImdb(Double.valueOf(dvdFields[6]));
        } catch (IndexOutOfBoundsException e) {
            // Error translation
            throw new DVDLibraryPersistenceException(
                    "Error in format of a DVD record.", e);
        }


        // Return DVD created from file
        return dvdFromFile;
    }
    
    /**
     * Reads all DVDs in the library to @dvds.
     * 
     * @throws DVDLibraryPersistenceException if an error occurs reading from the file
     */
    private void readLibrary() throws DVDLibraryPersistenceException {
        // Scanner from java.util.Scanner
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    // BuferredRead from java.io.BufferedReader
                    new BufferedReader(
                            // Filereader from java.io.FileReader
                            new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) { // Catch and translate FileNotFoundException
            throw new DVDLibraryPersistenceException(
                    "-_- Could not load library data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentDVD holds the most recent DVD unmarshalled
        DVD currentDVD;
        // Go through LIBRARY_FILE line by line, decoding each line into a 
        // DVD object by calling the unmarshallDVD method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a DVD
            currentDVD = unmarshallDVD(currentLine);

            // Put currentDVD into the map using DVD title as the key
            dvds.put(currentDVD.getTitle(), currentDVD);
        }
        // close scanner
        scanner.close();
    }
    
    private String marshallDVD(DVD aDVD){
        // Turn a DVD object into a line of text for file

        // Start with the DVD title, since that's supposed to be first.
        String dvdAsText = aDVD.getTitle() + DELIMITER;

        // Add rest of properties in the correct order:

        // Release date
        dvdAsText += aDVD.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + DELIMITER;

        // Age rating
        dvdAsText += aDVD.getMpaaRating()+ DELIMITER;
        
        dvdAsText += aDVD.getDirectorName()+ DELIMITER;
        
        dvdAsText += aDVD.getStudio()+ DELIMITER;
        
        dvdAsText += aDVD.getNote()+ DELIMITER;

        dvdAsText += aDVD.getImdb();

        // Return DVD as text.
        return dvdAsText;
    }
    
    /**
     * Writes all DVDs in the library out to a LIBRARY_FILE.  See readLibrary
     * for file format.
     * 
     * @throws DVDLibraryPersistenceException if an error occurs writing to the file
     */
    private void writeLibrary() throws DVDLibraryPersistenceException {
        // Printwriter from java.io.PrintWriter;
        PrintWriter out;

        try {
            // Filewriter from java.io.FileWriter
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new DVDLibraryPersistenceException(
                    "Could not save DVD data.", e);
        }

        // Write out the DVD objects to the library file.
        String dvdAsText;
        List<DVD> dvdList = new ArrayList(dvds.values());
        for (DVD currentDVD : dvdList) {
            // turn a DVD into a String
            dvdAsText = marshallDVD(currentDVD);
            // write the DVD object to the file
            out.println(dvdAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

    // Return size of hashmap
    @Override
    public int countDVDs() throws DVDLibraryPersistenceException {
        readLibrary();
        return dvds.size();
    }

    @Override
    public List<DVD> getAllWithin(int years) throws DVDLibraryPersistenceException {
        ArrayList<DVD> list = new ArrayList<>(dvds.values());
        return list.stream().filter((d) -> d.getDate().compareTo(LocalDate.now()) < years)
                .collect(Collectors.toList());
    }

    @Override
    public List<DVD> getAllWithMpaa(String mpaa) throws DVDLibraryPersistenceException {
        ArrayList<DVD> list = new ArrayList<>(dvds.values());
        return list.stream().filter((d) -> d.getMpaaRating().equals(mpaa))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<DVD>> getAllWithDirector(String dir) throws DVDLibraryPersistenceException {
        ArrayList<DVD> list = new ArrayList<>(dvds.values());
        // For organization
        // We want to return a map of lists, where seperate list for each MPAA rating
        Stream<DVD> listStream = list.stream().filter((d) -> d.getMpaaRating().equals(dir));
        return listStream.collect(Collectors.groupingBy((d) -> d.getMpaaRating()));
    }

    @Override
    public List<DVD> getAllByStudio(String studio) throws DVDLibraryPersistenceException {
        ArrayList<DVD> list = new ArrayList<>(dvds.values());
        return list.stream().filter((d) -> d.getStudio().equals(studio))
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal averageAge() throws DVDLibraryPersistenceException {
        ArrayList<DVD> list = new ArrayList<>(dvds.values());
        float averageAge = (float) list.stream().mapToInt((d) -> d.getDate().compareTo(LocalDate.now())).average().getAsDouble();
        return new BigDecimal(averageAge);
    }

    @Override
    public DVD getNewest() throws DVDLibraryPersistenceException {
        ArrayList<DVD> list = new ArrayList<>(dvds.values());
        return list.stream().max(Comparator.comparing((d) -> d.getDate().compareTo(LocalDate.now()))).get();
    }

    @Override
    public DVD getOldest() throws DVDLibraryPersistenceException {
        ArrayList<DVD> list = new ArrayList<>(dvds.values());
        return list.stream().min(Comparator.comparing((d) -> d.getDate().compareTo(LocalDate.now()))).get();
    }

    @Override
    public BigDecimal averageNotes() throws DVDLibraryPersistenceException {
        ArrayList<DVD> list = new ArrayList<>(dvds.values());
        float averageNotes = (float) list.stream().mapToInt((dvd) -> dvd.getNote().split(",").length)
                .average().getAsDouble();
        return new BigDecimal(averageNotes);
    }
    
}

