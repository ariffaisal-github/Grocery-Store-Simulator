package com.example.grocerystoresimulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class SupplierPage implements Initializable {
    @FXML private TextField noOfOrderedItems;
    @FXML private Button enterCategory;
    @FXML private ComboBox<String> prodCategoryCombobox, prodNameCombobox;


    public void onBackBtnClick(ActionEvent event) {
        try {
            Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
            st.close();
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("GROCERY STORE");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.setX(0);
            stage.setY(0);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prodCategoryCombobox.getItems().setAll("");
        prodCategoryCombobox.getItems().addAll("Bag","Chocolate","Dairy","Juice","Toffee","chips");
        enterCategory.setOnAction(e -> {
            prodNameCombobox.getItems().setAll("");
            String selectedType = prodCategoryCombobox.getValue();
            switch (selectedType) {
                case "Bag": {
                    prodNameCombobox.getItems().addAll("hiking bag","sports bag");
                    break;
                }
                case "Chocolate": {
                    prodNameCombobox.getItems().addAll("dairy milk","kinderjoy","kitkat");
                    break;
                }
                case "Dairy": {
                    prodNameCombobox.getItems().addAll("tasty Cheese","tasty Yoghurt");
                    break;
                }
                case "Juice": {
                    prodNameCombobox.getItems().addAll("Abc juice","PQR juice","Pran juice");
                    break;
                }
                case "Toffee": {
                    prodNameCombobox.getItems().addAll("Abc co.","XYZ");
                    break;
                }
                case "chips": {
                    prodNameCombobox.getItems().addAll("Pran chips","Sweet chips","potato chips","spicy");
                    break;
                }
            }
        });
    }

    public void onConfirmOrderbtnClick(ActionEvent event) {
        int amount = Integer.parseInt(noOfOrderedItems.getText());
        String name = prodNameCombobox.getValue();
        String type = prodCategoryCombobox.getValue();
        int costPrice;
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("SELECT COST_PRICE FROM WHOLESALE " +
                    "WHERE PRODUCT_TYPE='%s' AND PRODUCT_NAME='%s' ",type, name);
            ResultSet rs = oc.searchDB(query);
            while (rs.next()) {
                costPrice = rs.getInt("COST_PRICE");

            }
        } catch (Exception e) {
            System.out.println("Exception in listEmployees: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        noOfOrderedItems.setText(""); prodNameCombobox.getItems().setAll("");
    }
}
