package it.progettoweb.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * User Class
 * Class which collects all user's infos
 * @author Luca, Riccardo, Mario
 */
public class User implements Serializable{
    
    private int userType;       // 0 -> anonymous, 1 -> standard, 2 -> owner, 3 -> administrator
    private String name;        // User's name
    private String surname;     // User's surname
    private String email;       // User's email
    private ArrayList<Notification> notifications;  // List of notifications
    private ArrayList<RestaurantDropdown> ownedRestaurants;  // List of the owned restaurants

    public User(){
    }

    /**
     * @return the userType
     */
    public int getUserType() {
        return userType;
    }

    /**
     * @param userType the userType to set
     */
    public void setUserType(int userType) {
        this.userType = userType;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the notifications
     */
    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    /**
     * @param notifications the email to set
     */
    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    /**
     * @return the owned restaurants
     */
    public ArrayList<RestaurantDropdown> getOwnedRestaurants() {
        return ownedRestaurants;
    }

    /**
     * @param ownedRestaurants the owned restaurants to set
     */
    public void setOwnedRestaurants(ArrayList<RestaurantDropdown> ownedRestaurants) {
        this.ownedRestaurants = ownedRestaurants;
    }
}
