package View;
import Controller.StageController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;

/*
* This class Presents and runs the Customer Window
* */
public class ViewCustomerStage extends Stage {

    StageController stageController;

    public ViewCustomerStage() throws SQLException {
        String stageName = "customer";
        stageController = new StageController();
        Stage stage = new Stage();
        //Main Scene
        Scene scene = new Scene(stageController.getData(stageName), 820, 620);
        stage.setTitle("Sales");
        stage.setScene(scene);
        stage.show();
    }


}
