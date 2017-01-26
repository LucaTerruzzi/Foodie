package it.progettoweb.data;

import java.util.ArrayList;

/**
 * Class which stores restaurant data
 * @author Luca, Riccardo, Mario
 */
public class SearchResult {
    private int id;
    private String name;
    private float latitude;
    private float longitude;
    private String city;
    private String state;
    private float rating;
    private ArrayList<String> cuisine;
    private int nrev;
    private int rank;
    private int range;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public ArrayList<String> getCuisine() {
        return cuisine;
    }

    public void setCuisine(ArrayList<String> cuisine) {
        this.cuisine = cuisine;
    }

    public int getNrev() {
        return nrev;
    }

    public void setNrev(int nrev) {
        this.nrev = nrev;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}

