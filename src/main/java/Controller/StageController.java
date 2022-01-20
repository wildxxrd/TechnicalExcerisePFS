package Controller;

import Model.DB;
import javafx.scene.control.TableView;

import java.sql.Date;
import java.sql.SQLException;

//this class connects the view with the model
public class StageController {
    DB db;

    //constructor creates an instance of the db to connect to database
    public StageController() throws SQLException {
        db = new DB();
    }

    public TableView getData(String stageQueryToExecute){
        return db.getCustomerData(stageQueryToExecute);
    }

    public void CreateSale(String date, int customerId, int salesPersonId, int productId) throws SQLException {
        db.CreateSale(date, customerId, salesPersonId,productId);
    }

    public void addSalesPerson(int salesPersonId, String salesPersonFirstName, String salesPersonLastName, String salesPersonAddress, String salesPersonPhoneNumber,
                               Date salesPersonStartDate, String salesPersonManager, String salesPersonTermination) throws SQLException {
        db.AddSalesPerson(salesPersonId,salesPersonFirstName,salesPersonLastName,salesPersonAddress,salesPersonPhoneNumber,salesPersonStartDate,salesPersonManager,
                salesPersonTermination);
    }

    public void CreateProduct(String productName, int productQuantity, int commission, double productPrice, String productManufacturer, String productStyle, int productId) throws SQLException {
        db.CreateProduct(productName,productQuantity,commission,productPrice,productManufacturer,productStyle,productId);
    }
}
