import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class SimpleController implements Initializable {

    @FXML
    public TextField txtField;


    public void initialize(URL location, ResourceBundle resources) {
        txtField.setText("esto es una prueba");
        System.out.println("Estas en el metodo initilize de SimpleController");

    }
}
