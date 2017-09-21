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
private static Writer writer;
   private static InputStream input;
   private static OutputStream output;
@FXML private TextField textPut;
@FXML private TextArea textView;
    private static String what;
private boolean done=false;


private static void updateTextView (){
    //textView.setText();
}

    public FXMLDocumentController() {
    }

    public void getConnection() throws InterruptedException {
    try{
        Socket s = new Socket("127.0.0.1", 8001);



            //socket streams
            input = s.getInputStream();
             output = s.getOutputStream();
            writer = new OutputStreamWriter(s.getOutputStream(), "UTF-8");

        //input scanner
        Scanner scan = new Scanner(input);

        //while(!done&& scan.hasNextLine()){
            what = scan.next();

           // if(scan.next()=="hello"){
                done=false;
          //  }









        //}

        //s.close();



    }   catch (IOException ex){
        ex.printStackTrace();
    }
}




@FXML private void handleButtonCount(ActionEvent event){
    buttonCount.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            textView.setText(what);

            try{writer.write("COUNT");

                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });


}

    @FXML private void handleButtonPut(ActionEvent event){
        buttonCount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    System.out.println(textPut.getText());
                    writer.write("PUT:"+textPut.getText());
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
