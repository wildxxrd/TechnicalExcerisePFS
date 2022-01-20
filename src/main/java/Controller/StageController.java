package Controller;

import Model.DB;
import javafx.scene.control.TableView;

import java.sql.Date;
import java.sql.SQLException;

public class StageController {
    DB db;
    public StageController() throws SQLException {
        db = new DB();
    }

    public TableView getData(String stageQueryToExecute){
        return db.getCustomerData(stageQueryToExecute);
    }

    public void CreateSale(String date, int customerId, int salesPersonId, int productId) throws SQLException {
        db.CreateSale(date, customerId, salesPersonId,productId);
    }

    public void addSalesPerson(int salesPersonId, String salesPersonFname, String salesPersonLName, String salesPersonAddres, String salesPersonPhoneNumber,
                               Date salesPersonStartDate, String salesPersonManager, String salesPersonTermination) throws SQLException {
        db.AddSalesPerson(salesPersonId,salesPersonFname,salesPersonLName,salesPersonAddres,salesPersonPhoneNumber,salesPersonStartDate,salesPersonManager,
                salesPersonTermination);
    }
}
