package it.progettoweb.logic;

import it.progettoweb.db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet which manages password change for recovery
 * @author Luca, Riccardo, Mario
 */

public class PwdRecovery extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //you shouldn't reach this servlet via POST !!!
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parameters of the POST
        String email, token, password, passwordRep;

        // retrieve parameters
        email = request.getParameter("user");
        token = request.getParameter("token");
        password = request.getParameter("password");
        passwordRep = request.getParameter("password-rep");

        //If these parameters are null something went wrong (should never happen!)
        if(email == null || token == null || password == null || passwordRep == null){
            response.sendRedirect("index.jsp");
            return;
        }

        //password length
        if(password.length() < 6 || password.length() > 20){
            response.sendRedirect("pwdChange.jsp?user=" + email + "&token" + token + "&error=1");
            return;
        }

        //password equal
        if(!password.equals(passwordRep)){
            response.sendRedirect("pwdChange.jsp?user=" + email + "&token" + token + "&error=2");
            return;
        }

        //change password in DB
        if(dbmanager.changePassword(email, token, password)){
            response.sendRedirect("index.jsp?message=4");
        }else{
            response.sendRedirect("index.jsp?error=2");
        }


    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Manages password change for recovery";
    }
}
