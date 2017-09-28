package GUI;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class GUIController extends Application {
    @FXML private Button buttonCount;
    Writer writer;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {

        Parent root = FXMLLoader.load(getClass().getResource("GUIRequests.fxml"));

        Scene scene = new Scene(root,800,800);
        primaryStage.setScene(scene);
        primaryStage.show();

        FXMLDocumentController controller = new FXMLDocumentController();
        controller.getConnection();

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.handleButtonCount();
            }
        });

        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Timer timer = new Timer();
        TimerTask timertask = new TimerTask() {
            @Override
            public void run() {
                controller.handleButtonCount();
            }
        };
        // timer doesnt work yet
        //timer.schedule(timertask,5001);

    }



    }

