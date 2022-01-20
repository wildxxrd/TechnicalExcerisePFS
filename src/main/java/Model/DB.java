package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.sql.*;
/*
* This class is responsible for connecting and getting
* All the needed information from the database
* */
public class DB {
  ObservableList<ObservableList> data;
  //table view obj that will be returned
  private final TableView tableView;
  //connection obj
  private final Connection myConn;
  Statement myStmt;
  //Alert
  Alert errorAlert;
  Alert confirmationAlert;

  //constructor starts the connection
  public DB() throws SQLException {
    errorAlert = new Alert(Alert.AlertType.ERROR);
    confirmationAlert = new Alert(AlertType.INFORMATION);
    tableView = new TableView();

    String dbURL = "jdbc:mysql://localhost:3306/BeSpockedDB"; //url
    String user = "admin";  // username
    String pass = "Ilovejava22!"; //password

    //SQL connection variables
    myConn = DriverManager.getConnection(dbURL, user, pass);
    data = FXCollections.observableArrayList();
    myStmt = myConn.createStatement();

  }

  //returns a table view according to the String that is passed as parameter
  public TableView getCustomerData(String queryToExecute) {

    //table view to show the dataset
    //TABLE VIEW AND DATA
    try {
      //execute query
      ResultSet myRs = myStmt.executeQuery(validateSQL(queryToExecute));

      //Add Table Column Dynamically
      for (int i = 0; i < myRs.getMetaData().getColumnCount(); i++) {
        //making dynamic table
        final int j = i;
        TableColumn col = new TableColumn(myRs.getMetaData().getColumnName(i + 1));
        col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(j).toString()));

        tableView.getColumns().addAll(col);
      }

      //Add data to tableView
      while (myRs.next()) {
        //Iterate Row
        ObservableList<String> row = FXCollections.observableArrayList();
        for (int i = 1; i <= myRs.getMetaData().getColumnCount(); i++) {
          //Iterate Column
          row.add(myRs.getString(i));
        }
        data.add(row);

      }

      //Finally, add data to TableView
      tableView.setItems(data);
    } catch (Exception e) {
      e.printStackTrace();
    }
    //return the table view to the controller class
    return tableView;
  }

  //this method adds a sale to the database
  public void CreateSale(String date, int customerId, int salesPersonId, int productId) throws SQLException {
    try {
      myStmt.executeUpdate(("INSERT INTO `Sales`(SalesDate,Products_ProductID,SalesPerson_SalesPersonId,Customer_CustomerId) VALUE ('" + date + "','" + productId + "','" + salesPersonId + "'," + customerId + ")"));
      confirmationAlert.setTitle("Sales Created Successfully");
      confirmationAlert.setHeaderText("Sales Created Successfully");
      confirmationAlert.showAndWait();
    }catch (SQLIntegrityConstraintViolationException e){
      errorAlert.setTitle("ID Number Entered Does Not Exist");
      errorAlert.setHeaderText("One of The ID Entered Does Not Exist");
      errorAlert.setContentText("Sales Person ID Starts With a 3000\nCustomers ID Starts With a 1000\nProduct ID Starts With a 2000");
      errorAlert.showAndWait();
    }

    myConn.close();
  }

  //this method adds a product to the database
  public void CreateProduct(String productName, int productQuantity, int commission, double productPrice, String productManufacturer, String productStyle, int productId) throws SQLException {
    try {
        myStmt.executeUpdate(("INSERT INTO `Products`(Name,Manufacturer,Style,PurchasePrice,QtyOnHand,CommissionPercentage,ProductID) VALUE ('" + productName + "','" + productManufacturer + "','" + productStyle + "','" + productPrice + "','" + productQuantity + "','" + commission + "'," + productId + ")"));
        confirmationAlert.setTitle("Product Added Successfully");
        confirmationAlert.setHeaderText("New Product Added!");
        confirmationAlert.showAndWait();
    }catch (SQLException e){
        errorAlert.setTitle("Error Creating New Product");
        errorAlert.setHeaderText("Error Creating New Product");
    }
    myConn.close();
  }

  //this method adds a new salesperson to the database
  public void AddSalesPerson(int salesPersonId, String salesPersonFirstName, String salesPersonLastName, String salesPersonAddress, String salesPersonPhoneNumber,
                             Date salesPersonStartDate, String salesPersonManager, String salesPersonTermination) throws SQLException {
    try {
      myStmt.executeUpdate(
              ("INSERT INTO `SalesPerson`(FirstName,LastName,Address,Phone,StartDate,TerminationDate,Manager,SalesPersonId) VALUE ('" + salesPersonFirstName + "','" + salesPersonLastName + "','" + salesPersonAddress + "','" + salesPersonPhoneNumber + "','" + salesPersonStartDate + "','" + salesPersonTermination + "','" + salesPersonManager + "'," + salesPersonId + ")"));
      confirmationAlert.setTitle("Product Added Successfully");
      confirmationAlert.setHeaderText("Product Added Successfully");
      confirmationAlert.showAndWait();
    }catch (SQLIntegrityConstraintViolationException e){
      errorAlert.setTitle("Error Adding Sales Person");
      errorAlert.setHeaderText("The ID Number You Entered is Already in The System");
      errorAlert.setContentText(null);
      errorAlert.showAndWait();
    }
  }


  //this method takes the name of the stage that calls it
  //then returns the appropriate sql statement
  //I created this method to reduce duplicate code
  private String validateSQL(String stageName){
    String sql = "";
    if (stageName.equalsIgnoreCase("customer")) {
      sql = "SELECT CONCAT(FirstName) AS \"First Name\", CONCAT(LastName) AS \"Last Name\",Address, startDate ,CONCAT(CustomerId) AS \"Customer ID\"\n" +
              "FROM CUSTOMER;";
    }else if(stageName.equalsIgnoreCase("sales")){
      sql = """
                    SELECT Sales.SalesDate AS "Sales Date",\s
                    CONCAT(Customer.FirstName, ', ', Customer.LastName) AS "Customer",\s
                    CONCAT(SalesPerson.FirstName,', ', SalesPerson.LastName) AS "Sales Person",
                    CONCAT(Products.Name,': ',Products.purchasePrice, '$') AS "Product Name & Price"
                    FROM Sales
                    INNER JOIN Customer ON Sales.Customer_CustomerId = Customer.CustomerId
                    INNER JOIN Products ON Sales.Products_ProductID = Products.ProductID
                    INNER JOIN SalesPerson ON Sales.SalesPerson_SalesPersonId = SalesPerson.SalesPersonId;""";
    }else if (stageName.equalsIgnoreCase("product")){
      sql = "SELECT Name, Manufacturer, Style, CONCAT(PurchasePrice ,'$') AS \"Purchase Price\", QtyOnHand AS 'Quantity on Hand', CONCAT(CommissionPercentage, '%') AS \"Commission Percentage\", CONCAT(ProductID) AS \"Product ID\"\n" +
              "FROM Products";
    }else if (stageName.equalsIgnoreCase("salesPerson")){
      sql = "Select CONCAT(FirstName) AS \"Sales Person First Name\", CONCAT(LastName) AS \"Sales Person Last Name\",Address, Phone, CONCAT(StartDate) AS \"Start Date\",CONCAT(TerminationDate) AS \"Termination Date\", Manager, CONCAT(SalesPersonId) AS \"Sales Person ID\"\n" +
              "FROM SalesPerson";
    }
    return sql;
  }


}
