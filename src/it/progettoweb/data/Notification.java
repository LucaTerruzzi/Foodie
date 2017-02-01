package it.progettoweb.data;


/**
 * Class which stores notification data
 * @author Luca, Riccardo, Mario
 */
public class Notification{

    // Notification id
    private int id;

    // Notification text
    private String text;

    // Notification type
    private int type;

    // User who claims the restaurant
    private String restaurantClaimer;

    // Restaurant which is being claimed
    private int restaurantClaimed;

    // Owner of the restaurant who has flagged the picture
    private String restaurantOwner;

    // Review relative to the picture flagged
    private int review;

    // Id of the picture flagged
    private int pictureNumber;

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
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the user who claims the restaurant
     */
    public String getRestaurantClaimer() {
        return restaurantClaimer;
    }

    /**
     * @param restaurantClaimer the id of the user who claims the restaurant to set
     */
    public void setRestaurantClaimer(String restaurantClaimer) {
        this.restaurantClaimer = restaurantClaimer;
    }

    /**
     * @return the restaurant which is being claimed
     */
    public int getRestaurantClaimed() {
        return restaurantClaimed;
    }

    /**
     * @param restaurantClaimed the id of the restaurant which is being claimed to set
     */
    public void setRestaurantClaimed(int restaurantClaimed) {
        this.restaurantClaimed = restaurantClaimed;
    }

    /**
     * @return the owner of the restaurant who has flagged the picture
     */
    public String getRestaurantOwner() {
        return restaurantOwner;
    }

    /**
     * @param restaurantOwner the id of the owner of the restaurant who has flagged the picture to set
     */
    public void setRestaurantOwner(String restaurantOwner) {
        this.restaurantOwner = restaurantOwner;
    }

    /**
     * @return the review relative to the picture flagged
     */
    public int getReview() {
        return review;
    }

    /**
     * @param review the id of the review relative to the picture flagged to set
     */
    public void setReview(int review) {
        this.review = review;
    }

    /**
     * @return the id of the picture flagged
     */
    public int getPictureNumber() {
        return pictureNumber;
    }

    /**
     * @param pictureNumber the id of the id of the picture flagged to set
     */
    public void setPictureNumber(int pictureNumber) {
        this.pictureNumber = pictureNumber;
    }
}
