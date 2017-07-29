
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ReadOnlyIntegerProperty;


import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.beans.property.ReadOnlyIntegerProperty.readOnlyIntegerProperty;

public class TodoController implements Initializable {

    private Task currentTask = new Task();
    private final ObservableList<Task> tasks = FXCollections.observableArrayList();

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button cancelButton;


    @FXML
    private Spinner<Integer> progresspinner;


    @FXML
    private Button addButton;

    @FXML
    private CheckBox checkBoxCompleted;

    @FXML
    private TableColumn<Task, String> descriptionColumn;

    @FXML
    private TableColumn<Task, String> priorityColumn;
    @FXML
    private TableColumn<Task, String> progressColumn;

    @FXML
    private ComboBox<String> comboPriority;

    @FXML
    private TableView<Task> taskTable;

    @FXML
    private TextField textDescription;

    public void initialize(URL location, ResourceBundle resources) {
        textDescription.setText("Esto es una prueba");
        comboPriority.setPromptText("Elija uno");
        comboPriority.getItems().addAll("High", "Medium", "Low");

        progresspinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
        progresspinner.valueProperty().addListener((ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) -> {
            if (newValue.intValue() == 100) checkBoxCompleted.setSelected(true);
            else checkBoxCompleted.setSelected(false);

            tasks.add(new Task(25 + newValue, "Medium", "Fix Bug 2447895" + newValue, newValue));

            currentTask.setDescription(" " + newValue);

//            System.out.println(currentTask.getDescription());
//            System.out.println(currentTask.getPriority());
//            System.out.println(currentTask.getProgress());
        });

        ReadOnlyIntegerProperty intProgress = readOnlyIntegerProperty(progresspinner.valueProperty());
        progressBar.progressProperty().bind(intProgress.divide(100.0));
        comboPriority.valueProperty().bindBidirectional(currentTask.priorityProperty());
        textDescription.textProperty().bindBidirectional(currentTask.descriptionProperty());
        progresspinner.getValueFactory().valueProperty().bindBidirectional(currentTask.progressProperty());

        taskTable.setItems(tasks);
        priorityColumn.setCellValueFactory(rowData -> rowData.getValue().priorityProperty());
        descriptionColumn.setCellValueFactory(rowData -> rowData.getValue().descriptionProperty());
        progressColumn.setCellValueFactory(rowData -> Bindings.concat(rowData.getValue().progressProperty(), "%"));

        tasks.addAll(new Task(1, "HIGH", "Complete Design Document", 10),
                new Task(2, "MEDIUM", "Update class Diagram", 0),
                new Task(3, "LOW", "Fix Bug 243658", 0)
        );
        StringBinding addButtonTextBinding = new StringBinding() {
            {
                super.bind(currentTask.idProperty());
            }
            @Override
            protected String computeValue() {
                if (currentTask.getId() == null){
                    return "Add";
                }else{
                    return "Update";
                }

            }
        };


        addButton.textProperty().bind(addButtonTextBinding);
        addButton.disableProperty().bind(Bindings.greaterThan(3, currentTask.descriptionProperty().length()));

        taskTable.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Task> observable, Task oldValue, Task newValue) -> {
            setCurrentTask(newValue);

        });


    }

    private void setCurrentTask(Task selectedTask) {
        if (selectedTask != null) {
            setProperties(selectedTask);
        } else {

            currentTask.setId(null);
            currentTask.setPriority("");
            currentTask.setDescription("");
        }

    }

    private void setProperties(Task selectedTask) {

        currentTask.setId(selectedTask.getId());
        currentTask.setPriority(selectedTask.getPriority());
        currentTask.setDescription(selectedTask.getDescription());
    }


}
