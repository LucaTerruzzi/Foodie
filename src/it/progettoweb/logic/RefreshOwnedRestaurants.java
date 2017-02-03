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
 * Servlet which manages refreshing owned restaurants
 * @author Luca, Riccardo, Mario
 */
public class RefreshOwnedRestaurants extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        // If the user is an admin
        if(((int)session.getAttribute("userType") == 3 || (int)session.getAttribute("userType") == 2) && session.getAttribute("user") != null){
            ((User)session.getAttribute("user")).setOwnedRestaurants(dbmanager.getOwnedRestaurants(((User)session.getAttribute("user")).getEmail()));
            response.sendRedirect("index.jsp");
        }else{
            response.sendRedirect("index.jsp");
        }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // This servlet shouldn't be reached via POST
        response.sendRedirect("index.jsp");

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Manages refreshing owned restaurants";
    }

}