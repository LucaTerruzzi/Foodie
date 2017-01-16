/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoweb.db;

import it.progettoweb.data.AutocompleteElement;
import it.progettoweb.data.User;
import java.sql.SQLException;
import java.util.ArrayList;

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

    //private transient Connection connection;
    
    public DBManager(String dburl) throws SQLException {

        /*try {

            Class.forName("org.apache.derby.jdbc.ClientDriver", true, getClass().getClassLoader());

        } catch(Exception e) {
            throw new RuntimeException(e.toString(), e);
        }
        
        Connection con = DriverManager.getConnection(dburl);
        this.con = con;*/

    }

    public static void shutdown() {
        /*
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).info(ex.getMessage());
        }*/
    }
    
    public User authenticate(String username, String password){
        if("barzo".equals(username) && "password".equals(password)){
            User user = new User();
            user.setName("Tuca");
            user.setSurname("Lerruzzi");
            user.setEmail("luca.terruzzi@studenti.unitn.it");
            user.setUsername("RbaRzo");
            user.setUserType(1);
            return user;
        }else{
            return null;
        }
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
