package com.example.grocerystoresimulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController {
    @FXML private TextField userName;
    @FXML private PasswordField userPassword;
    @FXML private Label loginError;
    @FXML
    protected void onLoginBtnClick(ActionEvent event) {
        try {
            if( ! (userName.getText().equals("admin") && userPassword.getText().equals("admin")) ) {
                loginError.setVisible(true);
                userName.setText("");
                userPassword.setText("");
                return;
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.setX(0);
            stage.setY(0);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onExitBtnClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}