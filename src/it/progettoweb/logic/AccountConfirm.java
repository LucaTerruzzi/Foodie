package it.progettoweb.logic;

import it.progettoweb.db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet which manages account confirmation
 * @author Luca, Riccardo, Mario
 */

public class AccountConfirm extends HttpServlet {

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
        // Parameters of the GET request
        String email, token;

        // Retrieve parameters
        email = request.getParameter("user");
        token = request.getParameter("token");

        if(email == null || token == null){
            //something wrong !!!
            response.sendRedirect("index.jsp");
            return;
        }

        if(dbmanager.enableAccount(email, token)){
            response.sendRedirect("index.jsp?message=1");
        }else{
            response.sendRedirect("index.jsp?error=2");
        }



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
        //you shouldn't reach this servlet via POST !!!
        response.sendRedirect("index.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Manages account confirmation process";
    }
}
