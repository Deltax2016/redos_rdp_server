package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.GUI.ClientController;
import sample.Request.GetInput;
import sample.Request.SendScreen;
import sample.connection.ClientServer;

import java.util.Objects;


public class Main extends Application {

    //private Server server;
    private GetInput getInput;
    private Thread socketThread, sendThread;
    private SendScreen sendScreen;

    private FXMLLoader mainLoader;
    private Thread outSocketThread, receiveThread;
    private Scene mainScene;
    private ClientServer clientServer;
    private static Main main;
    private ClientController clientController;

    public static String mouseType = "m";
    public static String keyType = "k";



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
        launch(args);
        //Main main = new Main();

        //main.startServer();
    }


}
