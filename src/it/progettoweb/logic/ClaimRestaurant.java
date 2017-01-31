package it.progettoweb.logic;

import it.progettoweb.data.Notification;
import it.progettoweb.data.User;
import it.progettoweb.db.DBManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet which manages claiming restaurants
 * @author Luca, Riccardo, Mario
 */
public class ClaimRestaurant extends HttpServlet {

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id;
        // Parsing to integer
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e){
            response.sendRedirect("index.jsp");
            return;
        }

        HttpSession session = request.getSession();
        Notification notification = new Notification();
        notification.setRestaurantClaimed(id);
        notification.setRestaurantClaimer(((User)session.getAttribute("user")).getEmail());
        notification.setText("The user " + ((User)session.getAttribute("user")).getEmail() + " has claimed a restaurant.");
        // 0 -> Restaurant claiming
        notification.setType(0);

        // If the restaurant already has an owner
        if(dbmanager.hasOwner(id)){
            response.sendRedirect("index.jsp");
            return;
        }

        // If the notification has been successfully created
        if(dbmanager.placeClaimNotification(notification)){
            // Restaurant claimed
            response.sendRedirect("index.jsp?message=5");
        }else{
            response.sendRedirect("index.jsp");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Manages claiming restaurants";
    }

}