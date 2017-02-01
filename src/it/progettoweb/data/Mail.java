package it.progettoweb.data;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * User Class
 * Class which sends mail
 * @author Luca, Riccardo, Mario
 */
public class Mail {

    protected String to;
    protected String message;


    public boolean send(){
        Properties props = System.getProperties();

        // Gmail
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Get a Session object
        Session session = Session.getInstance(props, null);

        // Create a new message
        MimeMessage msg = new MimeMessage(session);

        Transport t = null;
        try {
            t = session.getTransport("smtps");
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return false;
        }

        // Set the FROM and TO fields
        try {
            msg.setFrom(new InternetAddress("team.at.foodie@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to,false));
            msg.setSubject("Foodie team");
            msg.setText(message, "utf-8", "html");
            msg.setSentDate(new Date());
            t.connect("smtp.gmail.com", 465,"team.at.foodie@gmail.com", "scC-Tvt-Upt-X5C");
            t.sendMessage(msg, msg.getAllRecipients());

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                t.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

}

