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
        String name, surname, email, emailRep, password, passwordRep, terms;
        
        //Rretrieve parameters values
        name = request.getParameter("name");
        surname = request.getParameter("surname");
        email = request.getParameter("email");
        emailRep = request.getParameter("email-rep");
        password = request.getParameter("password");
        passwordRep = request.getParameter("password-rep");
        terms = request.getParameter("terms");
        
        //If these parameters are null something went wrong (should never happen!)
        if(name == null || surname == null || email == null || emailRep == null || password == null || passwordRep == null){
            response.sendRedirect("index.jsp");
            return;
        }
        
        //Parameters error check (already performed via javascript. Server side control for security reasons).
        
        //name length
        if(name.length() < 2 || name.length() > 20){
            response.sendRedirect("register.jsp?error=1");
            return;
        }
        
        //surname length
        if(surname.length() < 2 || surname.length() > 20){
            response.sendRedirect("register.jsp?error=1");
            return;
        }

        //email validator
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            response.sendRedirect("register.jsp?error=1");
            return;
        }

        //email equal
        if(!email.equals(emailRep)){
            response.sendRedirect("register.jsp?error=2");
            return;
        }

        //password length
        if(password.length() < 6 || password.length() > 20){
            response.sendRedirect("register.jsp?error=1");
            return;
        }

        //password equal
        if(!password.equals(passwordRep)){
            response.sendRedirect("register.jsp?error=3");
            return;
        }

        //terms of service
        if(terms == null || !terms.equals("yes")){
            response.sendRedirect("register.jsp?error=4");
            return;
        }

        //Register in DB or error if already present
        if(dbmanager.register(name, surname, email, password) == 0){
            response.sendRedirect("register.jsp?error=5");
            return;
        }

        //MANDA EMAIL CONFERMA

        response.sendRedirect("index.jsp");
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
