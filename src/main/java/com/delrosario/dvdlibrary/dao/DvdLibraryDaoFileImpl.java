/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.delrosario.dvdlibrary.dao;

import com.delrosario.dvdlibrary.dto.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Daryl del Rosario
 */
public class DvdLibraryDaoFileImpl implements DvdLibraryDao {
    public static final String DVDLIBRARY_FILE = "dvdLibrary.txt";
    public static final String DELIMITER = "::";
    
    private Map<String, Dvd> fullLibrary = new HashMap<>();
    
    @Override
    public Dvd addDvd(String dvdTitle, Dvd dvd) throws DvdLibraryDaoException {
        loadLibrary();
        Dvd newDvd = fullLibrary.put(dvdTitle, dvd);
        writeLibrary();
        return newDvd;
    }
    
    @Override
    public Dvd editDvd(String dvdTitle, Dvd dvd) throws DvdLibraryDaoException {
        loadLibrary();
        Dvd editedDvd = fullLibrary.put(dvdTitle, dvd);
        writeLibrary();
        return editedDvd;
    }

    @Override
    public Dvd removeDvd(String dvdTitle) throws DvdLibraryDaoException {
        loadLibrary();
        Dvd removedDvd = fullLibrary.remove(dvdTitle);
        writeLibrary();
        return removedDvd;
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException {
        loadLibrary();
        return new ArrayList<Dvd>(fullLibrary.values());
    }

    @Override
    public Dvd findDvd(String dvdTitle) throws DvdLibraryDaoException {
        loadLibrary();
        return fullLibrary.get(dvdTitle);
    }
    
    private Dvd unmarshallDvd(String dvdAsText) {
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        String dvdTitle = dvdTokens[0];
        Dvd dvdFromFile = new Dvd(dvdTitle);
        dvdFromFile.setReleaseDate(dvdTokens[1]);
        dvdFromFile.setMpaaRating(dvdTokens[2]);
        dvdFromFile.setDirectorName(dvdTokens[3]);
        dvdFromFile.setStudio(dvdTokens[4]);
        dvdFromFile.setUserRatingNote(dvdTokens[5]);
        return dvdFromFile;
    }
    
    private void loadLibrary() throws DvdLibraryDaoException {
        Scanner sc;
        
        try {
            sc = new Scanner(new BufferedReader(new FileReader(DVDLIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException("-_- Could not load roster data into memory.", e);
        }
        
        String currentLine;
        Dvd currentDvd;
        
        while(sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentDvd = unmarshallDvd(currentLine);
            
            fullLibrary.put(currentDvd.getDvdTitle(), currentDvd);
        }
        sc.close();
    }
    
    private String marshallDvd(Dvd aDvd) {
        String dvdAsText = aDvd.getDvdTitle() + DELIMITER;
        dvdAsText += aDvd.getReleaseDate() + DELIMITER;
        dvdAsText += aDvd.getMpaaRating() + DELIMITER;
        dvdAsText += aDvd.getDirectorName() + DELIMITER;
        dvdAsText += aDvd.getStudio() + DELIMITER;
        dvdAsText += aDvd.getUserRatingNote();
        return dvdAsText;
    }
    
    private void writeLibrary() throws DvdLibraryDaoException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(DVDLIBRARY_FILE));
        } catch (IOException e) {
            throw new DvdLibraryDaoException("Could not save DVD data.", e);
        }
        
        String dvdAsText;
        List dvdList = this.getAllDvds();
        for (Object currentDvd : dvdList) {
            dvdAsText = marshallDvd((Dvd) currentDvd);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }
}
