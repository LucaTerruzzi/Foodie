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
 * Servlet for managing logins
 * @author Luca, Riccardo, Mario
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
           
        // If necessary parameters are null
        if(email == null || password == null){
            response.sendRedirect("index.jsp");
            return;
        }
        
        // Authenticates user
        User user = dbmanager.authenticate(email, password);

        // If user does not exist in DB
        if(user == null){
            response.sendRedirect("index.jsp?error=1");
        }else{
            // The user is present in DB, saves it in the session
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
    }

}
