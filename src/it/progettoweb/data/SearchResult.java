package it.progettoweb.data;

import java.util.ArrayList;

/**
 * Class which stores search results
 * @author Luca, Riccardo, Mario
 */
public class SearchResult {

    // Restaurant id
    private int id;

    // Restaurant name
    private String name;

    // Restaurant latitude
    private float latitude;

    // Restaurant longitude
    private float longitude;

    // Restaurant city
    private String city;

    // Restaurant state
    private String state;

    // Restaurant rating
    private float rating;

    // Restaurant cuisine
    private ArrayList<String> cuisine;

    // Restaurant number of reviews
    private int nrev;

    // Restaurant ranking
    private int rank;

    // Restaurant price range
    private int range;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the latitude
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the rating
     */
    public float getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(float rating) {
        this.rating = rating;
    }

    /**
     * @return the cuisine
     */
    public ArrayList<String> getCuisine() {
        return cuisine;
    }

    /**
     * @param cuisine the cuisine to set
     */
    public void setCuisine(ArrayList<String> cuisine) {
        this.cuisine = cuisine;
    }

    /**
     * @return the number of reviews
     */
    public int getNrev() {
        return nrev;
    }

    /**
     * @param nrev the number of reviews to set
     */
    public void setNrev(int nrev) {
        this.nrev = nrev;
    }

    /**
     * @return the ranking
     */
    public int getRank() {
        return rank;
    }

    /**
     * @param rank the rank position to set
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * @return the price range
     */
    public int getRange() {
        return range;
    }

    /**
     * @param range the price range to set
     */
    public void setRange(int range) {
        this.range = range;
    }
}

