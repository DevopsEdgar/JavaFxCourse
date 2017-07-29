import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;
public class TodoController implements Initializable {

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button cancelButton;

    @FXML
    private TableColumn<?, ?> description;

    @FXML
    private Spinner <Integer> progresspinner;


    @FXML
    private Button addButton;

    @FXML
    private CheckBox checkBoxCompleted;

    @FXML
    private TableColumn<?, ?> priority;

    @FXML
    private ComboBox<String> comboPriority;

    @FXML
    private TableView<?> taskTable;

    @FXML
    private TextField textDescription;

    public void initialize(URL location, ResourceBundle resources) {
        textDescription.setText("Esto es una prueba");
        comboPriority.setPromptText("Elija uno");
        comboPriority.getItems().addAll("High", "Medium", "Low");

        progresspinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
        progresspinner.valueProperty().addListener(new ChangeListener<Integer>() {
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                if (newValue.intValue() == 100) checkBoxCompleted.setSelected(true); else checkBoxCompleted.setSelected(false);
                progressBar.setProgress(1.0*newValue/100);
            }
        });


    }
}
