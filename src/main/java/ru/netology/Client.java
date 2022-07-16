package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "netology.homework";
        int port = 8888;
        Scanner scanner = new Scanner(System.in);
        try (Socket clientSocket = new Socket(host, port)) {
            System.out.println("Установлено соединение с сервером "
                    + clientSocket.getInetAddress().getHostName() + " - "
                    + clientSocket.getInetAddress().getHostAddress() + ":"
                    + clientSocket.getPort());
            try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                System.out.println(in.readLine()); // "write your name?"
                out.println(scanner.nextLine()); // отправить ответ на сервер
                System.out.println(in.readLine()); // "Are you child? (yes/no)"
                out.println(scanner.nextLine()); // отправить ответ на сервер
                System.out.println(in.readLine()); // получить ответ от сервера
            }
        } catch (ConnectException e) {
            System.out.println("Отказано в соединении");
        } catch (UnknownHostException e) {
            System.out.println("Неизвестный адрес для подключения");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
