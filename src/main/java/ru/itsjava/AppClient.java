package ru.itsjava;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class AppClient {
    public final static String HOST = "localhost";
    public final static int PORT = 8081;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(HOST, PORT);

        if (socket.isConnected()){
            System.out.println("I'm connected");

            PrintWriter serverWriter = new PrintWriter(socket.getOutputStream());
            serverWriter.println("Privet from client");
            serverWriter.flush();
        }


    }
}
