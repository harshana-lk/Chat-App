package me.harshana.chatapp.server.util.threads;

import me.harshana.chatapp.server.ChatServer;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private final ChatServer server;
    private final Socket socket;
    private final int ID;
    private DataInputStream streamIn;
    private DataOutputStream streamOut;
    private String clientName;
    private boolean haveName;

    public ServerThread(ChatServer server, Socket socket) {
        this.server = server;
        this.socket = socket;
        this.ID = socket.getPort();
        this.haveName = false;
    }

    public void send(String msg) {
        try {
            streamOut.writeUTF(msg);
            streamOut.flush();
        } catch (IOException e) {
            System.out.println(ID + " ERROR sending: " + e.getMessage());
            close();
        }
    }

    public void run() {
        System.out.println("Server Thread " + ID + " running.");
        try {
            open();
            while (true) {
                if (!haveName) {
                    setClientName(streamIn.readUTF());
                    haveName = true;
                    continue;
                }
                server.handle(ID, streamIn.readUTF());
            }
        } catch (IOException e) {
            System.out.println(ID + " ERROR reading: " + e.getMessage());
            close();
        }
    }

    public void open() throws IOException {
        streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        streamOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }

    public void close() {
        try {
            if (socket != null) socket.close();
            if (streamIn != null) streamIn.close();
            if (streamOut != null) streamOut.close();
        } catch (IOException e) {
            System.out.println(ID + " Error closing thread: " + e.getMessage());
        }
    }


    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getID() {
        return ID;
    }


}
