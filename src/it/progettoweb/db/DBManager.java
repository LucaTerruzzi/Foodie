/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoweb.db;

import it.progettoweb.data.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Luca
 */
public class DBManager {
    
    private String[] cuisine = {"bigola","italiana","francese","internazionale","tedesca","giapponese","indiana"};  //<-- TO BE DELETED
    private String[] cities = {"monza","milano","carnate","trento","caspoggio","roma","firenze"};  //<-- TO BE DELETED
    private String[] regions = {"lombardia","trentino","veneto","toscana","piemonte","puglia","emilia romagna"};  //<-- TO BE DELETED
    private String[] states = {"italia","francia","spagna","germania","inghilterra","austria","mongolia"};  //<-- TO BE DELETED
    private String[] restaurants = {"da gino","bigoli","mensa","cracco pec","forst","orso gino","da gianfranco","bigazzero","bigiggia","italia mia"};  //<-- TO BE DELETED

    private Connection connection;
    
    public DBManager(String dburl) throws SQLException {

        try {

           //Class.forName("org.apache.derby.jdbc.EmbeddedDriver", true, getClass().getClassLoader());
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

        } catch(Exception e) {
            throw new RuntimeException(e.toString(), e);
        }
        
        connection = DriverManager.getConnection(dburl);

    }

    public static void shutdown() {
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public User authenticate(String email, String password) {

        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM APP.\"USER\" WHERE EMAIL = ?")) {
            //stm = connection.prepareStatement("SELECT * FROM APP.USER WHERE EMAIL = ? AND PASSWORD = ?");
            stm.setString(1, email);
            //stm.setString(2, password);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    if ((rs.getInt("ACTIVE") != 0) && BCrypt.checkpw(password, rs.getString("PASSWORD"))){
                        User user = new User();
                        user.setName(rs.getString("NAME"));
                        user.setSurname(rs.getString("SURNAME"));
                        user.setEmail(rs.getString("EMAIL"));
                        user.setUserType(rs.getInt("TYPE"));
                        return user;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String register(String name, String surname, String email, String password){
        if(!uniqueMail(email)){
            return null;
        }

        String token;
        try (PreparedStatement stm = connection.prepareStatement("INSERT INTO APP.\"USER\" (EMAIL, NAME, SURNAME, PASSWORD, MAILTOKEN) VALUES (?, ?, ?, ?, ?)")) {
            stm.setString(1, email);
            stm.setString(2, name);
            stm.setString(3, surname);
            stm.setString(4, BCrypt.hashpw(password, BCrypt.gensalt()));
            token = UUID.randomUUID().toString();
            stm.setString(5, token);

            stm.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return token;
    }

    public boolean enableAccount(String email, String token){
        try (PreparedStatement stm = connection.prepareStatement("UPDATE APP.\"USER\" SET ACTIVE = 1, MAILTOKEN = 'ALREADY_USED' WHERE EMAIL = ? AND MAILTOKEN = ?")) {
            stm.setString(1, email);
            stm.setString(2, token);
            //stm.setString(2, password);
            if(stm.executeUpdate() == 0){
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String enableRecovery(String email){

        String token;
        try (PreparedStatement stm = connection.prepareStatement("UPDATE APP.\"USER\" SET MAILTOKEN = ? WHERE EMAIL = ? AND ACTIVE = 1")) {
            token = UUID.randomUUID().toString();
            stm.setString(1, token);
            stm.setString(2, email);
            if(stm.executeUpdate() == 0){
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return token;
    }

    public boolean changePasswordRecovery(String email, String token, String password){
        try (PreparedStatement stm = connection.prepareStatement("UPDATE APP.\"USER\" SET PASSWORD = ?, MAILTOKEN = 'ALREADY_USED' WHERE EMAIL = ? AND MAILTOKEN = ?")) {
            stm.setString(1, BCrypt.hashpw(password, BCrypt.gensalt()));
            stm.setString(2, email);
            stm.setString(3, token);
            //stm.setString(2, password);
            if(stm.executeUpdate() == 0){
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //RITORNA USER WHYYYYYYYYY <---------
    public User changeName(String name, String email){
        try (PreparedStatement stm = connection.prepareStatement("UPDATE APP.\"USER\" SET NAME = ? WHERE EMAIL = ?")) {
            stm.setString(1, name);
            stm.setString(2, email);

            if(stm.executeUpdate() == 0){
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM APP.\"USER\" WHERE EMAIL = ?")) {
            stm.setString(1, email);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setName(rs.getString("NAME"));
                    user.setSurname(rs.getString("SURNAME"));
                    user.setEmail(rs.getString("EMAIL"));
                    user.setUserType(rs.getInt("TYPE"));
                    return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User changeSurname(String surname, String email){
        try (PreparedStatement stm = connection.prepareStatement("UPDATE APP.\"USER\" SET SURNAME = ? WHERE EMAIL = ?")) {
            stm.setString(1, surname);
            stm.setString(2, email);

            if(stm.executeUpdate() == 0){
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM APP.\"USER\" WHERE EMAIL = ?")) {
            stm.setString(1, email);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setName(rs.getString("NAME"));
                    user.setSurname(rs.getString("SURNAME"));
                    user.setEmail(rs.getString("EMAIL"));
                    user.setUserType(rs.getInt("TYPE"));
                    return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean changePassword(String password, String passwordOld, String email){
        if(authenticate(email, passwordOld) == null){
            return false;
        }
        try (PreparedStatement stm = connection.prepareStatement("UPDATE APP.\"USER\" SET PASSWORD = ? WHERE EMAIL = ?")) {
            stm.setString(1, BCrypt.hashpw(password, BCrypt.gensalt()));
            stm.setString(2, email);

            if(stm.executeUpdate() == 0){
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean uniqueMail(String email){
        try (PreparedStatement stm = connection.prepareStatement("SELECT EMAIL FROM APP.\"USER\" WHERE EMAIL = ?")) {
            stm.setString(1, email);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean saveReview(Review review){
        try (PreparedStatement stm = connection.prepareStatement("INSERT INTO APP.REVIEW (TEXT, RATING, \"DATE\", RESTAURANT, AUTHOR, TITLE) VALUES (?, ?, ?, ?, ?, ?)")) {
            stm.setString(1, review.getBody());
            stm.setFloat(2, review.getRating());
            stm.setDate(3, new java.sql.Date(review.getDate().getTime()));
            stm.setInt(4,review.getRestaurant());
            stm.setString(5, review.getAuthor());
            stm.setString(6, review.getTitle());

            stm.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private ArrayList<Review> getReviews(int resId){
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM APP.REVIEW JOIN APP.\"USER\" ON APP.REVIEW.AUTHOR = APP.\"USER\".EMAIL  WHERE RESTAURANT = ?")) {
            stm.setInt(1, resId);
            try (ResultSet rs = stm.executeQuery()) {
                ArrayList<Review> reviews = new ArrayList<>();
                while (rs.next()) {
                    Review review = new Review();
                    review.setId(rs.getInt("ID"));
                    review.setTitle(rs.getString("TILE"));
                    review.setBody(rs.getString("BODY"));
                    review.setDate(rs.getDate("DATE"));
                    review.setRating(rs.getFloat("RATING"));
                    review.setAuthor(rs.getString("NAME") + ", " + rs.getString("SURNAME"));
                    review.setRestaurant(rs.getInt("RESTAURANT"));
                }

                return reviews;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public Restaurant getRestaurantById(int id){
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM APP.RESTAURANT JOIN APP.RESTAURANTCUISINE ON APP.RESTAURANT.ID = APP.RESTAURANTCUISINE.IDRES WHERE ID = ?")) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                Restaurant restaurant = new Restaurant();
                ArrayList<String> cuisines = new ArrayList<>();
                if (rs.next()) {
                    restaurant.setId(rs.getInt("ID"));
                    restaurant.setName(rs.getString("NAME"));
                    restaurant.setDescription(rs.getString("DESCRIPTION"));
                    restaurant.setLink(rs.getString("LINK"));
                    restaurant.setOpeningHours(rs.getString("TIMETABLE"));
                    restaurant.setPriceRange(rs.getInt("PRICE"));
                    restaurant.setRating(rs.getFloat("RATING"));
                    restaurant.setOwner(rs.getString("OWNER"));
                    Location location = new Location();
                    location.setLatitude(rs.getFloat("LATITUDE"));
                    location.setLongitude(rs.getFloat("LONGITUDE"));
                    location.setState(rs.getString("STATE"));
                    location.setRegion(rs.getString("REGION"));
                    location.setProvince(rs.getString("PROVINCE"));
                    location.setCity(rs.getString("CITY"));
                    location.setAddress(rs.getString("ADDRESS"));
                    restaurant.setLocation(location);
                    cuisines.add(rs.getString("CUISINETYPE"));
                }

                while (rs.next()){
                    cuisines.add(rs.getString("CUISINETYPE"));
                }

                restaurant.setCuisine(cuisines);
                return restaurant;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public ArrayList<SearchResult> getRestaurants(String term){
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM APP.RESTAURANT JOIN APP.RESTAURANTCUISINE ON APP.RESTAURANT.ID = APP.RESTAURANTCUISINE.IDRES WHERE LOWER(NAME) LIKE ? ORDER BY APP.RESTAURANT.NAME, APP.RESTAURANT.ID")) {
            stm.setString(1, '%' + term.toLowerCase() + '%');
            try (ResultSet rs = stm.executeQuery()) {
                ArrayList<SearchResult> results = new ArrayList<>();
                int curid = -1;
                while (rs.next()){
                    if(rs.getInt("ID") == curid){
                        results.get(results.size() - 1).getCuisine().add(rs.getString("CUISINETYPE"));
                    }else{
                        ArrayList<String> cuisines = new ArrayList<>();
                        SearchResult result = new SearchResult();
                        curid = rs.getInt("ID");
                        result.setId(rs.getInt("ID"));
                        result.setName(rs.getString("NAME"));
                        result.setRange(rs.getInt("PRICE"));
                        result.setRating(rs.getFloat("RATING"));
                        result.setLatitude(rs.getFloat("LATITUDE"));
                        result.setLongitude(rs.getFloat("LONGITUDE"));
                        result.setState(rs.getString("STATE"));
                        result.setCity(rs.getString("CITY"));
                        cuisines.add(rs.getString("CUISINETYPE"));
                        result.setCuisine(cuisines);
                        results.add(result);
                    }
                }

                return results;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public ArrayList<SearchResult> getRestaurantsByRegion(String term){
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM APP.RESTAURANT JOIN APP.RESTAURANTCUISINE ON APP.RESTAURANT.ID = APP.RESTAURANTCUISINE.IDRES WHERE LOWER(REGION) LIKE ? ORDER BY APP.RESTAURANT.RATING DESC, APP.RESTAURANT.ID")) {
            stm.setString(1, '%' + term.toLowerCase() + '%');
            try (ResultSet rs = stm.executeQuery()) {
                ArrayList<SearchResult> results = new ArrayList<>();
                int curid = -1;
                int rank = 1;
                while (rs.next()){
                    if(rs.getInt("ID") == curid){
                        results.get(results.size() - 1).getCuisine().add(rs.getString("CUISINETYPE"));
                    }else{
                        ArrayList<String> cuisines = new ArrayList<>();
                        SearchResult result = new SearchResult();
                        curid = rs.getInt("ID");
                        result.setId(rs.getInt("ID"));
                        result.setName(rs.getString("NAME"));
                        result.setRange(rs.getInt("PRICE"));
                        result.setRating(rs.getFloat("RATING"));
                        result.setLatitude(rs.getFloat("LATITUDE"));
                        result.setLongitude(rs.getFloat("LONGITUDE"));
                        result.setState(rs.getString("STATE"));
                        result.setCity(rs.getString("CITY"));
                        result.setRank(rank);
                        cuisines.add(rs.getString("CUISINETYPE"));
                        result.setCuisine(cuisines);
                        results.add(result);
                        rank++;
                    }
                }

                return results;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public ArrayList<SearchResult> getRestaurantsByCity(String term){
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM APP.RESTAURANT JOIN APP.RESTAURANTCUISINE ON APP.RESTAURANT.ID = APP.RESTAURANTCUISINE.IDRES WHERE LOWER(CITY) LIKE ? ORDER BY APP.RESTAURANT.RATING DESC, APP.RESTAURANT.ID")) {
            stm.setString(1, '%' + term.toLowerCase() + '%');
            try (ResultSet rs = stm.executeQuery()) {
                ArrayList<SearchResult> results = new ArrayList<>();
                int curid = -1;
                int rank = 1;
                while (rs.next()){
                    if(rs.getInt("ID") == curid){
                        results.get(results.size() - 1).getCuisine().add(rs.getString("CUISINETYPE"));
                    }else{
                        ArrayList<String> cuisines = new ArrayList<>();
                        SearchResult result = new SearchResult();
                        curid = rs.getInt("ID");
                        result.setId(rs.getInt("ID"));
                        result.setName(rs.getString("NAME"));
                        result.setRange(rs.getInt("PRICE"));
                        result.setRating(rs.getFloat("RATING"));
                        result.setLatitude(rs.getFloat("LATITUDE"));
                        result.setLongitude(rs.getFloat("LONGITUDE"));
                        result.setState(rs.getString("STATE"));
                        result.setCity(rs.getString("CITY"));
                        result.setRank(rank);
                        cuisines.add(rs.getString("CUISINETYPE"));
                        result.setCuisine(cuisines);
                        results.add(result);
                        rank++;
                    }
                }

                return results;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public ArrayList<AutocompleteElement> getCuisineSuggestion(String term) {
        ArrayList<AutocompleteElement> result = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement("SELECT TYPE FROM APP.CUISINETYPE WHERE LOWER(TYPE) LIKE ?")) {
            stm.setString(1, '%'+term.toLowerCase()+'%');
            try (ResultSet rs = stm.executeQuery()) {
                int i = 0;
                while (rs.next() && i < 2) {
                    AutocompleteElement elem = new AutocompleteElement();
                    elem.setValue(rs.getString("TYPE"));
                    result.add(elem);
                    i++;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public ArrayList<AutocompleteElement> getPlaceSuggestion(String term){
        ArrayList<AutocompleteElement> result = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement("SELECT DISTINCT CITY, REGION FROM APP.RESTAURANT WHERE LOWER(CITY) LIKE ?")) {
            stm.setString(1, '%'+term.toLowerCase()+'%');
            try (ResultSet rs = stm.executeQuery()) {
                int i = 0;
                while (rs.next() && i < 2) {
                    AutocompleteElement elem = new AutocompleteElement();
                    elem.setValue(rs.getString("CITY"));
                    elem.setSpec(rs.getString("REGION"));
                    elem.setId(-1);
                    result.add(elem);
                    i++;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement stm = connection.prepareStatement("SELECT DISTINCT REGION, STATE FROM APP.RESTAURANT WHERE LOWER(REGION) LIKE ?")) {
            stm.setString(1, '%'+term.toLowerCase()+'%');
            try (ResultSet rs = stm.executeQuery()) {
                int i = 0;
                while (rs.next() && i < 2) {
                    AutocompleteElement elem = new AutocompleteElement();
                    elem.setValue(rs.getString("REGION"));
                    elem.setSpec(rs.getString("STATE"));
                    elem.setId(-2);
                    result.add(elem);
                    i++;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public ArrayList<AutocompleteElement> getRestaurantSuggestion(String term){
        ArrayList<AutocompleteElement> result = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement("SELECT DISTINCT ID, NAME, CITY, REGION FROM APP.RESTAURANT WHERE LOWER(NAME) LIKE ?")) {
            stm.setString(1, '%'+term.toLowerCase()+'%');
            try (ResultSet rs = stm.executeQuery()) {
                int i = 0;
                while (rs.next() && i < 5) {
                    AutocompleteElement elem = new AutocompleteElement();
                    elem.setValue(rs.getString("NAME"));
                    elem.setSpec(rs.getString("CITY") + ", " + rs.getString("REGION"));
                    elem.setId(rs.getInt("ID"));
                    result.add(elem);
                    i++;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
