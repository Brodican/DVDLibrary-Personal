/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.dvdlibrary.app;

import sg.dvdlibrary.controller.DVDLibraryController;
import sg.dvdlibrary.dao.DVDLibraryDao;
import sg.dvdlibrary.dao.DVDLibraryDaoFileImp;
import sg.dvdlibrary.ui.DVDLibraryView;
import sg.dvdlibrary.ui.UserIO;
import sg.dvdlibrary.ui.UserIOConsoleImp;

/**
 *
 * @author utkua
 */
public class App {

    public static void main(String[] args) {
        
        // For dependency injection - don't want instantiation in other classes.
        UserIO userIO = new UserIOConsoleImp();
        DVDLibraryView view = new DVDLibraryView(userIO);
        
        DVDLibraryDao dao = new DVDLibraryDaoFileImp();
        DVDLibraryController controller = new DVDLibraryController(view, dao);
        controller.run();
    }    
}
