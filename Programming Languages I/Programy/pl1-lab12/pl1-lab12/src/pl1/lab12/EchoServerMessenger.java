package pl1.lab12;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerMessenger {

    private static final int PORT = 11111;
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ObjectOutputStream out = null;
        ObjectInputStream in = null;

        // start listening on port
        ServerSocket server = new ServerSocket(PORT);

        System.out.println("ServerSocket awaiting connections...");
        Socket client = server.accept();
        System.out.println("Connection from " + client + "!");

        out = new ObjectOutputStream(client.getOutputStream());
        in = new ObjectInputStream(client.getInputStream());

        MessageDTO msg = (MessageDTO) in.readObject();

        // print out the text of every message
        System.out.println(msg.toString());

        System.out.println("Closing sockets.");
        in.close();
        out.close();
        client.close();
        server.close();
    }
}
