package sample.GUI;

import sample.connection.MouseInfo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.connection.ClientServer;

import static sample.connection.ClientServer.keyType;
import static sample.connection.ClientServer.mouseType;

public class ClientStarter extends Application {

    private FXMLLoader mainLoader;
    private Thread socketThread, receiveThread;
    private Scene mainScene;
    private Parent root;
    private ClientServer server;
    private static ClientStarter clientStarter;
    private ClientController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        controller = new ClientController();
        mainLoader.setController(controller);
        root = mainLoader.load();
        primaryStage.setTitle("Control");
        mainScene = new Scene(root, 800, 500);
        mainScene.setOnKeyPressed(this::watchKeyInput);
        mainScene.setOnKeyReleased(this::watchKeyInput);
        mainScene.setOnMousePressed(this::watchMouseKey);
        mainScene.setOnMouseReleased(this::watchMouseKey);
        mainScene.setOnMouseMoved(this::watchMouseKey);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private void watchKeyInput(KeyEvent keyEvent) {
        try {
            server.sendKey(keyEvent, keyType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void watchMouseKey(MouseEvent mouseEvent) {
        try {
            server.sendMouse(new MouseInfo(mouseEvent), mainScene.getWidth(), mainScene.getHeight(), mouseType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            server = new ClientServer(52000);
            server.acceptClient();
            server.setOnReceive(controller::onReceive);
            receiveThread = new Thread(() -> {
                try {
                    server.receive();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "Receive");
            receiveThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ClientStarter() {
        socketThread = new Thread(this::start, "Socket");
        socketThread.start();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
        clientStarter = new ClientStarter();
    }
}
