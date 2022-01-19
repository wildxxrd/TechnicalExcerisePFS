package View;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage){

        Label label = new Label("Hello ");
        label.setAlignment(Pos.CENTER);
        Scene scene = new Scene(label, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}