package it.progettoweb.services;

import com.google.gson.Gson;
import it.progettoweb.db.DBManager;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Luca, Riccardo, Mario
 */
@Path("autocomplete")
public class autocompleteService {

    @Context
    private ServletContext context;

    /**
     * Creates a new instance of autocompleteService
     */
    public autocompleteService() {
    }

    /**
     * Retrieves representation of an instance of it.progettoweb.services.autocompleteService
     * @return an instance of java.lang.String
     */
    @GET
    @Path("cuisine/{term}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCuisine(@PathParam("term")String term) {
        if(term.toLowerCase().contains("%") || term.toLowerCase().contains("[") || term.toLowerCase().contains("]") ||
                term.toLowerCase().contains("^")){
            return null;
        }
        Gson gson = new Gson();
        return gson.toJson(((DBManager)context.getAttribute("dbmanager")).getCuisineSuggestion(term));

    }
    
    @GET
    @Path("places/{term}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPlaces(@PathParam("term")String term) {
        if(term.toLowerCase().contains("%") || term.toLowerCase().contains("[") || term.toLowerCase().contains("]") ||
                term.toLowerCase().contains("^")){
            return null;
        }
        Gson gson = new Gson();
        return gson.toJson(((DBManager)context.getAttribute("dbmanager")).getPlaceSuggestion(term));
        
    }
    
    @GET
    @Path("restaurants/{term}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRestaurants(@PathParam("term")String term) {
        if(term.toLowerCase().contains("%") || term.toLowerCase().contains("[") || term.toLowerCase().contains("]") ||
                term.toLowerCase().contains("^")){
            return null;
        }
        Gson gson = new Gson();
        return gson.toJson(((DBManager)context.getAttribute("dbmanager")).getRestaurantSuggestion(term));
        
    }

}
