package it.progettoweb.data;

/**
 * User Class
 * Class which collects all user's infos
 * @author Luca, Riccardo, Mario
 */
public class ConfirmationMail extends Mail{

    public ConfirmationMail(String to, String token){
        this.to = to;
        this.message = "Questo è il link bastardo: \n localhost:8080/AccountConfirm?user=" + to + "&token=" + token;
    }

}
