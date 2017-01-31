/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoweb.logic;

import it.progettoweb.data.Location;
import it.progettoweb.data.Restaurant;
import it.progettoweb.data.User;
import it.progettoweb.db.DBManager;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.glassfish.jersey.server.model.Suspendable;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
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
        int canreview = 0;

        if(restaurant == null){
            response.sendRedirect("index.jsp");
        }else{
            HttpSession session = request.getSession();
            if((int)session.getAttribute("userType") != 0) {
                LocalDate lastDate = dbmanager.lastReviewDate(id, ((User)session.getAttribute("user")).getEmail());
                if (lastDate != null && lastDate.isBefore(LocalDate.now())) {
                    canreview = 1;
                }
            }

            placeQR(restaurant);

            request.setAttribute("restaurant", restaurant);
            request.setAttribute("canreview",canreview);
            getServletContext().getRequestDispatcher("/restaurant.jsp").forward(request, response);
        }
    }



    private void placeQR(Restaurant restaurant) throws IOException{
        URL qrUrl = getServletContext().getResource("/pics/" + restaurant.getId());
        File qrFile = new File(qrUrl.getPath() + "/" + restaurant.getId() + "_qr.jpg");

        if(!qrFile.isFile()){
            StringBuilder text = new StringBuilder();
            text.append(restaurant.getName());
            text.append("\n");
            text.append(restaurant.getLocation().getAddress()).append(", ").append(restaurant.getLocation().getCity());
            text.append(", ").append(restaurant.getLocation().getState());
            text.append("\n");
            text.append(restaurant.getOpeningHours());
            ByteArrayOutputStream qrStream = QRCode.from(text.toString()).to(ImageType.JPG).withSize(256, 256).stream();
            try(OutputStream outputStream = new FileOutputStream(qrFile)) {
                qrStream.writeTo(outputStream);
            }
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