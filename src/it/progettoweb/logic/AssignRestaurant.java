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
public class AssignRestaurant extends HttpServlet {

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
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e){
            response.sendRedirect("index.jsp");
            return;
        }

        HttpSession session = request.getSession();
        if(request.getParameter("dismiss") != null){
            if(dbmanager.dismissNotification(id)){
                response.sendRedirect("index.jsp?message=6");
                ((User)session.getAttribute("user")).setNotifications(dbmanager.getAdminNotifications());
                return;
            }
        }

        if(request.getParameter("accept") != null){
            if(dbmanager.assignRestaurant(id)){
                ((User)session.getAttribute("user")).setNotifications(dbmanager.getAdminNotifications());
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
        return "Retrieve restaurant";
    }

}