package it.progettoweb.logic;

import it.progettoweb.data.Restaurant;
import it.progettoweb.data.User;
import it.progettoweb.db.DBManager;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
// QRGen to crate QR codes
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 * Class used to retrieve restaurants
 * @author Luca, Riccardo, Mario
 */
public class RetrieveRestaurant extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Restaurant id
        int id;

        // Parsing to integer
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e){
            response.sendRedirect("index.jsp");
            return;
        }

        // Gets the restaurant from the db
        Restaurant restaurant = dbmanager.getRestaurantById(id);

        // 1 if the user can review the restaurant, 0 otherwise
        int canreview = 0;

        // Checks if the user should be able to review the restaurant
        if(restaurant == null){
            response.sendRedirect("index.jsp");
        }else{
            HttpSession session = request.getSession();
            if((int)session.getAttribute("userType") != 0) {
                // Last user review of this restaurant must be at least 1 day old
                LocalDate lastDate = dbmanager.lastReviewDate(id, ((User)session.getAttribute("user")).getEmail());
                if (lastDate != null && lastDate.isBefore(LocalDate.now())) {
                    canreview = 1;
                }
            }

            // Generates the qr if it hasn't already been generated
            placeQR(restaurant);

            request.setAttribute("restaurant", restaurant);
            request.setAttribute("canreview", canreview);
            getServletContext().getRequestDispatcher("/restaurant.jsp").forward(request, response);
        }
    }


    /**
     * Generates the qr if it hasn't already been generated
     *
     * @param restaurant restaurant to check
     * @throws IOException if an I/O error occurs
     */
    private void placeQR(Restaurant restaurant) throws IOException{

        // Relative url of the picture folder
        URL qrUrl = getServletContext().getResource("/pics/" + restaurant.getId());

        // Creation of the qr file object in the given folder
        File qrFile = new File(qrUrl.getPath() + "/" + restaurant.getId() + "_qr.jpg");

        // If the qr hasn't already been generated, generate it
        if(!qrFile.isFile()){
            StringBuilder text = new StringBuilder();
            text.append(restaurant.getName());
            text.append("\n");
            text.append(restaurant.getLocation().getAddress()).append(", ").append(restaurant.getLocation().getCity());
            text.append(", ").append(restaurant.getLocation().getState());
            text.append("\n");
            text.append(restaurant.getOpeningHours());
            ByteArrayOutputStream qrStream = QRCode.from(text.toString()).to(ImageType.JPG).withSize(256, 256).stream();
            try(OutputStream outputStream = new FileOutputStream(qrFile)) {
                qrStream.writeTo(outputStream);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Retrieve restaurant";
    }

}