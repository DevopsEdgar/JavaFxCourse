import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;


public class HelloFx extends Application{

    TodoController controller;


    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/todo.fxml"));
        stage.setTitle("Do-It");
        GridPane gridPane = loader.load();
        controller = loader.getController();
        Scene scene = new Scene(gridPane, 800,500);

//        stage.setOnCloseRequest(event -> {
//            onClose(event);
//        });

        stage.setOnCloseRequest(this::onClose);
        controller.setTaskMap(readTaskFile());

        stage.setScene(scene);
        stage.show();

    }

    private HashMap<Integer, Task> readTaskFile() {

        FileInputStream in = null;
        HashMap<Integer, Task> taskMap = new HashMap<>();
        try {
            in = new FileInputStream("tasks.xml");
            XMLDecoder decoder = new XMLDecoder(in);
            taskMap = (HashMap<Integer, Task>) decoder.readObject();
            decoder.close();
        } catch (FileNotFoundException e) {
            if (in != null){
                    try {
                        in.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
            }
            e.printStackTrace();
        }finally {
            return taskMap;
        }


    }


    private void onClose(WindowEvent event) {
//        new Alert(Alert.AlertType.INFORMATION, event.getEventType().getName()).showAndWait();
        FileOutputStream out= null;

        try {
            out = new FileOutputStream("tasks.xml");
            XMLEncoder encoder = new XMLEncoder(out);
            encoder.writeObject(controller.getTaskMap());
            encoder.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


}
