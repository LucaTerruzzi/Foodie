package it.progettoweb.logic;

import it.progettoweb.data.User;
import it.progettoweb.db.DBManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
// Password strength libraries
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

/**
 * Servlet which manages the edit of the user's profile
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
        String name, surname, passwordOld, password, passwordRep;

        // Retrieve parameters values
        name = request.getParameter("name");
        surname = request.getParameter("surname");
        passwordOld = request.getParameter("password-old");
        password = request.getParameter("password");
        passwordRep = request.getParameter("password-rep");

        // Get user email
        HttpSession session = request.getSession();
        String email = ((User)session.getAttribute("user")).getEmail();

        // Name, Surname or Password
        String path = request.getPathInfo();

        switch (path){
            // Updates the name
            case "/name":
                if(changeName(name, email, session)){
                    response.sendRedirect("/user.jsp");
                    return;
                }else{
                    // Invalid name
                    response.sendRedirect("/user.jsp?error=1");
                    return;
                }

            // Updates the surname
            case "/surname":
                if(changeSurname(surname, email, session)){
                    response.sendRedirect("/user.jsp");
                    return;
                }else{
                    // Invalid surname
                    response.sendRedirect("/user.jsp?error=2");
                    return;
                }

            // Updates the password
            case "/pwd":
                if(changePassword(passwordOld, password, passwordRep, email)){
                    // Password changed successfully
                    response.sendRedirect("/user.jsp?message=1");
                    return;
                }else{
                    // Check password
                    response.sendRedirect("/user.jsp?error=3");
                    return;
                }

            default:
                response.sendRedirect("/index.jsp");

        }

    }


    private boolean changeName(String name, String email, HttpSession session){

        // If the name does not exist
        if(name == null){
            return false;
        }

        // Name length must be between 2 and 20
        if(name.length() < 2 || name.length() > 20){
            return false;
        }

        // Updates the name in the DB
        User mod = dbmanager.changeName(name, email);
        if(mod == null) {
            return false;
        }

        // Updates the user in the session
        session.setAttribute("user", mod);
        return true;
    }

    private boolean changeSurname(String surname, String email, HttpSession session){

        // If the surname does not exist
        if(surname == null){
            return false;
        }

        // Surame length must be between 2 and 20
        if(surname.length() < 2 || surname.length() > 20){
            return false;
        }

        // Updates the surname in the DB
        User mod = dbmanager.changeSurname(surname, email);
        if(mod == null) {
            return false;
        }

        // Updates the user in the session
        session.setAttribute("user", mod);
        return true;
    }

    private boolean changePassword(String passwordOld, String password, String passwordRep, String email){

        // If the password does not exist
        if(password == null || passwordOld == null || passwordRep == null){
            return false;
        }

        // Old password must't be empty
        if(passwordOld.length() < 1){
            return false;
        }

        // New password must be strong enough
        Strength strength = new Zxcvbn().measure(password);
        if(strength.getScore() < 2){
            // Password is not strong enough
            return false;
        }

        // New password must match its repetition
        if(!password.equals(passwordRep)){
            return false;
        }

        // Updates the password in the DB
        if(!dbmanager.changePassword(password, passwordOld, email)) {
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
        return "Manages the edit of the user's profile";
    }

}
