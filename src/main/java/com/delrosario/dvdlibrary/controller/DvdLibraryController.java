/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.delrosario.dvdlibrary.controller;

import com.delrosario.dvdlibrary.dao.DvdLibraryDao;
import com.delrosario.dvdlibrary.dao.DvdLibraryDaoException;
import com.delrosario.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.delrosario.dvdlibrary.dto.Dvd;
import com.delrosario.dvdlibrary.ui.DvdLibraryView;
import com.delrosario.dvdlibrary.ui.UserIO;
import com.delrosario.dvdlibrary.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author Daryl del Rosario
 */
public class DvdLibraryController {

    private DvdLibraryView view;
    private DvdLibraryDao dao;
    private UserIO io = new UserIOConsoleImpl();
    
    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view) {
        this.dao = dao;
        this.view = view;
    }
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();
            
                switch(menuSelection) {
                    case 1:
                        addDvd();
                        break;
                    case 2: 
                        removeDvd();
                        break;
                    case 3:
                        io.print("EDIT DVD");
                        editDvd();
                        break;
                    case 4:
                        listAllDvds();
                        break;
                    case 5:
                        findDvd();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (DvdLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void addDvd() throws DvdLibraryDaoException {
        String checkAnother = "";
        
        do {
            view.displayAddDvdBanner();
            Dvd newDvd = view.getNewDvdInfo();
            dao.addDvd(newDvd.getDvdTitle(), newDvd);
            view.displayAddSuccessBanner();
            
            checkAnother = view.anotherOne("add");
        } while (checkAnother.equalsIgnoreCase("y"));
    }
    
    private void editDvd() throws DvdLibraryDaoException {
        String checkAnother = "";
        view.displayEditDvdBanner();
        
        do {
            String dvdTitle = view.getDvdChoice("edit");
            Dvd dvdToBeEdited = dao.findDvd(dvdTitle);
            String confirmChoice = view.confirmEdit(dvdToBeEdited);
        
            if(confirmChoice.equalsIgnoreCase("y")) {
                Dvd editedDvd = view.editDvdInfo(dvdToBeEdited);
                dao.editDvd(editedDvd.getDvdTitle(), editedDvd);
                io.print("");
                io.print("SUCCESSFULLY EDITED - " + editedDvd.getDvdTitle().toUpperCase() + " - ");
            } else if(confirmChoice.equalsIgnoreCase("n")) {
                io.print("YOU SAID NO! therefore NO DVD WAS EDITED FROM YOUR LIBRARY");
            } else if(confirmChoice.equalsIgnoreCase("x")) {
                io.print("MAYBE NEXT TIME ?!");
                io.print("");
            } else {
                io.print("THAT'S NOT A VALID INPUT! BACK TO MAIN MENU!");
                io.print("");
                break;
            }
            checkAnother = view.anotherOne("edit");
        } while(checkAnother.equalsIgnoreCase("y"));
        
    }
    
    private void listAllDvds() throws DvdLibraryDaoException {
        view.displayListAllBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }
    
    private void findDvd() throws DvdLibraryDaoException {
        String checkAnother = "";
        view.displayFindDvdBanner();
        
        do {
            String dvdTitle = view.getDvdChoice("find");
            Dvd dvd = dao.findDvd(dvdTitle);
            view.displayDvd(dvd, "SUCCESSFULLY FOUND");
            
            checkAnother = view.anotherOne("find");
        } while (checkAnother.equalsIgnoreCase("y"));
    }
    
    private void removeDvd() throws DvdLibraryDaoException {
        String checkAnother = "";
        view.displayRemoveDvdBanner();
        
        do {
            String dvdTitle = view.getDvdChoice("remove");
            Dvd dvdToBeRemoved = dao.findDvd(dvdTitle);
            String confirmChoice = view.confirmRemoval(dvdToBeRemoved);
            
            if (confirmChoice.equalsIgnoreCase("y")) {
                dvdToBeRemoved = dao.removeDvd(dvdTitle);
                io.print("SUCCESSFULLY REMOVED - " + dvdTitle.toUpperCase() + " - ");
            } else if (confirmChoice.equalsIgnoreCase("n")) {
                io.print("YOU SAID NO! therefore NO DVD WAS REMOVED FROM YOUR LIBRARY");
            } else if (confirmChoice.equalsIgnoreCase("x")) {
                io.print("MAYBE NEXT TIME ?!");
                io.print("");
            } else {
                io.print("THAT'S NOT A VALID INPUT! BACK TO MAIN MENU!");
                io.print("");
                break;
            }
            checkAnother = view.anotherOne("remove");
        } while (checkAnother.equalsIgnoreCase("y"));
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
}
