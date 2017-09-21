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


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("GUIRequests.fxml"));

        Scene scene = new Scene(root,800,800);
        primaryStage.setScene(scene);
        primaryStage.show();

        try{
            Socket s = new Socket("127.0.0.1", 8001);

            while(true){
                //socket streams
                InputStream input = s.getInputStream();
                OutputStream output = s.getOutputStream();
                //ObjectInputStream ois = new ObjectInputStream(input);

                //input scanner
                Scanner scan = new Scanner(input);

                PrintWriter out = new PrintWriter(output, true);

                //scanner starts, modtag velkomst
                String welcome = scan.nextLine();




                out.println("Første besked");

                System.out.println(scan.nextLine());

               // System.out.println(ois.readObject().toString());
                System.out.println(scan.nextLine());

                System.out.println(scan.nextLine());

                s.close();
                System.out.println("Forbindelsen lukket.");
            }
        }   catch (IOException ex){
            ex.printStackTrace();
        }
    }



    }

