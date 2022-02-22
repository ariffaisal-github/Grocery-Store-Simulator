package com.example.grocerystoresimulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class BillsPage implements Initializable {

    @FXML private Label currentBalance,taxPending,electricPending;

    public void onBackbtnClick(ActionEvent event) {
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

    public void onPayBillsbtnClick(ActionEvent event) {
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            oc.updateDB(String.format("update account set current_balance=current_balance-(%s+%s)", taxPending.getText(),electricPending.getText()));
            ResultSet rs = oc.searchDB("SELECT CURRENT_BALANCE FROM ACCOUNT");
            taxPending.setText("0"); electricPending.setText("0");
            while (rs.next()) {
                int curr_bal = rs.getInt("CURRENT_BALANCE");
                currentBalance.setText(Integer.toString(curr_bal));
            }
        } catch (Exception e) {
            System.out.println("Exception in bills: " + e);
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
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            ResultSet rs = oc.searchDB("SELECT CURRENT_BALANCE FROM ACCOUNT");
            while (rs.next()) {
                int curr_bal = rs.getInt("CURRENT_BALANCE");
                currentBalance.setText(Integer.toString(curr_bal));
            }
        } catch (Exception e) {
            System.out.println("Exception in bills: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
