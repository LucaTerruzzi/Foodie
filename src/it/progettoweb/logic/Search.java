/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoweb.logic;

import it.progettoweb.data.SearchResult;
import it.progettoweb.data.User;
import it.progettoweb.db.DBManager;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet for managing searches
 * @author Luca, Riccardo, Mario
 */
public class Search extends HttpServlet {

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

        String term = request.getParameter("term");
        int type;
        try {
            type = Integer.parseInt(request.getParameter("type"));
        } catch (NumberFormatException e){
            response.sendRedirect("index.jsp");
            return;
        }

        //if necessary parameters are null, something wrong happened
        if(term == null){
            response.sendRedirect("index.jsp");
            return;
        }

        ArrayList<SearchResult> results = null;
        switch (type){
            case 0:
                results = dbmanager.getRestaurants(term);
                break;
            case 1:
                results = dbmanager.getRestaurantsByCity(term);
                break;
            case 2:
                results = dbmanager.getRestaurantsByRegion(term);
                break;
            default:
        }

        if(results == null){
            response.sendRedirect("index.jsp");
        }else{
            request.setAttribute("results", results);
            getServletContext().getRequestDispatcher("/searchResults.jsp").forward(request, response);
        }

    }



    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Manages searches";
    }

}