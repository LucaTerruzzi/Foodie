package it.progettoweb.services;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * To add the autocomplete service
 *
 * @author Luca, Riccardo, Mario
 */
@javax.ws.rs.ApplicationPath("service")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(it.progettoweb.services.autocompleteService.class);
    }
    
}
