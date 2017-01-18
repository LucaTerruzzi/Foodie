package it.progettoweb.data;

/**
 * User Class
 * Class which collects all user's infos
 * @author Luca, Riccardo, Mario
 */
public class RecoveryMail extends Mail{

    public RecoveryMail(String to, String token){
        this.to = to;
        this.message = "Questo è il link bastardo: \n localhost:8080/pwdChange.jsp?user=" + to + "&token=" + token;
    }

}