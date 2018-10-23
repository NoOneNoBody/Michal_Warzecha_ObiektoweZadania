import java.util.Date;
import java.util.LinkedList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailMessage {
    private String from; //required (must be e-mail)
    private LinkedList<String> to; //required at least one (must be e-mail)
    private String host; //optional
    private String subject; //optional
    private String content; //optional
    private String mimeType; // optional
    private LinkedList<String> cc; //optional
    private LinkedList<String> bcc; // optional

    protected EmailMessage(){
        host = "localhost";
        subject = "";
        content = "";
        mimeType = "text/html;charset=UTF-8";
        to = new LinkedList<String>();
        cc = new LinkedList<String>();
        bcc = new LinkedList<String>();
    }

    public void sendMessage(String password){
        System.out.println("\"" + from + "\" sends email to \"" + to.get(0) + "\" subject is: \"" + subject + "\"");

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.ssl.trust", host);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.debug", "true");
        properties.put("mail.store.protocol", "pop3");
        properties.put("mail.transport.protocol", "smtp");

        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", password);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            for(String toAddress : to) {
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
            }

            for(String ccAddress : cc) {
                message.setRecipient(Message.RecipientType.CC, new InternetAddress(ccAddress));
            }

            for(String bccAddress : bcc) {
                message.setRecipient(Message.RecipientType.BCC, new InternetAddress(bccAddress));
            }

            // Set Subject: header field
            message.setSubject(subject, "UTF-8");

            // Now set the actual message
            message.setContent(content, mimeType);

            message.setSentDate(new Date());

            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            System.out.println("Transport: "+transport.toString());
            transport.sendMessage(message, message.getAllRecipients());

            System.out.println("Sent message successfully....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private EmailMessage emailMessage = new EmailMessage();

        private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        private static boolean isValidAddress(String email) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
            return matcher.find();
        }

        public Builder addFrom(String from) throws Exception{
            if(isValidAddress(from))
                emailMessage.from = from;
            else
                throw new Exception("\"From\" email is not valid");
            return this;
        }

        public Builder addTo(String to){
            emailMessage.to.add(to);
            return this;
        }

        public Builder addHost(String host){
            emailMessage.host = host;
            return this;
        }

        public Builder addSubject(String subject){
            emailMessage.subject = subject;
            return  this;
        }

        public Builder addContent(String content){
            emailMessage.content = content;
            return  this;
        }

        public Builder addMimeType(String mimeType){
            emailMessage.mimeType = mimeType;
            return  this;
        }

        public Builder addCC(String cc) throws  Exception{
                emailMessage.cc.add(cc);
            return  this;
        }

        public Builder addBCC(String bcc) throws  Exception{
                emailMessage.bcc.add(bcc);
            return  this;
        }

        public EmailMessage build() throws Exception{
            if(emailMessage.from == null)
                throw new Exception("\"From\" address is null");
            if(emailMessage.to.isEmpty())
                throw new Exception("\"To\" addresses are empty");
            return emailMessage;
        }
    }
}
