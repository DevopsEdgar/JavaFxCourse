
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import static javafx.beans.property.ReadOnlyIntegerProperty.readOnlyIntegerProperty;

public class TodoController implements Initializable {

    private Task currentTask = new Task();



    private final ObservableList<Task> tasks = FXCollections.observableArrayList();



    private HashMap<Integer, Task> taskMap = new HashMap<>();

    public void setTaskMap(HashMap<Integer, Task> initialTaskMap) {
        taskMap.clear();
        tasks.clear();
        taskMap.putAll(initialTaskMap);
        tasks.addAll(initialTaskMap.values());
        lastId = taskMap.keySet().stream().max(Integer::compare).get();

    }
    public HashMap<Integer, Task> getTaskMap() {
        return taskMap;
    }

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

        comboPriority.getItems().addAll("High", "Medium", "Low");
        progresspinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
        progresspinner.valueProperty().addListener((ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) -> {
            if (newValue.intValue() == 100) checkBoxCompleted.setSelected(true);
            else checkBoxCompleted.setSelected(false);
            currentTask.setDescription(" " + newValue);

        });

        ReadOnlyIntegerProperty intProgress = readOnlyIntegerProperty(progresspinner.valueProperty());
        progressBar.progressProperty().bind(intProgress.divide(100.0));
        comboPriority.valueProperty().bindBidirectional(currentTask.priorityProperty());
//        textDescription.textProperty().bindBidirectional(currentTask.descriptionProperty());
        progresspinner.getValueFactory().valueProperty().bindBidirectional(currentTask.progressProperty());

        taskTable.setItems(tasks);
        priorityColumn.setCellValueFactory(rowData -> rowData.getValue().priorityProperty());
        descriptionColumn.setCellValueFactory(rowData -> rowData.getValue().descriptionProperty());
        progressColumn.setCellValueFactory(rowData -> Bindings.concat(rowData.getValue().progressProperty(), "%"));

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
    int lastId = 0;
    @FXML
    public void addButtonClicked(ActionEvent actionEvent) {
        if (currentTask.getId() == null){
            Task t = new Task(++lastId,currentTask.getPriority(), currentTask.getDescription(), currentTask.getProgress());
            tasks.add(t);
            taskMap.put(lastId, t);
        }
        setCurrentTask(null);

    }

    @FXML
    public void cancelButtonClicked(ActionEvent actionEvent) {
        showAlertDialog();
        setCurrentTask(null);
        taskTable.getSelectionModel().clearSelection();


    }
    public  void showAlertDialog(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Are you want to cancell");
        alert.setTitle("Cancell");
        alert.getButtonTypes().remove(0,2);
        alert.getButtonTypes().add(0, ButtonType.YES);
        alert.getButtonTypes().add(1, ButtonType.NO);
//        alert.setContentText("Content text");
//        alert.showAndWait();
        Optional<ButtonType> confirmationResponse = alert.showAndWait();
        if (confirmationResponse.get() == ButtonType.YES){
            System.out.println("ok");
        }else{
            System.out.println("cancel");
        }

    }


}
