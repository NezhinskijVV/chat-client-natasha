package ru.itsjava.service;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientImpl implements Client {
    public final static String HOST = "localhost";
    public final static int PORT = 8081;

    @SneakyThrows
    @Override
    public void start() {
        Socket socket = new Socket(HOST, PORT);

        if (socket.isConnected()) {
            System.out.println("I'm connected");

            BufferedReader consoleReader
                    = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter serverWriter = new PrintWriter(socket.getOutputStream());

            new Thread(() -> {
                try {

                    BufferedReader serverReader =
                            new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String serverMessage;
                    while ((serverMessage = serverReader.readLine()) != null){
                        System.out.println(serverMessage);
                    }

                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }).start();


            while (true) {
                System.out.println("Введи сообщение");
                serverWriter.println(consoleReader.readLine());
                serverWriter.flush();
            }

        }
    }
}
