/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.dvdlibrary.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import sg.dvdlibrary.dto.DVD;

/**
 *
 * @author utkua
 */
public class DVDLibraryDaoStubImp implements DVDLibraryDao {
    
    public DVD onlyDVD;

    public DVDLibraryDaoStubImp() {
        String dvdTitle = "0001";
        DVD onlyDVD = new DVD(dvdTitle);
        onlyDVD.setDate(LocalDate.parse("11/11/1997", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        onlyDVD.setMpaaRating("18");
        onlyDVD.setDirectorName("Utku");
        onlyDVD.setStudio("Jimmy");
        onlyDVD.setNote("Notes");
        onlyDVD.setImdb(5.2);
    }

    public DVDLibraryDaoStubImp(DVD testDVD){
         this.onlyDVD = testDVD;
    }

    @Override
    public DVD addDVD(String dvdTitle, DVD student)
                  throws DVDLibraryPersistenceException {
        if (dvdTitle.equals(onlyDVD.getTitle())) {
            return onlyDVD;
        } else {
            return null;
        }
    }

    @Override
    public List<DVD> getAllDVDs()
                 throws DVDLibraryPersistenceException {
        List<DVD> dvdList = new ArrayList<>();
        dvdList.add(onlyDVD);
        return dvdList;
    }

    @Override
    public DVD getDVD(String dvdTitle)
                throws DVDLibraryPersistenceException {
        if (dvdTitle.equals(onlyDVD.getDate())) {
            return onlyDVD;
        } else {
            return null;
        }       
    }

    @Override
    public DVD removeDVD(String dvdTitle)
                throws DVDLibraryPersistenceException {
        if (dvdTitle.equals(onlyDVD.getTitle())) {
            return onlyDVD;
        } else {
            return null;
        }
    }    

    @Override
    public int countDVDs() throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFieldDate(LocalDate value, String title) throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFieldRating(String value, String title) throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFieldDirectorName(String value, String title) throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFieldStudio(String value, String title) throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFieldNote(String value, String title) throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFieldImdb(double value, String title) throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DVD> getAllWithin(int years) throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DVD> getAllWithMpaa(String mpaa) throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, List<DVD>> getAllWithDirector(String dir) throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DVD> getAllByStudio(String studio) throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BigDecimal averageAge() throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DVD getNewest() throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DVD getOldest() throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BigDecimal averageNotes() throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
