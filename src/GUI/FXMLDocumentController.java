package GUI;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;


public class FXMLDocumentController implements Initializable {
@FXML private Button buttonCount;
@FXML private Button buttonPut;




@FXML private void handleButtonCount(ActionEvent event){
    buttonCount.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("hellopresscount");
        }
    });


}

    @FXML private void handleButtonPut(ActionEvent event){
        buttonCount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("hellopressput");
            }
        });


    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
