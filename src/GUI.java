/**
 * 14-09-2017
 *
 * @author Mads Heilberg
 * mads.heilberg@gmail.com
 * DAT - EASJ Næstved
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class GUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));

        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Putin Socket Exchange - Welcome friend!");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
