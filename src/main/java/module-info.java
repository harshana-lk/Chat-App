module me.harshana.chatapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;


    exports me.harshana.chatapp.server;
    exports me.harshana.chatapp.client;
    opens me.harshana.chatapp.client to javafx.fxml;
    opens me.harshana.chatapp.server to javafx.fxml;
    exports me.harshana.chatapp.client.controller;
    opens me.harshana.chatapp.client.controller to javafx.fxml;

}