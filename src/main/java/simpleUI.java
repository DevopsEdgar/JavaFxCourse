import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class simpleUI extends Application {


    public void start(Stage stage) throws Exception {
        // esto se hacd para poder sacar referencia a
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/simpleui.fxml"));
        //HBox hBox = FXMLLoader.load(getClass().getResource("/simpleui.fxml"));
        HBox hBox = loader.load();

        SimpleController controller = loader.getController();
        System.out.println(controller.txtField.getText());
        Scene scene = new Scene(hBox);
        stage.setScene(scene);

        stage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
