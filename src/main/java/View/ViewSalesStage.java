package View;

import Controller.StageController;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class ViewSalesStage extends Stage {
        StageController stageController;

    ViewSalesStage() throws SQLException {
        String stageName = "sales"; //string to get the right scene from controller
        Stage stage = new Stage();
        stageController = new StageController();
        //Main Scene
        Scene scene = new Scene(stageController.getData(stageName), 500, 320);
        stage.setTitle("Sales");
        stage.setScene(scene);
        stage.show();
    }




}
