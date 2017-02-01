package it.progettoweb.data;

import java.util.ArrayList;

/**
 * Class which stores restaurant data
 * @author Luca, Riccardo, Mario
 */
public class Restaurant{

    // Restaurant id
    private int id;

    // Restaurant name
    private String name;

    // Restaurant description
    private String description;

    // Restaurant web link
    private String link;

    // Restaurant cuisine
    private ArrayList<String> cuisine;

    // Restaurant location
    private Location location;

    // Restaurant opening hours
    private String openingHours;

    // Restaurant price range
    private int priceRange;

    // Restaurant rating
    private float rating;

    // Restaurant reviews
    private ArrayList<Review> reviews;

    // Restaurant owner
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
