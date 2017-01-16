/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoweb.logic;

import it.progettoweb.data.User;
import it.progettoweb.db.DBManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luca
 */
public class Login extends HttpServlet {

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
        
        String name = request.getParameter("name");
        String password = request.getParameter("password");
           
        //if necessary parameters are null, something wrong happened
        if(name == null || password == null){
            response.sendRedirect("index.jsp");
        }
        
        //authentiate user
        User user = dbmanager.authenticate(name, password);
        
        if(user == null){
            //user not present in DB
            //request.setAttribute("errorCode", 1);
            //request.getRequestDispatcher("index.jsp").forward(request, response);
            response.sendRedirect("index.jsp?error=1");
        }else{
            //user present in DB. save in session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("userType", user.getUserType());
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
        return "Manages login process";
    }// </editor-fold>

}
