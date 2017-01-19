/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoweb.data;

import java.util.ArrayList;

/**
 * Class which stores restaurant data
 * @author Luca, Riccardo, Mario
 */
public class Restaurant{
    
    private int id;
    private String name;
    private String description;
    private String link;
    private ArrayList<String> cuisine;
    private Location location;
    private OpeningHours openingHours;
    private int priceRange;
    private int rating;

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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
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
     * @return the rank
     */
    public int getRating() {
        return rating;
    }

    /**
     * @param rank the rank to set
     */
    public void setRating(int rank) {
        this.rating = rank;
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return the price range
     */
    public int getPriceRange() {
        return priceRange;
    }

    /**
     * @param priceRange the price range to set
     */
    public void setPriceRange(int priceRange) {
        this.priceRange = priceRange;
    }

    /**
     *
     * @return the opening hours
     */
    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    /**
     *
     * @param openingHours the opening hours to set
     */
    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }
}
