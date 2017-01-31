package it.progettoweb.logic;

import it.progettoweb.data.User;
import it.progettoweb.db.DBManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class for assigning restaurants
 * @author Luca, Riccardo, Mario
 */
public class AssignRestaurant extends HttpServlet {

    private DBManager dbmanager;
    /**
     * initialize DBManager attribute
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        // Initialize dbmanager attribute
        this.dbmanager = (DBManager)super.getServletContext().getAttribute("dbmanager");
    }


    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // This servlet shouldn't be reached via GET
        response.sendRedirect("index.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id;
        // Parsing to integer
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e){
            response.sendRedirect("index.jsp");
            return;
        }

        // If notification is dismissed
        HttpSession session = request.getSession();
        if(request.getParameter("dismiss") != null){
            if(dbmanager.dismissNotification(id)){
                // Notification dismissed
                response.sendRedirect("index.jsp?message=6");
                // Notification refresh
                ((User)session.getAttribute("user")).setNotifications(dbmanager.getAdminNotifications());
                return;
            }
        }

        // If notification is accepted, sets the owner
        if(request.getParameter("accept") != null){
            if(dbmanager.assignRestaurant(id)){
                // Notification refresh
                ((User)session.getAttribute("user")).setNotifications(dbmanager.getAdminNotifications());
                // Owner set
                response.sendRedirect("index.jsp?message=7");
                return;
            }
        }

        response.sendRedirect("index.jsp");
    }


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Assign restaurant";
    }

}