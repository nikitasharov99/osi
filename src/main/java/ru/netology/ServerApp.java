package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    public static void main(String[] args) {
        int port = 8888;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен");
            try (Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                System.out.println("New connection accepted "
                        + clientSocket.getInetAddress().getHostName() + " - "
                        + clientSocket.getInetAddress().getHostAddress() + ":"
                        + clientSocket.getPort());
                out.println("Hi, dear. Write your name");
                final String name = in.readLine();
                out.println("Are you child? (yes/no)");
                String ans = in.readLine();
                if (ans.equals("yes")) {
                    out.printf("Welcome to the kids area, %s! " +
                            "Let's play!%n", name);
                } else if (ans.equals("no")) {
                    out.printf("Welcome to the adult zone, %s! " +
                            "Have a good rest, or a good working day!%n", name);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}