package me.harshana.chatapp.client.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import me.harshana.chatapp.client.AppInitializer;
import me.harshana.chatapp.client.util.helpers.ApplicationContext;

import java.io.IOException;

public class LoginFormController {

    public JFXButton btnStartChat;
    public JFXTextField txtUserName;

    public void initialize() {
        Platform.runLater(txtUserName::requestFocus);
    }

    private void initStreamConfiguration(String name) throws IOException {
        ApplicationContext.getStreamConfiguration().setName(name);
        ApplicationContext.getStreamConfiguration().initSocket();
        ApplicationContext.getStreamConfiguration().initStream();
    }

    public void startChatOnAction(ActionEvent actionEvent) {
        String name = txtUserName.getText();

        try {
            initStreamConfiguration(name);
            FXMLLoader fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("ClientForm.fxml"));
            Parent window = fxmlLoader.load();
            Scene newScene = new Scene(window);
            Stage mainWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            mainWindow.setResizable(false);
            mainWindow.setTitle("Messenger");
            mainWindow.getIcons().add(new Image("D:\\Projects\\Java\\chat-app\\src\\main\\resources\\assets\\messenger1.png"));
            mainWindow.centerOnScreen();
            mainWindow.setScene(newScene);
        } catch (IOException ex) {
            showErrorAlert("An error occurred.");
            ex.printStackTrace();
        } catch (Exception e) {
            showErrorAlert("An error occurred.");
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void showErrorAlert(String message) {
        new Alert(Alert.AlertType.ERROR, message).show();
    }
}
