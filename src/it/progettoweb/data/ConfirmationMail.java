package it.progettoweb.data;

/**
 * User Class
 * Class which collects all user's infos
 * @author Luca, Riccardo, Mario
 */
public class ConfirmationMail extends Mail{

    public ConfirmationMail(String to, String token){
        this.to = to;
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head><title></title></head>");
        sb.append("<body>");
        sb.append("<h3>Account confirmation</h3>");
        sb.append("<p>Dear user, here's the link to confirm your account.</p>");
        sb.append("<a href='localhost:8080/AccountConfirm?user=");
        sb.append(to);
        sb.append("&token=");
        sb.append(token);
        sb.append("'>Click here!</a>");
        sb.append("<h5>Thanks for using Foodie</h5>");
        sb.append("</body>");
        sb.append("</html>");
        this.message = sb.toString();
    }

}
