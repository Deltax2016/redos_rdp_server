package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button entry8;

    @FXML
    private Button remouteButton;
    private Button conferenseButton;
    private Button historyConnectButton;
    private Button messengerButton;
    private Button settingsButton;

//    public void remouteAction(){
//        remouteButton.setText("rrrr");
//    }

    public void connectInToMe(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void remouteAction(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        //scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        //scene.getStylesheets().add("Styles.css");
        //remouteButton.setId("button");
        //remouteButton.getStyleClass().add("button");
        //stage.setScene(scene);
        //stage.show();
    }
    public void switchToMainScane(ActionEvent event) throws IOException{
//        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();

    }

    @FXML
    private AnchorPane anchor;
    @FXML
    private ImageView imageView;

    public void initialize() {
        imageView.fitHeightProperty().bind(anchor.heightProperty());
        imageView.fitWidthProperty().bind(anchor.widthProperty());
    }

    void onReceive(Object obj) {
        BufferedImage img = (BufferedImage) obj;
        imageView.setImage(SwingFXUtils.toFXImage(img, null));
    }



}
