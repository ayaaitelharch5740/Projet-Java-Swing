package entities;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 *
 * @author ORIGINAL SHOP
 */
public class EmailUtil {
    
    private static final String FROM_EMAIL = "aitelharchaya12@gmail.com";
    private static final String PASSWORD = "jwepijfhedcyllpc";
    
    public static void sendEmail(String toEmail, String subject, String body) throws MessagingException {
        Properties props = new Properties();
        
        // Configuration SMTP de base
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        // Timeouts pour accélérer et éviter les blocages
        props.put("mail.smtp.connectiontimeout", "5000"); // 5 secondes pour la connexion
        props.put("mail.smtp.timeout", "5000");           // 5 secondes pour la lecture
        props.put("mail.smtp.writetimeout", "5000");      // 5 secondes pour l'écriture
        
        // Propriétés SSL/TLS
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
            }
        });
        
        // Activer le debug si nécessaire (à commenter en production)
        // session.setDebug(true);
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);
            
            // Envoyer l'email
            Transport.send(message);
            System.out.println("Email envoyé avec succès à : " + toEmail);
            
        } catch (MessagingException e) {
            System.err.println("Erreur lors de l'envoi de l'email : " + e.getMessage());
            throw e; // Relancer l'exception pour la gérer dans ForgotPassword
        }
    }
    
    /**
     * Version asynchrone pour ne pas bloquer l'interface
     */
    public static void sendEmailAsync(String toEmail, String subject, String body, EmailCallback callback) {
        new Thread(() -> {
            try {
                sendEmail(toEmail, subject, body);
                if (callback != null) {
                    callback.onSuccess();
                }
            } catch (MessagingException e) {
                if (callback != null) {
                    callback.onError(e);
                }
            }
        }).start();
    }
    
    /**
     * Interface pour les callbacks asynchrones
     */
    public interface EmailCallback {
        void onSuccess();
        void onError(Exception e);
    }
}