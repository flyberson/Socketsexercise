package GUI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

