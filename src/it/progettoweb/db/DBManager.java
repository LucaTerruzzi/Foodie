/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoweb.db;

import it.progettoweb.data.AutocompleteElement;
import it.progettoweb.data.User;

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



    public ArrayList<AutocompleteElement> getCuisineSuggestion(String term){
        ArrayList<AutocompleteElement> result = new ArrayList<>();
        for(String cur:cuisine){
            if(cur.contains(term)){
                AutocompleteElement elem = new AutocompleteElement();
                elem.setValue(cur);
                result.add(elem);
            }
        }
        return result;
    }
    
    public ArrayList<AutocompleteElement> getPlaceSuggestion(String term){
        ArrayList<AutocompleteElement> result = new ArrayList<>();
        for(String cur:cities){
            if(cur.contains(term)){
                AutocompleteElement elem = new AutocompleteElement();
                elem.setValue(cur);
                elem.setSpec1("Lombardia");
                elem.setSpec2("Italia");
                result.add(elem);
            }
        }
        for(String cur:regions){
            if(cur.contains(term)){
                AutocompleteElement elem = new AutocompleteElement();
                elem.setValue(cur);
                elem.setSpec1("Italia");
                result.add(elem);
            }
        }
        for(String cur:states){
            if(cur.contains(term)){
                AutocompleteElement elem = new AutocompleteElement();
                elem.setValue(cur);
                elem.setSpec1("Europa");
                result.add(elem);
            }
        }
        return result;
    }
    
    public ArrayList<AutocompleteElement> getRestaurantSuggestion(String term){
        ArrayList<AutocompleteElement> result = new ArrayList<>();
        for(String cur:restaurants){
            if(cur.contains(term)){
                AutocompleteElement elem = new AutocompleteElement();
                elem.setValue(cur);
                elem.setSpec1("Monza");
                elem.setSpec2("Lombardia");
                elem.setSpec3("Italia");
                result.add(elem);
            }
        }
        return result;
    }
}
