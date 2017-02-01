package it.progettoweb.logic;

import it.progettoweb.data.Review;
import it.progettoweb.data.User;
import it.progettoweb.db.DBManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

/**
 * Servlet which manages storing review
 * @author Luca, Riccardo, Mario
 */
public class StoreReview extends HttpServlet {

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

        // Parameters of the form
        String title, body;
        int rating, id;

        // Retrieve parameters values
        title = request.getParameter("title");
        body = request.getParameter("desc");

        // Parsing to integer
        try {
            rating = Integer.parseInt(request.getParameter("rating"));
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e){
            response.sendRedirect("index.jsp");
            return;
        }

        // If mandatory parameters are null
        if(title == null || body == null){
            response.sendRedirect("index.jsp");
            return;
        }

        // Rating must be between 1 and 5
        if(rating < 1 || rating > 5){
            // Invalid Rating
            response.sendRedirect("writeRev.jsp?error=1&id="+id);
            return;
        }

        // Title length must be between 3 and 63
        if(title.length() < 3 || title.length() > 63){
            // Invalid Title
            response.sendRedirect("writeRev.jsp?error=2&id="+id);
            return;
        }

        // Body length must be between 16 and 1023
        if(body.length() < 16 || body.length() > 1023){
            // Invalid Description
            response.sendRedirect("writeRev.jsp?error=3&id="+id);
            return;
        }

        HttpSession session = request.getSession();

        // Date checking
        LocalDate lastDate = dbmanager.lastReviewDate(id, ((User)session.getAttribute("user")).getEmail());
        if(lastDate == null || !lastDate.isBefore(LocalDate.now())){
            response.sendRedirect("index.jsp");
            return;
        }

        // Filling of review object
        Review review = new Review();
        review.setTitle(title);
        review.setBody(body);
        review.setRating(rating);
        review.setDate(LocalDate.now());
        review.setAuthor(((User)session.getAttribute("user")).getEmail());
        review.setRestaurant(id);

        if(dbmanager.saveReview(review)){
            // Redirects to the restaurant
            response.sendRedirect("RetrieveRestaurant?id="+id);
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
        return "Manages storing reviews";
    }

}
