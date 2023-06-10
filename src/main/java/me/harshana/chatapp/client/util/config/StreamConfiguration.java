package me.harshana.chatapp.client.util.config;

import javafx.scene.control.Alert;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class StreamConfiguration {
    private int portAddr = 54001;
    private Socket socket;
    private String hostAddr = "localhost";
    private String name;
    private DataInputStream streamIn;
    private DataOutputStream streamOut;

    public void initSocket() throws IOException {
        if (hostAddr.isEmpty() || portAddr == 0) {
            new Alert(Alert.AlertType.INFORMATION, "Invalid host address or port address. Please try again...").show();
            return;
        }
        this.socket = new Socket(hostAddr, portAddr);
    }

    public void initStream() {
        try {
            streamIn = new DataInputStream(socket.getInputStream());
            streamOut = new DataOutputStream(socket.getOutputStream());
            streamOut.writeUTF(name);
            streamOut.flush();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred while initializing streams. Please check the server and try again...").show();
            System.exit(0);
        }
    }

    public void stopStream() {
        try {
            if (streamIn != null) streamIn.close();
            if (streamOut != null) streamOut.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred closing the application. Shutdown initiated.").show();
            System.exit(0);
        }

    }

    public DataInputStream getStreamIn() {
        return streamIn;
    }


    public DataOutputStream getStreamOut() {
        return streamOut;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
