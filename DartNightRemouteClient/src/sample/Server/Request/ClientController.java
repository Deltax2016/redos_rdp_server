package sample.Server.Request;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ClientController {

    @FXML
    private Button button;
    @FXML
    private Button remouteButton;


    public void click(ActionEvent event){
        button.setText("ffff");
    }

    public void hundleButtonClick() {
        button.setText("ffff");
    }

    public void hundleButtonClick2() {
        remouteButton.setText("ffff");
    }
}
