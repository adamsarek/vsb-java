package pl1.lab12;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClientMessenger {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 11111;

    public static void main(String[] args) {

        System.out.println(HOST);

        try (
                // create socket to listen on <host> : <port>
                Socket echoSocket = new Socket(HOST, PORT);
                // get the output stream from the socket.
                OutputStream outputStream = echoSocket.getOutputStream();
                // create an object output stream from the output stream so we can send an object through it
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        ) {

            MessageDTO msg = new MessageDTO(5, "Echo Test");

            System.out.println(msg.toString());

            objectOutputStream.writeObject(msg);
            objectOutputStream.flush();

            System.out.println("SENT");

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + HOST);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    HOST);
            System.exit(1);
        }
    }
}
