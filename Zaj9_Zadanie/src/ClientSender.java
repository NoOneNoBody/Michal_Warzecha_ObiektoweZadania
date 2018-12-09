import java.io.*;
import java.net.*;
public class ClientSender{

    Socket socket;
    ObjectOutputStream out;

    public void connect(){
        try {
            socket = new Socket("localhost", 6666);
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: localhost.");
            System.exit(1);
        }
    }

    public void sendObject(Object object){
        try {
            out.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try {
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
