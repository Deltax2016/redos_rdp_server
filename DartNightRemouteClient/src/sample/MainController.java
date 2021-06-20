package sample;

import ir.ashkanabd.StarterMain;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.GUI.ClientStarter;

public class MainController {

    @FXML
    private AnchorPane anchor;
    @FXML
    private ImageView imageView;
    @FXML
    private Button connect;
    @FXML
    private Button connect2;



    public void initialize() {
        //imageView.fitHeightProperty().bind(anchor.heightProperty());
        //imageView.fitWidthProperty().bind(anchor.widthProperty());
    }

    void onReceive(Object obj) {
        //BufferedImage img = (BufferedImage) obj;
        //imageView.setImage(SwingFXUtils.toFXImage(img, null));
    }

    // Для кнопки входа открывать окно entry
    public void signIn(){
        //ClientStarter clientStarter = new ClientStarter();
    }

    // entry8 Открывам окно трансляции, если пароль верный, 3 реквеста
    public void goToControlWindow(ActionEvent event){
        connect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                ClientStarter clientStarter = new ClientStarter();
                try {
                    clientStarter.start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void activateStream(ActionEvent event){
        connect2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StarterMain starterMain = new StarterMain();
                try {
                    starterMain.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    // Открывать новое окно для чата
    public void goToChat(){

    }

    // Открывать окно настроек
    public void goToSettings(){

    }
}
