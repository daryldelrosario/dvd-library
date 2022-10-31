/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.delrosario.dvdlibrary;

import com.delrosario.dvdlibrary.controller.DvdLibraryController;
import com.delrosario.dvdlibrary.dao.DvdLibraryDao;
import com.delrosario.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.delrosario.dvdlibrary.ui.DvdLibraryView;
import com.delrosario.dvdlibrary.ui.UserIO;
import com.delrosario.dvdlibrary.ui.UserIOConsoleImpl;

/**
 *
 * @author Daryl del Rosario
 */
public class App {

    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        DvdLibraryView myView = new DvdLibraryView(myIo);
        DvdLibraryDao myDao = new DvdLibraryDaoFileImpl();
        DvdLibraryController controller = new DvdLibraryController(myDao, myView);
        controller.run();
    }
}
