package View;

import Controller.StageController;
import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import javafx.event.ActionEvent;
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
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateSaleStage{
    private int customerId;
    private int salesPersonId;
    private int productId;
    StageController stageController;

    public CreateSaleStage() throws SQLException {
        String pattern = "yyyy-MM-dd";
        String dateInString = new SimpleDateFormat(pattern).format(new Date());
        String stageName = "createSale";

        //Title Label
        Label createSaleTitleLabel = new Label("Sale Information");

        //TEXT FIELDS
        TextField salesDateTextField = new TextField();
        salesDateTextField.setPromptText("ENTER TODAY'S DATE");
        salesDateTextField.setText(dateInString);   // sets the current date by default
        salesDateTextField.setMaxWidth(220);
        Label dateLabel = new Label("Date:");

        //Hbox for the date date texfield
        HBox dateHbox = new HBox(dateLabel, salesDateTextField);
            dateHbox.setAlignment(Pos.CENTER);
        TextField customerIdTextField = new TextField();
        customerIdTextField.setMaxWidth(220);
        customerIdTextField.setPromptText("ENTER CUSTOMER ID");
        //sale person id
        TextField salesPersonIdTextField = new TextField();
        salesPersonIdTextField.setMaxWidth(220);
        salesPersonIdTextField.setPromptText("ENTER SALES PERSON ID");
        //product id
        TextField productIdTextField = new TextField();
        productIdTextField.setMaxWidth(220);
        productIdTextField.setPromptText("ENTER THE PRODUCT ID");


        Button addButton = new Button("Create Sale");
        //add button action
        addButton.setOnAction(ActionEvent -> {
            try {
                    customerId = Integer.parseInt(customerIdTextField.getText());   //customer id
                    productId = Integer.parseInt(productIdTextField.getText()); //product id
                    salesPersonId = Integer.parseInt(salesPersonIdTextField.getText()); // sales person id

                    //calls the create sale method passing the form information
                    stageController.CreateSale(dateInString,customerId,salesPersonId,productId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        //Vbox to hold nodes
        VBox createSaleVbox = new VBox();
        createSaleVbox.setAlignment(Pos.CENTER);
        createSaleVbox.setSpacing(20);
        VBox.setMargin(createSaleVbox, new Insets(50,50,150,50));

        //add ndes to Vbox
        createSaleVbox.getChildren().addAll(createSaleTitleLabel,dateHbox,salesPersonIdTextField,customerIdTextField,productIdTextField,addButton);
        stageController = new StageController();
        Stage stage = new Stage();
        //Main Scene
        Scene scene = new Scene(createSaleVbox, 820, 620);
        stage.setTitle("Create Sale");
        stage.setScene(scene);
        stage.show();
    }
}
