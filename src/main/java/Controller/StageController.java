package Controller;

import Model.DB;
import javafx.scene.control.TableView;

import java.sql.SQLException;

public class StageController {
    DB db;
    public StageController() throws SQLException {
        db = new DB();
    }

    public TableView getData(String stageQueryToExecute){
        return db.getCustomerData(stageQueryToExecute);
    }

}
