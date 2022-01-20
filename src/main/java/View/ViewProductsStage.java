package View;

import Controller.StageController;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;


/*
* This class presents and run the Product Window
* */
public class ViewProductsStage extends Stage {
    StageController stageController;
    public ViewProductsStage() throws SQLException {
        String stageName = "product";// string to get the right scene from controller
        stageController = new StageController();
        Stage stage = new Stage();
        //Main Scene

        Scene scene = new Scene(stageController.getData(stageName), 820, 620);
        stage.setTitle("Products");
        stage.setScene(scene);
        stage.show();
    }

}
