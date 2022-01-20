package View;

import Controller.StageController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddSalesPersonStage {
    private int salesPersonID;
    private String salesPersonPhoneNumber;
    private String salesPersonFirstName;
    private String salesPersonLastName;
    private final String terminationDate = "N/A"; //set to N/A since when adding new sales person termination is not required
    private String salesPersonManager;
    private String salesPersonAddress;
    StageController stageController;

    public AddSalesPersonStage() throws SQLException, ParseException {
        //create a .sql date obj to pass as the starting date
        String pattern = "yyyy-MM-dd";
        String salesPersonStartDate = new SimpleDateFormat(pattern).format(new Date());
        SimpleDateFormat sdf1 = new SimpleDateFormat(pattern);
        java.util.Date date = sdf1.parse(salesPersonStartDate);
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());

        //Title Label
        Label createSaleTitleLabel = new Label("ADD NEW SALE PERSON TO SYSTEM");

        //TEXT FIELDS
        TextField startDateTextField = new TextField();
        startDateTextField.setPromptText("ENTER TODAY'S DATE");
        startDateTextField.setText(salesPersonStartDate);   // sets the current date by default
        startDateTextField.setMaxWidth(220);
        startDateTextField.setDisable(true);
        Label startDateLabel = new Label("Start Date:");

        //Vbox for the date text field
        HBox startDateHbox = new HBox(startDateLabel, startDateTextField);
        startDateHbox.setAlignment(Pos.CENTER);

        //first name
        TextField salesPersonFirstNameTextField = new TextField();
        salesPersonFirstNameTextField.setMaxWidth(220);
        salesPersonFirstNameTextField.setPromptText("ENTER A NAME");

        //last name
        TextField salesPersonLastNameTextField = new TextField();
        salesPersonLastNameTextField.setMaxWidth(220);
        salesPersonLastNameTextField.setPromptText("ENTER LAST NAME");

        //address
        TextField salesPersonAddressTextField = new TextField();
        salesPersonAddressTextField.setMaxWidth(220);
        salesPersonAddressTextField.setPromptText("ENTER CURRENT ADDRESS");
        //phone
        TextField salesPersonPhoneTextField = new TextField();
        salesPersonPhoneTextField.setMaxWidth(220);
        salesPersonPhoneTextField.setPromptText("ENTER CURRENT PHONE NUMBER");

        //Manager
        TextField salesPersonManagerTextField = new TextField();
        salesPersonManagerTextField.setMaxWidth(220);
        salesPersonManagerTextField.setPromptText("MANAGER");

        //Sales Person ID
        TextField salesPersonIdTextField = new TextField();
        salesPersonIdTextField.setMaxWidth(220);
        salesPersonIdTextField.setPromptText("ENTER NEW SALES PERSON ID");


        Button addButton = new Button("Add New Person");
        //add button action
        addButton.setOnAction(ActionEvent -> {
            try {
                salesPersonID = Integer.parseInt(salesPersonIdTextField.getText());   //sales person id
                salesPersonFirstName = salesPersonFirstNameTextField.getText();// sales person first name
                salesPersonLastName = salesPersonLastNameTextField.getText();// sales person last name
                salesPersonManager = salesPersonManagerTextField.getText();// sales person manager
                salesPersonAddress = salesPersonAddressTextField.getText();//sales person address
                salesPersonPhoneNumber = salesPersonPhoneTextField.getText();// sales person phone

                //calls the create sale method passing the form information
                stageController.addSalesPerson(salesPersonID,salesPersonFirstName,salesPersonLastName,
                        salesPersonAddress,salesPersonPhoneNumber, sqlStartDate,terminationDate,salesPersonManager);

                //empty the text fields
                salesPersonAddressTextField.clear();
                salesPersonFirstNameTextField.clear();
                salesPersonIdTextField.clear();
                salesPersonLastNameTextField.clear();
                salesPersonManagerTextField.clear();
                salesPersonPhoneTextField.clear();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        //Vbox to hold nodes
        VBox createSaleVbox = new VBox();
        createSaleVbox.setAlignment(Pos.CENTER);
        createSaleVbox.setSpacing(20);
        VBox.setMargin(createSaleVbox, new Insets(50,50,150,50));

        //add nodes to Vbox
        createSaleVbox.getChildren().addAll(createSaleTitleLabel,startDateTextField,salesPersonIdTextField,salesPersonFirstNameTextField,salesPersonLastNameTextField
                ,salesPersonAddressTextField,salesPersonPhoneTextField,salesPersonManagerTextField,addButton);
        stageController = new StageController();
        Stage stage = new Stage();
        //Main Scene
        Scene scene = new Scene(createSaleVbox, 820, 620);
        stage.setTitle("Add New Sales Person");
        stage.setScene(scene);
        stage.show();
    }
}
