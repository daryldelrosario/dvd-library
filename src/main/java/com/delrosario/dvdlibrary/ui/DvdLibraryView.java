/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.delrosario.dvdlibrary.ui;

import com.delrosario.dvdlibrary.dto.Dvd;
import java.util.List;

/**
 *
 * @author Daryl del Rosario
 */
public class DvdLibraryView {

    private UserIO io;
    
    public DvdLibraryView(UserIO io) {
        this.io = io;
    }
    
    public int printMenuAndGetSelection() {
        io.print("=== Main Menu ===");
        io.print("1. Add DVD");
        io.print("2. Remove DVD");
        io.print("3. Edit DVD");
        io.print("4. List All DVDs");
        io.print("5. Find DVD");
        io.print("6. Exit");
            
        return io.readInt("What would you like to do with your DVD Library today?", 1, 6);
    }
    
    public Dvd getNewDvdInfo() {
        String dvdTitle = io.readString("Please enter Title of DVD:");
        
        String releaseDate = "";
        io.print("");
        io.print("Please enter Release Date: (MM/DD/YY)");
        int month = getMonthRelease();
        String displayMonth = displayMonthRelease(month);
        String date = getDateRelease(month);
        String year = getYearRelease(2021);
        releaseDate += displayMonth + " ";
        releaseDate += date + " ";
        releaseDate += year;
        io.print(releaseDate + " was entered.");
        
        io.print("");
        io.print("Please enter the MPAA Rating:");
        int rating = getMPAARating();
        String mpaaRating = displayMPAARating(rating);
        
        io.print("");
        String directorName = io.readString("Please enter Director Name:");
        String studio = io.readString("Please enter Studio Production:");
        String userRatingNote = io.readString("Please enter User Rating or Note:");
        
        Dvd currentDvd = new Dvd(dvdTitle);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setMpaaRating(mpaaRating);
        currentDvd.setDirectorName(directorName);
        currentDvd.setStudio(studio);
        currentDvd.setUserRatingNote(userRatingNote);
        
        return currentDvd;
    }
    
    public int getMonthRelease() {
        int month = 0;
        
        io.print("What Month was it released? (please choose numerical equivalent)");
        io.print("1 - January");
        io.print("2 - February");
        io.print("3 - March");
        io.print("4 - April");
        io.print("5 - May");
        io.print("6 - June");
        io.print("7 - July");
        io.print("8 - August");
        io.print("9 - September");
        io.print("10 - October");
        io.print("11 - November");
        io.print("12 - December");
        
        month = io.readInt("Enter month between 1 - 12: ", 1, 12);
        
        return month;
    }
    
    public String displayMonthRelease(int month) {    
        String displayMonth = "";
        
        switch(month) {
            case 1:
                displayMonth = "January";
                break;
            case 2:
                displayMonth = "February";
                break;
            case 3:
                displayMonth = "March";
                break;
            case 4:
                displayMonth = "April";
                break;
            case 5:
                displayMonth = "May";
                break;
            case 6:
                displayMonth = "June";
                break;
            case 7:
                displayMonth = "July";
                break;
            case 8:
                displayMonth = "August";
                break;
            case 9:
                displayMonth = "September";
                break;
            case 10:
                displayMonth = "October";
                break;
            case 11:
                displayMonth = "November";
                break;
            case 12:
                displayMonth = "December";
                break;
            default:
                io.print("This shouldn't be happening ?!?!");
                break;
        }
        return displayMonth;
    }
    
    public String getDateRelease(int month) {
        int date = 0;
        String displayMonth = displayMonthRelease(month);
        String displayDate = "";
        
        io.print("What date was it released on " + displayMonth + "?");
        
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            date = io.readInt("Enter date beween 1 - 31: ", 1, 31);
        } else if(month == 4 || month == 6 || month == 8 || month == 9 || month == 11) {
            date = io.readInt("Enter date between 1 - 30: ", 1, 30);
        } else if(month == 2) {
            date = io.readInt("Enter date between 1 - 29: ", 1, 29);
        } else {
            io.print("This shouldn't be happening ?!?! ");
        }
        
        displayDate = Integer.toString(date);
        
        return displayDate;
    }
    
    public String getYearRelease(int currentYear) {
        int year = 0;
        String displayYear = "";
        
        year = io.readInt("What year was it released?", 1880, currentYear);
        displayYear = Integer.toString(year);
        
        return displayYear;
    }
    
    public int getMPAARating() {
        int rating = 0;
        
        io.print("1 - G: General Audiences. All Ages Admitted.");
        io.print("2 - PG: Parental Guidance Suggested. Some Material May Not Be Suitable For Children.");
        io.print("3 - PG-13: Parents Strongly Cautioned. Some Material May Be Inappropriate For Children Under 13.");
        io.print("4 - R: Restricted. Children Under 17 Require Accompanying Parent or Legal Guardian.");
        io.print("5 - NC-17: No One 17 and Under Admitted.");
        
        rating = io.readInt("Choose the numerical equivalent for MPAA Rating: ", 1, 5);
        
        return rating;
    }
    
    public String displayMPAARating(int rating) {
        String displayRating = "";
        
        switch(rating) {
            case 1:
                displayRating = "G - General Audiences. All Ages Admitted.";
                break;
            case 2:
                displayRating = "PG - Parental Guidance Suggested. Some Material May Not Be Suitable For Children.";
                break;
            case 3:
                displayRating = "PG-13 - Parents Strongly Cautioned. Some Material May Be Inappropriate For Children Under 13.";
                break;
            case 4:
                displayRating = "R - Restricted. Children Under 17 Require Accompanying Parent or Legal Guardian.";
                break;
            case 5:
                displayRating = "NC-17 - No One 17 and Under Admitted.";
                break;
            default:
                io.print("Is this even possible ?!?! ");
                break;
        }
        return displayRating;
    }
    
    public void displayAddDvdBanner() {
        io.print("=== Add DVD ===");
    }
    
    public void displayAddSuccessBanner() {
        io.print("");
        io.print("SUCCESSFULLY ADDED DVD TO LIBRARY.");
    }
    
    public void displayEditDvdBanner() {
        io.print("=== Edit DVD ===");
    }
    
    public String confirmEdit(Dvd dvd) {
        String confirmChoice = "";
        
        if(dvd != null) {
            displayDvd(dvd, "ARE YOU SURE YOU WANT TO EDIT");
            confirmChoice = io.readString("(y / n)");
        } else {
            io.print("");
            io.print("That DVD does not exist in your Library");
            confirmChoice = "x";
        }
        
        return confirmChoice;
    }
    
    public Dvd editDvdInfo(Dvd dvd) {
        String dvdTitle = dvd.getDvdTitle();
        String confirmReleaseDate = "";
        String confirmRating = "";
        String confirmDirector = "";
        String confirmStudio = "";
        String confirmUserNote = "";
        
        String releaseDate = "";
        String rating = "";
        String director = "";
        String studio = "";
        String userNote = "";
        
        Dvd editedDvd = new Dvd(dvdTitle);
        
        io.print("=== We Are Editing - " + dvdTitle.toUpperCase() + " - ===");
        
        io.print("CURRENT RELEASE DATE: " + dvd.getReleaseDate());
        confirmReleaseDate = io.readString("Would you like to edit release date? (y / n)");
        if (confirmReleaseDate.equalsIgnoreCase("y")) {
            io.print("");
            io.print("Please enter Release Date: (MM/DD/YY)");
            int month = getMonthRelease();
            String displayMonth = displayMonthRelease(month);
            String date = getDateRelease(month);
            String year = getYearRelease(2021);
            releaseDate += displayMonth + " ";
            releaseDate += date + " ";
            releaseDate += year;
            editedDvd.setReleaseDate(releaseDate);
            io.print("UPDATED: " + releaseDate + " was entered.");
        } else {
            io.print("NO UPDATES");
            releaseDate = dvd.getReleaseDate();
            editedDvd.setReleaseDate(releaseDate);
        }
        
        io.print("");
        io.print("CURRENT MPAA RATING: " + dvd.getMpaaRating());
        confirmRating = io.readString("Would you like to edit MPAA Rating? (y / n)");
        if(confirmRating.equalsIgnoreCase("y")) {
            io.print("");
            io.print("Please enter the MPAA Rating:");
            int ratingInt = getMPAARating();
            String mpaaRating = displayMPAARating(ratingInt);
            editedDvd.setMpaaRating(mpaaRating);
            io.print("UPDATED: MPAA Rating");
        } else {
            io.print("NO UPDATES");
            rating = dvd.getMpaaRating();
            editedDvd.setMpaaRating(rating);
        }
        
        io.print("");
        io.print("CURRENT DIRECTOR: " + dvd.getDirectorName());
        confirmDirector = io.readString("Would you like to edit Director Name? (y / n)");
        if(confirmDirector.equalsIgnoreCase("y")) {
            io.print("");
            director = io.readString("Please enter new Director Name:");
            editedDvd.setDirectorName(director);
            io.print("UPDATED: Director Name");
        } else {
            io.print("NO UPDATES");
            director = dvd.getDirectorName();
            editedDvd.setDirectorName(director);
        }
        
        io.print("");
        io.print("CURRENT STUDIO: " + dvd.getStudio());
        confirmStudio = io.readString("Would you like to edit Studio Production? (y / n)");
        if(confirmStudio.equalsIgnoreCase("y")) {
            io.print("");
            studio = io.readString("Please enter new Studio Production:");
            editedDvd.setStudio(studio);
            io.print("UPDATED: Studio Production");
        } else {
            io.print("NO UPDATES");
            studio = dvd.getStudio();
            editedDvd.setStudio(studio);
        }
        
        io.print("");
        io.print("CURRENT USER RATING (NOTE): " + dvd.getUserRatingNote());
        confirmUserNote = io.readString("Would you like to edit User Rating or Note? (y / n)");
        if(confirmUserNote.equalsIgnoreCase("y")) {
            io.print("");
            userNote = io.readString("Please enter new User Rating or Note:");
            editedDvd.setUserRatingNote(userNote);
            io.print("UPDATED: User Rating or Note");
        } else {
            io.print("NO UPDATES");
            userNote = dvd.getUserRatingNote();
            editedDvd.setUserRatingNote(userNote);
        }
        
        return editedDvd;
        
    }
    
    public String anotherOne(String currentOption) {
        String confirmAnother = "";
        
        confirmAnother = io.readString("Would you like to " + currentOption + " another DVD? (y / n)");
        
        if(confirmAnother.equalsIgnoreCase("y")) {
            confirmAnother = "y";
        } else if(confirmAnother.equalsIgnoreCase("n")) {
            confirmAnother = "x";
        } else {
            io.print("That's not an option. We're going back to Main Menu");
            io.print("");
            confirmAnother = "x";
        }
        
        return confirmAnother;
    }
    
    public void displayDvdList(List<Dvd> dvdList){
        for (Dvd currentDvd : dvdList) {
            String displayTitle = ("TITLE: " + currentDvd.getDvdTitle());
            io.print(displayTitle);
            
            String displayReleaseDate = ("RELEASE DATE: " + currentDvd.getReleaseDate());
            io.print (displayReleaseDate);
            
            io.print("RATED: " + currentDvd.getMpaaRating());
            io.print("DIRECTOR: " + currentDvd.getDirectorName());
            io.print("STUDIO: " + currentDvd.getStudio());
            io.print("USER RATING (NOTE): " + currentDvd.getUserRatingNote());
            io.print("");
        }
        io.readString("Please hit ENTER to return to Main Menu");
    }
    
    public void displayListAllBanner() {
        io.print("=== List All DVDs ===");
    }
    
    public void displayFindDvdBanner() {
        io.print("=== Find DVD ===");
    }
    
    public String getDvdChoice(String option) {
        return io.readString("Please enter the DVD Title you would like to " + option + ": ");
    }
    
    public void displayDvd(Dvd dvd, String optionMsg) {
        if(dvd != null) {
            io.print("");
            io.print("TITLE: " + dvd.getDvdTitle());
            io.print("RELEASE DATE: " + dvd.getReleaseDate());
            io.print("RATED: " + dvd.getMpaaRating());
            io.print("DIRECTOR: " + dvd.getDirectorName());
            io.print("STUDIO: " + dvd.getStudio());
            io.print("USER RATING (NOTE): " + dvd.getUserRatingNote());
            io.print("");
            io.print(optionMsg + " - " + dvd.getDvdTitle().toUpperCase() + " - ");
        } else {
            io.print("");
            io.print("That DVD does not exist in your Library");
        } 
    }
    
    public void displayRemoveDvdBanner() {
        io.print("=== Remove DVD ===");
    }
    
    public String confirmRemoval(Dvd dvd) {
        String confirmChoice = "";
        
        if(dvd != null) {
            displayDvd(dvd, "ARE YOU SURE YOU WANT TO REMOVE");
            confirmChoice = io.readString("(y / n)");
        } else {
            io.print("");
            io.print("That DVD does not exist in your Library");
            confirmChoice = "x";
        }
        
        return confirmChoice;
    }
    
    /*
    public void displayRemoveResult(Dvd dvdRecord) {
        if(dvdRecord != null) {
            io.print("Dvd successfully removed.");
        } else {
            io.print("That DVD does not exist in your Library");
        }
        io.readString("Please hit enter to continue.");
    }
    */
    
    public void displayExitBanner() {
        io.print("Good Bye !!! ");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command !!! ");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
