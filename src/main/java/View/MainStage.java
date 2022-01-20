package View;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import org.controlsfx.control.action.Action;

import java.sql.SQLException;


public class MainStage extends Application {
    @Override
    public void start(Stage stage){

        //Title Label
        Label titleLabel = new Label("BE SPOCKED BICYCLES");
        titleLabel.getStyleClass().add("title-label");

        //Buttons
        //sales button
        Button salesButton = new Button("View Sales");
        salesButton.setPrefSize(150, 100);
        salesButton.getStyleClass().add("rich-blue");
        salesButton.setOnAction(ActionEvent -> {
            try {
                getViewSalesStage();
            } catch (SQLException e) {
                System.out.println("Error Loading Sales Screen");
                e.printStackTrace();
            }
        });
        //customer button
        Button customerButton = new Button("View Customers");
        customerButton.setPrefSize(150, 100);
        customerButton.getStyleClass().add("rich-blue");
        customerButton.setOnAction(ActionEvent -> {
            try {
                getCustomerStage();
            } catch (SQLException e) {
                System.out.println("Error Loading Sales Customers Screen");
                e.printStackTrace();
            }
        });
        //products button
        Button productsButton = new Button("View Products");
        productsButton.setPrefSize(150, 100);
        productsButton.getStyleClass().add("rich-blue");
        productsButton.setOnAction(ActionEvent -> {
            try {
                getProductStage();
            } catch (SQLException e) {
                System.out.println("Error Loading Products Screen");
                e.printStackTrace();
            }
        });
        //sales person button
        Button salesPersonButton = new Button("View Sales\n   Person");
        salesPersonButton.setPrefSize(150, 100);
        salesPersonButton.getStyleClass().add("rich-blue");
        salesPersonButton.setOnAction(ActionEvent -> {
            try {
                getSalesPersonStage();
            } catch (SQLException e) {
                System.out.println("Error Loading Sales Person Screen");
                e.printStackTrace();
            }
        });

        //Hbox to hold the buttons
        HBox buttonsHbox = new HBox();
        buttonsHbox.setPadding(new Insets(15, 12, 15, 12));
        buttonsHbox.setSpacing(50);
        buttonsHbox.setAlignment(Pos.CENTER);

        //add the buttons to the hbox
        buttonsHbox.getChildren().addAll(salesButton,customerButton,productsButton,salesPersonButton);

        //Create new Sale Button
        Button createNewSaleButton = new Button("  Create \nNew Sale");
        createNewSaleButton.setPrefSize(150, 100);
        createNewSaleButton.getStyleClass().add("rich-blue");
        createNewSaleButton.setId("record-sales");

        //add everything to a Vbox
        VBox vbox = new VBox(titleLabel, buttonsHbox, createNewSaleButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        VBox.setMargin(titleLabel, new Insets(50,0,150,0));


        //create scene
        Scene scene = new Scene(vbox, 820, 620);
        scene.getStylesheets().add("file:src/stylesSheet.css");
        stage.setTitle("BeSpocked Bicycle Company");
        stage.setScene(scene);
        stage.show();
    }

    public void getViewSalesStage() throws SQLException {
        ViewSalesStage start = new ViewSalesStage();
    }

    public void getCustomerStage() throws SQLException {
        ViewCustomerStage viewCustomerStage = new ViewCustomerStage();
    }

    public void getProductStage() throws SQLException {
        ViewProductsStage viewProductsStage = new ViewProductsStage();
    }

    public void getSalesPersonStage() throws SQLException {
        ViewSalesPersonStage viewSalesPersonStage = new ViewSalesPersonStage();
    }

    public static void main(String[] args) {
        launch();
    }
}