/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoweb.data;


/**
 *
 * @author Luca
 */
public class Notification{

    private int id;
    private String text;
    private int type;
    private String restaurantClaimer;
    private int restaurantClaimed;
    private String restaurantOwner;
    private int review;
    private int pictureNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRestaurantClaimer() {
        return restaurantClaimer;
    }

    public void setRestaurantClaimer(String restaurantClaimer) {
        this.restaurantClaimer = restaurantClaimer;
    }

    public int getRestaurantClaimed() {
        return restaurantClaimed;
    }

    public void setRestaurantClaimed(int restaurantClaimed) {
        this.restaurantClaimed = restaurantClaimed;
    }

    public String getRestaurantOwner() {
        return restaurantOwner;
    }

    public void setRestaurantOwner(String restaurantOwner) {
        this.restaurantOwner = restaurantOwner;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getPictureNumber() {
        return pictureNumber;
    }

    public void setPictureNumber(int pictureNumber) {
        this.pictureNumber = pictureNumber;
    }
}
