/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoweb.logic;

import it.progettoweb.data.Location;
import it.progettoweb.data.Restaurant;

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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1482);
        restaurant.setName("Procinto");
        restaurant.setDescription("Il procinto è molto fico");
        restaurant.setLink("www.garfer.it");
        restaurant.setPriceRange(3);
        restaurant.setRating(4);
        restaurant.setOpeningHours("Mon-Sat: 11-22 | Sun: Closed");

        ArrayList<String> cuisines = new ArrayList<String>();
        cuisines.add("povera");
        cuisines.add("ricca");
        cuisines.add("costosa");
        restaurant.setCuisine(cuisines);

        Location location = new Location();
        location.setCity("Pordenone");
        location.setAddress("Parco della Vittoria, 47");

        restaurant.setLocation(location);

        request.setAttribute("restaurant", restaurant);

        getServletContext().getRequestDispatcher("/restaurant.jsp").forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Retrieve restaurant";
    }// </editor-fold>

}