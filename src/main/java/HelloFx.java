import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.List;


public class HelloFx extends Application {


    public static void main(String[] args) {
        Application.launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Do-It");
        //main layout
        GridPane grid = new GridPane();
        grid.setMinHeight(600);
        grid.setMinWidth(400);
        grid.setGridLinesVisible(false);
        grid.setHgap(20);
        grid.setVgap(5);


        TableView table = new TableView();
        table.setMinHeight(300);
        table.setMinWidth(550);


        TableColumn column1 = new TableColumn("Priority");
        TableColumn column2 = new TableColumn("Description");
        TableColumn column3 = new TableColumn("Progress");
        List<TableColumn> columnList = Arrays.asList(column1,column2,column3);

        table.getColumns().addAll(columnList);


        GridPane.setConstraints(table, 1,1,3,1);

        TextField taskName = new TextField();
        taskName.setPromptText("introduce Tasks");
        taskName.setText("Default text");
        taskName.setMinWidth(300);

        GridPane.setConstraints(taskName, 2,2);

        ComboBox priority = new ComboBox();
        priority.getItems().addAll("High", "Low", "Medium");
        priority.setPromptText("Enter Prority");

        GridPane.setConstraints(priority,1,2);

        Button addButton = new Button();
        addButton.setText("Add");
        addButton.setMinWidth(100);
        GridPane.setConstraints(addButton,3,2);

        Button cancelButton = new Button();
        cancelButton.setText("Cancel");
        cancelButton.setMinWidth(100);
        GridPane.setConstraints(cancelButton,3,3);

        HBox progressArea = new HBox();
        progressArea.getChildren().addAll(new Label("Progress"),
                new Spinner<Integer>(0,100,0),
                new CheckBox("Completed"));
        progressArea.setAlignment(Pos.CENTER_RIGHT);
        progressArea.setSpacing(10);
        GridPane.setConstraints(progressArea,1,3,2,1);



        grid.getChildren().addAll(table, taskName, priority,addButton, cancelButton, progressArea);
        Scene scene = new Scene(grid,600,400);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);//con esta linea lo que hacemos es mantener simepre la ventana visible
        stage.setResizable(false); // con esta line impedimos que la ventana se dimensione
        stage.show();

    }

    @Override
    public void init() throws Exception {
        System.out.println("Este es el metodo init");
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Este es el metodo stop");
    }


}
