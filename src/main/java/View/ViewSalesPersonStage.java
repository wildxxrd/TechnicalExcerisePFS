package View;

import Controller.StageController;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

/*
* This class Creates and show the Sales Person Window
* */
public class ViewSalesPersonStage {

    StageController stageController;

    public ViewSalesPersonStage() throws SQLException {
        String stageName = "salesPerson";   //string to get the right scene from controller
        stageController = new StageController();
        Stage stage = new Stage();
        //Main Scene
        Scene scene = new Scene(stageController.getData(stageName), 950, 620);
        stage.setTitle("Sales Persons");
        stage.setScene(scene);
        stage.show();
    }
}
