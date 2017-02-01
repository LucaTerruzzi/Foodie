package it.progettoweb.logic;

import it.progettoweb.data.ConfirmationMail;
import it.progettoweb.db.DBManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.internet.*;
// Password strength libraries
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

/**
 * Servlet which manages registration
 * @author Luca, Riccardo, Mario
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
        
        // Parameters of the form
        String name, surname, email, emailRep, password, passwordRep, terms;
        
        // Retrieve parameters values
        name = request.getParameter("name");
        surname = request.getParameter("surname");
        email = request.getParameter("email");
        emailRep = request.getParameter("email-rep");
        password = request.getParameter("password");
        passwordRep = request.getParameter("password-rep");
        terms = request.getParameter("terms");
        
        // If these parameters are null
        if(name == null || surname == null || email == null || emailRep == null || password == null || passwordRep == null){
            response.sendRedirect("index.jsp");
            return;
        }
        
        // Parameters error check (already performed via javascript. Server side control for security reasons).

        // Name length must be between 2 and 20
        if(name.length() < 2 || name.length() > 20){
            response.sendRedirect("register.jsp?error=1");
            return;
        }

        // Surname length must be between 2 and 20
        if(surname.length() < 2 || surname.length() > 20){
            // Generic error
            response.sendRedirect("register.jsp?error=1");
            return;
        }

        // Email validation
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            // Generic error
            response.sendRedirect("register.jsp?error=1");
            return;
        }

        // Email must match with its repetition
        if(!email.equals(emailRep)){
            // Emails are different
            response.sendRedirect("register.jsp?error=2");
            return;
        }

        // Password must be strong enough
        Strength strength = new Zxcvbn().measure(password);
        if(strength.getScore() < 2){
            // Password is not strong enough
            response.sendRedirect("register.jsp?error=6");
            return;
        }

        // Password must match with its repetition
        if(!password.equals(passwordRep)){
            // Passwords are different
            response.sendRedirect("register.jsp?error=3");
            return;
        }

        // Terms of service must be accepted
        if(terms == null || !terms.equals("yes")){
            // You must accept the terms of service
            response.sendRedirect("register.jsp?error=4");
            return;
        }

        // Register in DB or error if already present
        String token = dbmanager.register(name, surname, email, password);
        if(token == null){
            // This email has already been registered
            response.sendRedirect("register.jsp?error=5");
            return;
        }

        // Send confirmation email
        if(new ConfirmationMail(email, token).send()){
            // Registration successful. Check email
            response.sendRedirect("index.jsp?message=2");
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
        return "Manages register process";
    }

}
