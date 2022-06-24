package pl1.lab12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 11111;

    public static void main(String[] args) {

        try (
                // create socket to listen on <host> : <port>
                Socket echoSocket = new Socket(HOST, PORT);

                // create output stream to send information to server
                PrintWriter out =
                        new PrintWriter(echoSocket.getOutputStream(), true);

                // create output stream to send information to server
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(echoSocket.getInputStream()));

                // create input stream from keyboard
                BufferedReader stdIn =
                        new BufferedReader(
                                new InputStreamReader(System.in))
        ) {
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
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
