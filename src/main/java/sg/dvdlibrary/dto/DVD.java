/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.dvdlibrary.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Holds data for an individual DVD
 * @author utkua
 */
public class DVD {
    
    // Variables with all info to be held
    // Title will not change
    private String title;
    private LocalDate date;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String note;
    private double imdb;

    /**
     * Constructs a DVD
     * @param title 
     */
    public DVD(String title) {
        this.title = title;
    }

    /**
     * Gets the title of the DVD
     * @return 
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the date
     * @return 
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the Mpaa Rating
     * @return 
     */
    public String getMpaaRating() {
        return mpaaRating;
    }

    /**
     * Sets the date
     * @param date 
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Sets the MPAA Rating
     * @param mpaaRating 
     */
    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    /**
     * Sets the director name for the DVD
     * @param directorName 
     */
    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    /**
     * Sets the studio of the DVD
     * @param studio 
     */
    public void setStudio(String studio) {
        this.studio = studio;
    }
    
    /**
     * Sets the note of a DVD
     * @param note 
     */
    public void setNote(String note) {
        this.note = note;
    }
    
  
    public double getImdb() {
        return imdb;
    }

    public void setImdb(double imdb) {
        this.imdb = imdb;
    }

    public String getDirectorName() {
        return directorName;
    }

    public String getStudio() {
        return studio;
    }

    public String getNote() {
        return note;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.title);
        hash = 53 * hash + Objects.hashCode(this.date);
        hash = 53 * hash + Objects.hashCode(this.mpaaRating);
        hash = 53 * hash + Objects.hashCode(this.directorName);
        hash = 53 * hash + Objects.hashCode(this.studio);
        hash = 53 * hash + Objects.hashCode(this.note);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.imdb) ^ (Double.doubleToLongBits(this.imdb) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DVD other = (DVD) obj;
        if (Double.doubleToLongBits(this.imdb) != Double.doubleToLongBits(other.imdb)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.mpaaRating, other.mpaaRating)) {
            return false;
        }
        if (!Objects.equals(this.directorName, other.directorName)) {
            return false;
        }
        if (!Objects.equals(this.studio, other.studio)) {
            return false;
        }
        if (!Objects.equals(this.note, other.note)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DVD{" + "title=" + title + ", date=" + date + ", mpaaRating=" + mpaaRating + ", directorName=" + directorName + ", studio=" + studio + ", note=" + note + ", imdb=" + imdb + '}';
    }
    
}
