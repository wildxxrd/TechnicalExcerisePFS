package View;

import Controller.StageController;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class CreateSaleStage{

    StageController stageController;

    public CreateSaleStage() throws SQLException {
        String stageName = "createSale";
        stageController = new StageController();
        Stage stage = new Stage();
        //Main Scene
        Scene scene = new Scene(stageController.getData(stageName), 820, 620);
        stage.setTitle("Sales");
        stage.setScene(scene);
        stage.show();
    }
}
