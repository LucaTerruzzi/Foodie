/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoweb.logic;

import it.progettoweb.data.Location;
import it.progettoweb.data.Restaurant;
import it.progettoweb.db.DBManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringJoiner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class for logging out users
 * @author Luca, Riccardo, Mario
 */
public class RetrieveRestaurant extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //you shouldn't reach this servlet via GET !!!
        //response.sendRedirect("index.jsp");
        doPost(request, response);
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
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e){
            response.sendRedirect("index.jsp");
            return;
        }

        Restaurant restaurant = dbmanager.getRestaurantById(id);

        if(restaurant == null){
            response.sendRedirect("index.jsp");
        }else{
            request.setAttribute("restaurant", restaurant);
            getServletContext().getRequestDispatcher("/restaurant.jsp").forward(request, response);
        }
    }


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Retrieve restaurant";
    }

}