package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//นายดิศรณ์  ฐืติกรโกวิท 5810400990
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Server server = new Server();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
