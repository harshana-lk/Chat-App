package me.harshana.chatapp.server;

import me.harshana.chatapp.server.util.threads.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer implements Runnable {
    private final List<ServerThread> clients = new ArrayList<>();
    private ServerSocket server = null;
    private Thread thread = null;
    private int clientCount = 0;

    public ChatServer(int port) {
        try {
            System.out.println("Binding to port " + port + ", please wait...");
            server = new ServerSocket(port);
            System.out.println("Server started: " + server);
            start();
        } catch (IOException e) {
            System.out.println("Cannot bind to port " + port + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ChatServer(44001);
    }

    public void run() {
        while (thread != null) {
            try {
                System.out.println("Waiting for a client ...");
                addThread(server.accept());
            } catch (IOException ioe) {
                System.out.println("Server accept error: " + ioe);
                stop();
            }
        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() {
        if (thread != null) {
            thread = null;
        }
    }

    public synchronized void handle(int ID, String input) {
        for (ServerThread client : clients) {
            if (client.getID() == ID) {
                continue;
            }
            client.send(input);
        }
    }

    private void addThread(Socket socket) {
        if (clientCount < 50) {
            System.out.println("Client accepted: " + socket);
            ServerThread serverThread = new ServerThread(this, socket);
            try {
                serverThread.open();
                serverThread.start();
                clients.add(serverThread);
                clientCount++;
            } catch (IOException e) {
                System.out.println("Error opening thread: " + e);
            }
        } else {
            System.out.println("Client refused: maximum " + 50 + " reached.");
        }
    }


}
