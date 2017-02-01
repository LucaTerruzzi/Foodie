package it.progettoweb.data;

/**
 * Class which handles recovery mail
 * @author Luca, Riccardo, Mario
 */
public class RecoveryMail extends Mail{

    /**
     * Handles the confirmation mail
     *
     * @param to recipient
     * @param token token sent to recover mail
     */
    public RecoveryMail(String to, String token){
        this.to = to;
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head><title></title></head>");
        sb.append("<body>");
        sb.append("<h3>Password recovery</h3>");
        sb.append("<p>Dear user, here's the link to recover your password.</p>");
        sb.append("<a href='http://localhost:8080/pwdChange.jsp?user=");
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