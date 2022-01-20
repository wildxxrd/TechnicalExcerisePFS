package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.sql.*;
/*
* This class is responsible of connecting and getting
* All the needed information from the database
* */
public class DB {
  ObservableList<ObservableList> data;
  //table view obj that will be returned
  private TableView tableView;
  //connection obj
  private Connection myConn;
  Statement myStmt;
  public DB() throws SQLException {
    tableView = new TableView();

    String dbURL = "jdbc:mysql://localhost:3306/BeSpockedDB"; //url
    String user = "admin";  // user name
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

      //Finally add data to TableView
      tableView.setItems(data);
    } catch (Exception e) {
      e.printStackTrace();
    }
    //return the table view to the controller class
    return tableView;
  }

  //this method adds a sale to the database
  public void CreateSale(String date, int customerId, int salesPersonId, int productId) throws SQLException {

    myStmt.executeUpdate(("INSERT INTO `Sales`(SalesDate,Products_ProductID,SalesPerson_SalesPersonId,Customer_CustomerId) VALUE ('"+date+"','"+productId+"','"+salesPersonId+"',"+customerId+")"));
    System.out.println("Added Suscefully");

    myConn.close();
  }

  //this method adds a new salesperson to the database
  public void AddSalesPerson(int salesPersonId, String salesPersonFname, String salesPersonLName, String salesPersonAddres, String salesPersonPhoneNumber,
                             Date salesPersonStartDate, String salesPersonManager, String salesPersonTermination) throws SQLException {
    myStmt.executeUpdate(
            ("INSERT INTO `SalesPerson`(FirstName,LastName,Address,Phone,StartDate,TerminationDate,Manager,SalesPersonId) VALUE ('"+salesPersonFname+"','"+salesPersonLName+"','"+salesPersonAddres+"','"+salesPersonPhoneNumber+"','"+salesPersonStartDate+"','"+salesPersonTermination+"','"+salesPersonManager+"',"+salesPersonId+")"));
    System.out.println("Sales Person Added Suscefully");
  }


  //this method takes the name of the stage that calls it ]
  //then returns the appropiate sql statement
  //i created this method to reduce duplicate code
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
      sql = "SELECT Name, Manufacturer, Style, CONCAT(PurchasePrice ,'$') AS \"Purchase Price\", QtyOnHand AS 'Quantity on Hand', CONCAT(CommissionPercentage, '%') AS \"Commission Percentage\", CONCAT(ProductID) AS \"Producrt ID\"\n" +
              "FROM Products";
    }else if (stageName.equalsIgnoreCase("salesPerson")){
      sql = "Select CONCAT(FirstName) AS \"Sales Person First Name\", CONCAT(LastName) AS \"Sales Person Last Name\",Address, Phone, CONCAT(StartDate) AS \"Start Date\",CONCAT(TerminationDate) AS \"Termination Date\", Manager, CONCAT(SalesPersonId) AS \"Sales Person ID\"\n" +
              "FROM SalesPerson";
    }
    return sql;
  }


}
