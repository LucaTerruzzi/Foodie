/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoweb.logic;

import it.progettoweb.db.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.internet.*;
import javax.mail.*;
import java.util.*;

/**
 *
 * @author Luca
 */
public class Register extends HttpServlet {

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
        String name, surname, username, email, emailRep, password, passwordRep, terms;
        
        //Rretrieve parameters values
        name = request.getParameter("name");
        surname = request.getParameter("surname");
        username = request.getParameter("username");
        email = request.getParameter("email");
        emailRep = request.getParameter("email-rep");
        password = request.getParameter("password");
        passwordRep = request.getParameter("password-rep");
        terms = request.getParameter("terms");
        
        //If these parameters are null something went wrong (should never happen!)
        if(name == null || surname == null || username == null || email == null || emailRep == null || password == null || passwordRep == null){
            response.sendRedirect("index.jsp");
        }
        
        //Parameters error check (already performed via javascript. Server side control for security reasons).
        
        //name
        if(name.length() < 2 || name.length() > 20){
            response.sendRedirect("register.jsp?error=1");
        }
        
        //surname
        if(surname.length() < 2 || surname.length() > 20){
            response.sendRedirect("register.jsp?error=1");
        }
        
        //name
        if(username.length() < 3 || username.length() > 20){
            response.sendRedirect("register.jsp?error=1");
        }

        /*
        boolean result = true;

        try {
           InternetAddress emailAddr = new InternetAddress(email);
           emailAddr.validate();
        } catch (AddressException ex) {
           result = false;
        }
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("InternetAddress ->"+result);
        out.close();
        */
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Manages register process";
    }

}
