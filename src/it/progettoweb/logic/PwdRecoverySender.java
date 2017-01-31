package it.progettoweb.logic;

import it.progettoweb.data.RecoveryMail;
import it.progettoweb.db.DBManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet which manages sending password recovery mails
 * @author Luca, Riccardo, Mario
 */

public class PwdRecoverySender extends HttpServlet {

    private DBManager dbmanager;

    /**
     * initialize DBManager attribute
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        // Initialize dbmanager attribute
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
        // This servlet shouldn't be reached via GET
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
        String email;

        // Retrieve mail
        email = request.getParameter("email");

        // If mail is null
        if(email == null){
            response.sendRedirect("index.jsp");
            return;
        }

        // Gets the generated token
        String token = dbmanager.enableRecovery(email);
        // If the token cannot be generated
        if(token == null){
            // Wrong username or password
            response.sendRedirect("pwdRecovery.jsp?error=1");
            return;
        }

        // Send confirmation email
        if(new RecoveryMail(email, token).send()){
            // Recovery email sent. Check email
            response.sendRedirect("index.jsp?message=3");
        }else{
            // Something went wrong
            response.sendRedirect("index.jsp?error=3");
        }


    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Manages sending password recovery mails";
    }
}
