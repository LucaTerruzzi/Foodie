/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoweb.logic;

import it.progettoweb.data.ConfirmationMail;
import it.progettoweb.data.Review;
import it.progettoweb.data.User;
import it.progettoweb.db.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.internet.*;
import javax.mail.*;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.*;

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
        // initialize dbmanager attribute
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

        //you shouldn't reach this servlet via GET !!!
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

        //Parameters of the form
        String title, body;
        int rating, id;
        //Retrieve parameters values
        title = request.getParameter("title");
        body = request.getParameter("desc");
        try {
            rating = Integer.parseInt(request.getParameter("rating"));
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e){
            response.sendRedirect("index.jsp");
            return;
        }


        //if necessary parameters are null, something wrong happened
        if(title == null || body == null){
            response.sendRedirect("index.jsp");
            return;
        }

        // validate input
        if(rating < 1 || rating > 5){
            response.sendRedirect("writeRev.jsp?error=1&id="+id);
            return;
        }

        if(title.length() < 3 || title.length() > 63){
            response.sendRedirect("writeRev.jsp?error=2&id="+id);
            return;
        }

        if(body.length() < 16 || body.length() > 1023){
            response.sendRedirect("writeRev.jsp?error=3&id="+id);
            return;
        }

        HttpSession session = request.getSession();
        LocalDate lastDate = dbmanager.lastReviewDate(id, ((User)session.getAttribute("user")).getEmail());
        if(lastDate == null || !lastDate.isBefore(LocalDate.now())){
            response.sendRedirect("index.jsp");
            return;
        }
        Review review = new Review();
        review.setTitle(title);
        review.setBody(body);
        review.setRating(rating);
        //review.setDate(new Date());
        review.setDate(LocalDate.now());
        review.setAuthor(((User)session.getAttribute("user")).getEmail());
        review.setRestaurant(id);
        //get user email

        if(dbmanager.saveReview(review)){
            response.sendRedirect("writeRev.jsp?message=1&id="+id);
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
