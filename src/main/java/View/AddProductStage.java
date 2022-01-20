package View;

import Controller.StageController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;


/*
* This class bring up the stage to add a new product to the DB
* */
public class AddProductStage {

    private String productName;
    private String productManufacturer;
    private String productStyle;
    private double productPurchasePrice;
    private int quantityOnHand;
    private int commission;
    private int productId;

    StageController stageController;

    public AddProductStage() throws SQLException {
        //Title Label
        Label addProductTitle = new Label("ADD NEW PRODUCT TO SYSTEM");

        //product id
        TextField productIdTextField = new TextField();
        productIdTextField.setMaxWidth(220);
        productIdTextField.setPromptText("ENTER PRODUCT ID");

        //product name
        TextField productNameTextField = new TextField();
        productNameTextField.setMaxWidth(220);
        productNameTextField.setPromptText("ENTER PRODUCT NAME");

        //product manufacturer
        TextField productManufacturerTextField = new TextField();
        productManufacturerTextField.setMaxWidth(220);
        productManufacturerTextField.setPromptText("MANUFACTURER");

        //address
        TextField productStyleTextField = new TextField();
        productStyleTextField.setMaxWidth(220);
        productStyleTextField.setPromptText("ENTER BICYCLE STYLE");
        //phone
        TextField productPurchasePriceTextField = new TextField();
        productPurchasePriceTextField.setMaxWidth(220);
        productPurchasePriceTextField.setPromptText("ENTER PURCHASE PRICE");

        //Manager
        TextField productQuantityOnHandTextField = new TextField();
        productQuantityOnHandTextField.setMaxWidth(220);
        productQuantityOnHandTextField.setPromptText("ENTER QUANTITY ON HAND");

        //Sales Person ID
        TextField productCommissionPercentageTextField = new TextField();
        productCommissionPercentageTextField.setMaxWidth(220);
        productCommissionPercentageTextField.setPromptText("ENTER COMMISSION RATE");


        Button addButton = new Button("ADD PRODUCT");
        //add button action
        addButton.setOnAction(ActionEvent -> {
            try {
                productPurchasePrice = Double.parseDouble(productPurchasePriceTextField.getText());   //product Price
                commission = Integer.parseInt(productCommissionPercentageTextField.getText());// product commission
                productManufacturer = productManufacturerTextField.getText();// product Manufacturer
                productName = productNameTextField.getText();// product name
                productStyle = productStyleTextField.getText();//product styles
                quantityOnHand = Integer.parseInt(productQuantityOnHandTextField.getText());// quantity on hand
                productId = Integer.parseInt(productIdTextField.getText()); //product id

                //calls the create sale method passing the form information
                stageController.CreateProduct(productName,quantityOnHand, commission,
                        productPurchasePrice,productManufacturer, productStyle,productId);

                productIdTextField.clear();
                productCommissionPercentageTextField.clear();
                productNameTextField.clear();
                productManufacturerTextField.clear();
                productPurchasePriceTextField.clear();
                productQuantityOnHandTextField.clear();
                productStyleTextField.clear();
            } catch (SQLException e) {
                e.printStackTrace();
            }catch (NumberFormatException a){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Adding Product");
                alert.setHeaderText("Error Adding Product Try Again");
                alert.showAndWait();
                productIdTextField.clear();
                productCommissionPercentageTextField.clear();
                productNameTextField.clear();
                productManufacturerTextField.clear();
                productPurchasePriceTextField.clear();
                productQuantityOnHandTextField.clear();
                productStyleTextField.clear();
            }
        });
        //Vbox to hold nodes
        VBox addProductVbox = new VBox();
        addProductVbox.setAlignment(Pos.CENTER);
        addProductVbox.setSpacing(20);
        VBox.setMargin(addProductVbox, new Insets(50,50,150,50));

        //add nodes to Vbox
        addProductVbox.getChildren().addAll(addProductTitle,productIdTextField,productNameTextField, productManufacturerTextField, productStyleTextField,productPurchasePriceTextField
        ,productCommissionPercentageTextField,productQuantityOnHandTextField,addButton);
        stageController = new StageController();
        Stage stage = new Stage();
        //Main Scene
        Scene scene = new Scene(addProductVbox, 820, 620);
        stage.setTitle("Create Sale");
        stage.setScene(scene);
        stage.show();
    }
}
