import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class HelloFx extends Application {


    public static void main(String[] args) {
        Application.launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Do-It");
        Group group = new Group();
        Button b1 = new Button("Fist Button");
        Button b2 = new Button("Second Button");
        group.getChildren().addAll(b1, b2);
        b1.setLayoutX(50);
        b1.setLayoutX(150);

        Scene scene = new Scene(group,300,300);
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
