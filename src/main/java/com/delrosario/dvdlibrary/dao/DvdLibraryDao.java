/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.delrosario.dvdlibrary.dao;

import com.delrosario.dvdlibrary.dto.Dvd;
import java.util.List;

/**
 *
 * @author Daryl del Rosario
 */
public interface DvdLibraryDao {
    Dvd addDvd(String dvdTitle, Dvd dvd) throws DvdLibraryDaoException;
    Dvd editDvd(String dvdTitle, Dvd dvd) throws DvdLibraryDaoException;
    Dvd removeDvd(String dvdTitle) throws DvdLibraryDaoException;
    List<Dvd> getAllDvds() throws DvdLibraryDaoException;
    Dvd findDvd(String dvdTitle) throws DvdLibraryDaoException;
    
    // Once basic completed - implement an edit method
    
}
