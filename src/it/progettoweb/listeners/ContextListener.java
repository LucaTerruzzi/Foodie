package it.progettoweb.listeners;

import it.progettoweb.db.DBManager;
import java.sql.SQLException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Luca, Riccardo, Mario
 */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        String dburl = sce.getServletContext().getInitParameter("dburl");

        try {

            DBManager manager = new DBManager(dburl);
            sce.getServletContext().setAttribute("dbmanager", manager);

        } catch (SQLException ex) { throw new RuntimeException(ex); }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
        DBManager.shutdown();

    }
}
