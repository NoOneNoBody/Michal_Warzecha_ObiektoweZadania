import java.io.*;
import java.net.*;
public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(6666);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 6666");
            System.exit(-1);
        }
        Socket clientSocketSender = null;
        Socket clientSocketReceiver = null;
        try {
            clientSocketSender = serverSocket.accept();
            clientSocketReceiver = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 6666");
            System.exit(-1);
        }
        ObjectOutputStream out = new ObjectOutputStream(clientSocketReceiver.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(clientSocketSender.getInputStream());
        Object inputObject;
        while ((inputObject = in.readObject()) != null) {
            out.writeObject(inputObject);
        }
        out.writeObject(null);
        out.close();
        in.close();
        clientSocketSender.close();
        clientSocketReceiver.close();
        serverSocket.close();
    }
}
