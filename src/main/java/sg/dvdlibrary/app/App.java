/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.dvdlibrary.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
         /*// Instantiate the UserIO implementation
        UserIO myIo = new UserIOConsoleImp();
        // Instantiate the View and wire the UserIO implementation into it
        ClassRosterView myView = new ClassRosterView(myIo);
        // Instantiate the DAO
        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
        // Instantiate the Audit DAO
        ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
        ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao, myAuditDao);
        // Instantiate the Controller and wire the Service Layer into it
        ClassRosterController controller = new ClassRosterController(myView, myService);
        // Kick off the Controller
        controller.run();*/
         
        // For dependency injection - don't want instantiation in other classes.
        /*UserIO userIO = new UserIOConsoleImp();
        DVDLibraryView view = new DVDLibraryView(userIO);
        
        DVDLibraryDao dao = new DVDLibraryDaoFileImp();
        DVDLibraryController controller = new DVDLibraryController(view, dao);
        controller.run();*/
        
        ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        DVDLibraryController controller = 
           ctx.getBean("controller", DVDLibraryController.class);
        controller.run();  
    }    
}
