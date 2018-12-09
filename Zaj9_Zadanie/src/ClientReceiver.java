import java.io.*;
import java.net.*;
public class ClientReceiver {

    private Socket socket;
    private ObjectInputStream in;

    public void connect(){
        try {
            socket = new Socket("localhost", 6666);
            in = new ObjectInputStream(socket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: localhost.");
            System.exit(1);
        }
    }

    public void listenForObjects(ReceiveObjectListener listener){
        try {
            Object inputObject;
            while ((inputObject = in.readObject()) != null) {
                listener.onReceive(inputObject);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try {
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}