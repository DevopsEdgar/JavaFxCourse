import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class HelloFx extends Application{


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Do-It");
        GridPane gridPane = FXMLLoader.load(getClass().getResource("/todo.fxml"));
        Scene scene = new Scene(gridPane, 800,500);

        stage.setScene(scene);

//        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                System.out.println(event.getEventType().getName());
//                event.consume();
//
//            }
//        });
        stage.show();

    }



}
