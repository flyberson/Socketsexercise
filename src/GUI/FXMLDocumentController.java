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

import static java.lang.Integer.parseInt;


public class FXMLDocumentController implements Initializable{
@FXML private Button buttonCount;
@FXML private Button buttonPut;
@FXML private Button buttonConnect;
    @FXML private Button buttonName;
private static Writer writer;
private static PrintWriter pwriter;
   private static InputStream input;
   private static OutputStream output;
@FXML private TextField textPut;
    @FXML private TextField textName;
@FXML private TextArea textView;
    private static String what;
private boolean done=false;
private boolean open=true;
private static Socket s;
private static Scanner scan;
private static int count=0;
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



@FXML public void handleButtonCount() {
    //textView.clear();
    pwriter.println("COUNT");
    int view;
    String sview = null;


    sview = scan.nextLine();
    sview = sview.substring(6, 7);
    count = parseInt(sview);
    count -= 1;
    int newcount = -2;
    if (count != newcount) {
        newcount = count;
        //textView.appendText(sview);
        pwriter.println("GET:" + newcount);
        sview = scan.nextLine();
        textView.appendText("\n" + sview);
    }
}

    @FXML private void handleButtonName(ActionEvent event){
        //textView.clear();
        pwriter.println("NAME:"+textName.getText());
        String sview = null;


        sview = scan.nextLine();

}

    @FXML private void handleButtonPut(ActionEvent event){
            //textView.clear();
                String view;

                   pwriter.println("PUT:"+textPut.getText());
                // if(scan.hasNextLine()) {

         view = scan.nextLine();
       // textView.appendText(view);
                 //}






            }




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
