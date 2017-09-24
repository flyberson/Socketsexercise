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

    }



    }

