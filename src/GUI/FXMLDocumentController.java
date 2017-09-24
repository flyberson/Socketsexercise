package GUI;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class FXMLDocumentController implements Initializable{
@FXML private Button buttonCount;
@FXML private Button buttonPut;
@FXML private Button buttonConnect;
private static Writer writer;
private static PrintWriter pwriter;
   private static InputStream input;
   private static OutputStream output;
@FXML private TextField textPut;
@FXML private TextArea textView;
    private static String what;
private boolean done=false;
private boolean open=true;
private static Socket s;
private static Scanner scan;

private static void updateTextView (){
    //textView.setText();
}

    public FXMLDocumentController() {
    }

    public void getConnection() {

    try{

        s = new Socket("127.0.0.1", 8001);



            //socket streams
            input = s.getInputStream();
             output = s.getOutputStream();
            writer = new OutputStreamWriter(s.getOutputStream(), "UTF-8");
            pwriter = new PrintWriter(output,true);

        //input scanner
       scan = new Scanner(input);


           /*if(scan.hasNextLine()){
               String view =scan.nextLine();
               textView.appendText(view);
           }*/


    }   catch (IOException ex){

        ex.printStackTrace();
    }

}

    @FXML private void handleButtonConnect(ActionEvent event) {

        try {

            getConnection();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeSocket() {
        try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



@FXML private void handleButtonCount(ActionEvent event){
    textView.clear();
            pwriter.println("COUNT");
    if(scan.hasNextLine()){

        String view =scan.nextLine();
        textView.appendText(view);
    }



}

    @FXML private void handleButtonPut(ActionEvent event){
            textView.clear();


                   pwriter.println("PUT:"+textPut.getText());
                 if(scan.hasNextLine()) {
                     String view = scan.nextLine();
                     textView.appendText(view);
                 }


            }




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
