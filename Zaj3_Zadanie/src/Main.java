public class Main {

    public static void main(String[] argv){
        EmailMessage message;
        try {
            message = EmailMessage.builder()
                    .addFrom("usernameFrom@poczta.com")
                    .addTo("usernameTo@poczta.com")
                    .addHost("smtp.poczta.com")
                    .addSubject("Subject")
                    .addContent("Content")
                    .build();

            message.sendMessage("password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
