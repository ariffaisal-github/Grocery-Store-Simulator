package com.example.grocerystoresimulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class SellProductsPage implements Initializable {
    @FXML private Label outOfStckPrint;
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


    public void onSellbtnClick(ActionEvent event) {
        OracleConnect oc = null;
        try {
            String type = prodCategoryCombobox.getValue(), name=prodNameCombobox.getValue();
            int orderedAmount=Integer.parseInt(noOfOrderedItems.getText());
            oc = new OracleConnect();
            String query = String.format("select * from products where product_type='%s' and product_name='%s'",type,name);
            ResultSet rs = oc.searchDB(query);
            while (rs.next()) {
                int currentAmount = rs.getInt("amount");
                int pID = rs.getInt("product_id");
                if(orderedAmount > currentAmount) { // out of stock
                    outOfStckPrint.setText("OUT OF STOCK !!");
                    return;
                }
                int totalSellingPrice = orderedAmount*rs.getInt("selling_price");
                int totalCostPrice = orderedAmount*rs.getInt("cost_price");
                oc.updateDB(String.format("update products set amount=amount-%d where product_id=%d",orderedAmount,pID));
                oc.updateDB(String.format("update account set current_balance=current_balance+%d",totalSellingPrice));
                oc.updateDB(String.format("update account set net_profit=net_profit+%d",(totalSellingPrice-totalCostPrice)));


                // now another page opens
                try {
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CustomerInfo.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    stage.setTitle("Fire an Employee");
                    //stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(scene);
                    stage.setX(0);
                    stage.setY(0);
                    stage.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Exception in selling : " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prodCategoryCombobox.getItems().setAll("");
        outOfStckPrint.setText("");
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
}
