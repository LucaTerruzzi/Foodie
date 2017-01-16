/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoweb.listeners;

import it.progettoweb.db.DBManager;
import java.sql.SQLException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Luca
 */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        //!!!! dburl -> TO BE SET IN web.xml !!!!
        String dburl = sce.getServletContext().getInitParameter("dburl");

        try {

            DBManager manager = new DBManager(dburl);

            sce.getServletContext().setAttribute("dbmanager", manager);

        } catch (SQLException ex) {

            //Logger.getLogger(getClass().getName()).severe(ex.toString());

            throw new RuntimeException(ex);

        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
        DBManager.shutdown();

    }
}
