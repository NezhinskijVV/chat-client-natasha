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

            MessageInputService consoleReader
                    = new MessageInputServiceImpl(System.in);

            new Thread(new SocketRunnable(socket)).start();

            System.out.println("Введи логин");
            String login = consoleReader.getMessage();
            System.out.println("Введи пароль");
            String password = consoleReader.getMessage();

            PrintWriter serverWriter = new PrintWriter(socket.getOutputStream());
            serverWriter.println("!auth!" + login + ":" + password);
            serverWriter.flush();

            while (true) {
                serverWriter.println(consoleReader.getMessage());
                serverWriter.flush();
            }
        }
    }
}
//имя + успешно зарегистрирован
//InputService в отдельный класс
//доменная сущность пользователь (имя и пароль)
//!!Вопросы по прощедшему материалу!!
//+ prototype


//след. группа паттернов (структурные) *