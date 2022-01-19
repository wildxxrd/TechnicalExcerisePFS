package Model;

import java.sql.*;

public class DB {

  public DB() throws SQLException {

    String dbURL = "jdbc:mysql://localhost:3306/BeSpockedDB"; //url
    String user = "admin";  // user name
    String pass = "Ilovejava22!"; //password

//    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection myConn = DriverManager.getConnection(dbURL, user, pass);

    Statement myStmt = myConn.createStatement();

    ResultSet myRs = myStmt.executeQuery("select  * from Customer");

    //testing
    while (myRs.next()) {
      System.out.println(myRs.getString("FirstName") + "\t" + myRs.getString("LastName"));
    }
  }
}
