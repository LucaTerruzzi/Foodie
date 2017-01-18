/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoweb.logic;

import it.progettoweb.data.ConfirmationMail;
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
import java.util.*;

/**
 * Servlet which manages edit info
 * @author Luca, Riccardo, Mario
 */
public class EditInfos extends HttpServlet {

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
        String name, surname, passwordOld, password, passwordRep;

        //Retrieve parameters values
        name = request.getParameter("name");
        surname = request.getParameter("surname");
        passwordOld = request.getParameter("password-old");
        password = request.getParameter("password");
        passwordRep = request.getParameter("password-rep");

        //get user email
        HttpSession session = request.getSession();
        String email = ((User)session.getAttribute("user")).getEmail();

        String path = request.getPathInfo();

        switch (path){
            case "/name":
                if(changeName(name, email, session)){
                    response.sendRedirect("/user.jsp");
                    return;
                }else{
                    response.sendRedirect("/user.jsp?error=1");
                    return;
                }

            case "/surname":
                if(changeSurname(surname, email, session)){
                    response.sendRedirect("/user.jsp");
                    return;
                }else{
                    response.sendRedirect("/user.jsp?error=2");
                    return;
                }

            case "/pwd":
                if(changePassword(passwordOld, password, passwordRep, email)){
                    response.sendRedirect("/user.jsp?message=1");
                    return;
                }else{
                    response.sendRedirect("/user.jsp?error=3");
                    return;
                }

            default:
                response.sendRedirect("/index.jsp");

        }

    }


    private boolean changeName(String name, String email, HttpSession session){
        //if !exist
        if(name == null){
            return false;
        }
        //name length
        if(name.length() < 2 || name.length() > 20){
            return false;
        }

        User mod = dbmanager.changeName(name, email);
        if(mod == null) {
            return false;
        }

        session.setAttribute("user", mod);
        return true;
    }

    private boolean changeSurname(String surname, String email, HttpSession session){
        //if !exist
        if(surname == null){
            return false;
        }
        //surname length
        if(surname.length() < 2 || surname.length() > 20){
            return false;
        }

        User mod = dbmanager.changeSurname(surname, email);
        if(mod == null) {
            return false;
        }

        session.setAttribute("user", mod);
        return true;
    }

    private boolean changePassword(String passwordOld, String password, String passwordRep, String email){
        //if !exist
        if(password == null || passwordOld == null || passwordRep == null){
            return false;
        }

        //old password length
        if(passwordOld.length() < 6 || passwordOld.length() > 20){
            return false;
        }

        //password length
        if(password.length() < 6 || password.length() > 20){
            return false;
        }

        //password equal
        if(!password.equals(passwordRep)){
            return false;
        }

        if(!dbmanager.changePassword(password, email)) {
            return false;
        }

        return true;
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
