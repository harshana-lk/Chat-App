package me.harshana.chatapp.client.util.config;

import com.jfoenix.effects.JFXDepthManager;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import me.harshana.chatapp.client.util.helpers.ApplicationContext;

import java.io.File;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class MessageConfiguration {

    private void createLabel(ListView<HBox> listView, String text, Pos pos) {
        HBox hBox = new HBox();
        Label l1 = new Label();
        Label l2 = new Label(LocalTime.now()
                .truncatedTo(ChronoUnit.MINUTES)
                .format(DateTimeFormatter.ISO_LOCAL_TIME));

        l1.setText(text+"\t"+l2.getText().toString());
        l1.setFont(Font.font("Consolas", FontWeight.NORMAL, 14));
        l1.setStyle("-fx-background-color: #008069; -fx-background-radius: 13px; -fx-padding: 10px");
        l1.setTextFill(Color.web("#ffffff"));
        l1.setWrapText(true);
        hBox.setMaxWidth(listView.getWidth() - 20);
        hBox.setAlignment(pos);
        hBox.getChildren().add(l1);
        JFXDepthManager.setDepth(hBox, 2);
        listView.getItems().add(hBox);
        listView.scrollTo(listView.getItems().lastIndexOf(hBox));
    }

    private void createImageView(ListView<HBox> listView, String img, Pos pos) {
        String[] ar = img.split(":- ");
        File file = new File(ar[1]);

        HBox hBox = new HBox();
        Label name = new Label();
        name.setText(ar[0] + ":- ");
        name.setFont(Font.font("System", FontWeight.NORMAL, 14));
//        hBox.setStyle("-fx-background-color: #008069;");
        name.setTextFill(Color.web("#222222"));
        Image image = new Image(file.toURI().toString());
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setX(10);
        imageView.setY(20);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);
        hBox.setMaxWidth(listView.getWidth() - 20);
        hBox.setAlignment(pos);
        hBox.getChildren().add(name);
        hBox.getChildren().add(imageView);
        JFXDepthManager.setDepth(hBox, 2);
        listView.getItems().add(hBox);
        listView.scrollTo(listView.getItems().lastIndexOf(hBox));
    }

    public void getClientNotification(ListView<HBox> listView, String text) {
        HBox hBox = new HBox();
        Label l1 = new Label();

        l1.setText(text);
        l1.setFont(Font.font("Consolas", FontWeight.NORMAL, 12));
        l1.setStyle("-fx-background-color: #008069; -fx-background-radius: 13px; -fx-padding: 10px");
        l1.setTextFill(Color.web("#ffffff"));
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(l1);
        listView.getItems().add(hBox);
        listView.scrollTo(listView.getItems().lastIndexOf(hBox));
    }

    public void getClientRespond(ListView<HBox> listView, String text) {
        String name = ApplicationContext.getStreamConfiguration().getName();
        if (text.endsWith(".png") || text.endsWith(".jpg") || text.endsWith(".gif")) {
            createImageView(listView, name + ":- " + text, Pos.TOP_RIGHT);
        } else {
            createLabel(listView, name + ":- " + text, Pos.TOP_RIGHT);
        }
    }

    public void getServerRespond(ListView<HBox> listView, String text) {
        if (text.endsWith(".png") || text.endsWith(".jpg") || text.endsWith(".gif")) {
            createImageView(listView, " " + text, Pos.TOP_LEFT);
        } else {
            createLabel(listView, " " + text, Pos.TOP_LEFT);
        }
    }
}
