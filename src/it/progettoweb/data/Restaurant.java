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
    //private OpeningHours openingHours;
    private String openingHours;
    private int priceRange;
    private float rating;
    private ArrayList<Review> reviews;
    private String owner;

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
    public float getRating() {
        return rating;
    }

    /**
     * @param rank the rank to set
     */
    public void setRating(float rank) {
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
    public String getOpeningHours() {
        return openingHours;
    }

    /**
     *
     * @param openingHours the opening hours to set
     */
    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    /**
     *
     * @return the list of reviews
     */
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    /**
     *
     * @param reviews the list of reviews to set
     */
    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     *
     * @return the owner of the restaurant
     */
    public String getOwner() {
        return owner;
    }

    /**
     *
     * @param owner the owner of the restaurant to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }
}
