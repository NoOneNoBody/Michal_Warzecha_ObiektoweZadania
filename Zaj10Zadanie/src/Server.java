import java.io.*;
import java.net.*;

public class Server {

    public static final int MAX_THREAD_COUNT = 20;

    public ClientConnection[] clientConnections = new ClientConnection[MAX_THREAD_COUNT];

    public void sendToAllClients(int current, Object object) throws IOException {
        for(int i=0; i<MAX_THREAD_COUNT; ++i){
            if(i == current) continue;
            if(clientConnections[i] != null){
                clientConnections[i].sendObjectToClient(object);
            }
        }
    }

    public static void main(String[] args){
        Server server = new Server();

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(6666);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 6666");
            System.exit(-1);
        }
        try {
           for(int i=0; i<MAX_THREAD_COUNT; ++i){
               server.clientConnections[i] = new ClientConnection(server, serverSocket, i);
           }
        } catch (IOException e) {
            System.out.println("Accept failed: 6666");
            System.exit(-1);
        }
    }
}
